package com.t4.gamerepo.service;

import com.t4.gamerepo.model.*;
import com.t4.gamerepo.model.repositories.GameRepository;
import com.t4.gamerepo.model.PlayerResult;
import com.t4.gamerepo.service.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testrobotchallenge.commons.models.opponent.GameMode;
import testrobotchallenge.commons.models.opponent.OpponentDifficulty;
import testrobotchallenge.commons.models.opponent.OpponentType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final RoundService roundService;

    private final Logger logger = LoggerFactory.getLogger(GameService.class);


    public GameService(GameRepository gameRepository, RoundService roundService) {
        this.gameRepository = gameRepository;
        this.roundService = roundService;
    }

    public Game getGameById(Long gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException("Game not found"));
    }

    public List<Game> getAllPlayerGames(Long playerId) {
        return gameRepository.findByPlayerId(playerId);
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }


    @Transactional
    public Game createGame(GameMode gameMode, List<Long> players) {
        Set<Long> playersSet = new HashSet<>(players);

        if (playersSet.size() != players.size())
            throw new DuplicatedPlayersInGameException("Duplicated players in game");

        Game newGame = new Game(gameMode, players);
        newGame.setStatus(GameStatus.CREATED);

        return gameRepository.save(newGame);
    }

    @Transactional
    public Round startRound(Long gameId, String classUT, OpponentType type, OpponentDifficulty difficulty) {
        Game game = getGameById(gameId);

        if (game.getClosedAt() != null)
            throw new GameAlreadyClosedException("Game already closed");

        List<Round> rounds = game.getRounds();
        int roundNumber;
        if (rounds.isEmpty()) {
            roundNumber = 1;
        } else {
            if (game.getLastRound().getClosedAt() == null)
                throw new FoundRoundNotClosedException("The last round has not been closed, can't create a new round");

            roundNumber = game.getLastRound().getRoundNumber() + 1;
        }

        Round newRound = roundService.createRound(roundNumber, classUT, type, difficulty);
        game.addRound(newRound);
        game.setStatus(GameStatus.STARTED);
        gameRepository.save(game);

        return newRound;
    }

    @Transactional
    public Turn startTurn(Long gameId, Long playerId) {
        Game game = getGameById(gameId);
        List<Round> rounds = game.getRounds();

        if (game.getClosedAt() != null)
            throw new GameAlreadyClosedException("Game already closed");

        if (!game.getPlayers().contains(playerId))
            throw new PlayerNotInGameException("Player not in game");

        if (rounds.isEmpty())
            throw new RoundNotFoundException("Round not found");

        Turn newTurn = roundService.startTurn(game.getLastRound(), playerId);

        game.setStatus(GameStatus.IN_PROGRESS);
        gameRepository.save(game);

        return newTurn;
    }

    @Transactional
    public Turn endTurn(Long gameId, Long playerId, int turnNumber, TurnScore turnScore) {
        Game game = getGameById(gameId);
        List<Round> rounds = game.getRounds();

        logger.info("Game rounds: {}", rounds);

        if (game.getClosedAt() != null) {
            logger.error("Game {} is already closed", gameId);
            throw new GameAlreadyClosedException("Game already closed");
        }

        if (!game.getPlayers().contains(playerId)) {
            logger.error("Player {} isn't register in game {}", playerId, gameId);
            throw new PlayerNotInGameException("Player not in game");
        }

        if (rounds.isEmpty()) {
            logger.error("Rounds not found for game {}", gameId);
            throw new RoundNotFoundException("Round not found");
        }

        Turn closedTurn = roundService.closeTurn(game.getLastRound(), turnNumber, playerId, turnScore);
        gameRepository.save(game);

        return closedTurn;
    }

    @Transactional
    public Round endRound(Long gameId) {
        Game game = getGameById(gameId);
        List<Round> rounds = game.getRounds();

        if (game.getClosedAt() != null)
            throw new GameAlreadyClosedException("Game already closed");

        if (rounds.isEmpty())
            throw new RoundNotFoundException("Round not found");

        Round closedRound = roundService.closeRound(game.getLastRound());
        gameRepository.save(game);

        return closedRound;
    }

    @Transactional
    public Game endGame(Long gameId, Map<Long, PlayerResult> playersResult) {
        Game game = getGameById(gameId);

        if (game.getClosedAt() != null)
            throw new GameAlreadyClosedException("Game already closed");

        game.setStatus(GameStatus.FINISHED);
        game.setClosedAt(Timestamp.from(Instant.now()));
        game.setPlayerResults(playersResult);

        return gameRepository.save(game);
    }
}
