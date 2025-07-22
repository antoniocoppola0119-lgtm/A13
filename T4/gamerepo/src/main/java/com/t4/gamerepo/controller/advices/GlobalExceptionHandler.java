package com.t4.gamerepo.controller.advices;

import com.t4.gamerepo.service.exceptions.*;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<Map<String, List<Map<String, String>>>> gameNotFound(GameNotFoundException e, Locale locale) {
        return ResponseEntity.status(NOT_FOUND).body(Map.of("errors", List.of(Map.of("field", "none",
                "message", messageSource.getMessage("errors.game.notFound", null, locale)))));
    }

    @ExceptionHandler(PlayerNotInGameException.class)
    public ResponseEntity<Map<String, List<Map<String, String>>>> playerNotInGame(GameNotFoundException e, Locale locale) {
        return ResponseEntity.badRequest().body(Map.of("errors", List.of(Map.of("field", "none",
                "message", messageSource.getMessage("errors.players.notFoundInGame", null, locale)))));
    }

    @ExceptionHandler(DuplicatedPlayersInGameException.class)
    public ResponseEntity<Map<String, List<Map<String, String>>>> duplicatedPlayersInGame(DuplicatedPlayersInGameException e, Locale locale) {
        return ResponseEntity.badRequest().body(Map.of("errors", List.of(Map.of("field", "none",
                "message", messageSource.getMessage("errors.players.duplicated", null, locale)))));
    }

    @ExceptionHandler(FoundRoundNotClosedException.class)
    public ResponseEntity<Map<String, List<Map<String, String>>>> foundRoundNotClosed(FoundRoundNotClosedException e, Locale locale) {
        return ResponseEntity.badRequest().body(Map.of("errors", List.of(Map.of("field", "none",
                "message", messageSource.getMessage("errors.rounds.foundOpen", null, locale)))));
    }

    @ExceptionHandler(GameAlreadyClosedException.class)
    public ResponseEntity<Map<String, List<Map<String, String>>>> gameAlreadyClosed(GameAlreadyClosedException e, Locale locale) {
        return ResponseEntity.badRequest().body(Map.of("errors", List.of(Map.of("field", "none",
                "message", messageSource.getMessage("errors.games.alreadyClosed", null, locale)))));
    }

    @ExceptionHandler(RoundAlreadyClosedException.class)
    public ResponseEntity<Map<String, List<Map<String, String>>>> foundRoundNotClosed(RoundAlreadyClosedException e, Locale locale) {
        return ResponseEntity.badRequest().body(Map.of("errors", List.of(Map.of("field", "none",
                "message", messageSource.getMessage("errors.rounds.alreadyClosed", null, locale)))));
    }

    @ExceptionHandler(TurnAlreadyClosedException.class)
    public ResponseEntity<Map<String, List<Map<String, String>>>> turnAlreadyClosed(TurnAlreadyClosedException e, Locale locale) {
        return ResponseEntity.badRequest().body(Map.of("errors", List.of(Map.of("field", "none",
                "message", messageSource.getMessage("errors.turns.alreadyClosed", null, locale)))));
    }

    @ExceptionHandler(NotPlayerTurnException.class)
    public ResponseEntity<Map<String, List<Map<String, String>>>> notPlayerTurn(NotPlayerTurnException e, Locale locale) {
        return ResponseEntity.badRequest().body(Map.of("errors", List.of(Map.of("field", "none",
                "message", messageSource.getMessage("errors.players.notInTurn", null, locale)))));
    }

    @ExceptionHandler(RoundNotFoundException.class)
    public ResponseEntity<Map<String, List<Map<String, String>>>> roundNOtFound(RoundNotFoundException e, Locale locale) {
        return ResponseEntity.badRequest().body(Map.of("errors", List.of(Map.of("field", "none",
                "message", messageSource.getMessage("errors.rounds.notFound", null, locale)))));
    }
}
