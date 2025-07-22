package com.t4.gamerepo.service;

import com.t4.gamerepo.model.Round;
import com.t4.gamerepo.model.Turn;
import com.t4.gamerepo.model.TurnScore;
import com.t4.gamerepo.model.repositories.RoundRepository;
import com.t4.gamerepo.service.exceptions.RoundAlreadyClosedException;
import org.springframework.stereotype.Service;
import testrobotchallenge.commons.models.opponent.OpponentDifficulty;
import testrobotchallenge.commons.models.opponent.OpponentType;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class RoundService {

    private final RoundRepository roundRepository;
    private final TurnService turnService;

    public RoundService(RoundRepository roundRepository, TurnService turnService) {
        this.roundRepository = roundRepository;
        this.turnService = turnService;
    }

    private int nextTurnNumber(Round round) {
        List<Turn> turns = round.getTurns();
        return turns.isEmpty() ? 1 : round.getLastTurn().getTurnNumber() + 1;
    }

    public Round createRound(int roundNumber, String classUT, OpponentType type, OpponentDifficulty difficulty) {
        Round round = new Round(roundNumber, classUT, type, difficulty);
        return roundRepository.save(round);
    }

    public Turn startTurn(Round round, Long playerId) {
        if (round.getClosedAt() != null)
            throw new RoundAlreadyClosedException("Round " + round.getRoundNumber() + " has already been closed");

        Turn turn = turnService.createTurn(playerId, nextTurnNumber(round));
        round.addTurn(turn);
        roundRepository.save(round);

        return turn;
    }

    public Turn closeTurn(Round currentRound, int turnNumber, Long playerId, TurnScore turnScore) {
        return turnService.closeTurn(currentRound.getTurns().get(turnNumber - 1), playerId, turnScore);
    }

    public Round closeRound(Round round) {
        if (round.getClosedAt() != null)
            throw new RoundAlreadyClosedException("Round " + round.getRoundNumber() + " has already been closed");
        round.setClosedAt(Timestamp.from(Instant.now()));
        return roundRepository.save(round);
    }
}
