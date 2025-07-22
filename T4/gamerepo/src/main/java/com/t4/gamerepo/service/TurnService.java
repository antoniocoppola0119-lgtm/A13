package com.t4.gamerepo.service;

import com.t4.gamerepo.model.Turn;
import com.t4.gamerepo.model.TurnScore;
import com.t4.gamerepo.model.repositories.TurnRepository;
import com.t4.gamerepo.service.exceptions.NotPlayerTurnException;
import com.t4.gamerepo.service.exceptions.TurnAlreadyClosedException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class TurnService {

    private final TurnRepository turnRepository;

    public TurnService(TurnRepository turnRepository) {
        this.turnRepository = turnRepository;
    }

    public Turn createTurn(Long playerId, int turnNumber) {
        Turn turn = new Turn();
        turn.setPlayerId(playerId);
        turn.setTurnNumber(turnNumber);
        turnRepository.save(turn);

        return turn;
    }

    public Turn closeTurn(Turn turn, Long playerId, TurnScore turnScore) {
        if (!turn.getPlayerId().equals(playerId))
            throw new NotPlayerTurnException("Turn " + turn.getTurnNumber() + " is not associated with player " + playerId);

        if (turn.getClosedAt() != null)
            throw new TurnAlreadyClosedException("Turn " + turn.getTurnNumber() + " is already closed at " + turn.getClosedAt());

        turn.setClosedAt(Timestamp.from(Instant.now()));
        turn.setScore(turnScore);
        return turnRepository.save(turn);
    }

}
