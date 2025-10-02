package com.t4.gamerepo.model.dto;

import lombok.*;

import java.util.Map;

/**
 * DTO usato per la chiusura (completamento) di una partita.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CloseGameDTO {

    /**
     * Mappa che associa l'ID di ciascun giocatore al relativo risultato.
     */
    Map<Long, PlayerResultDTO> results;

    /**
     * DTO che rappresenta il risultato di un singolo giocatore.
     * Include informazioni sul punteggio finale ottenuto giocatore e se è il vincitore.
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class PlayerResultDTO {
        /** Indica se il giocatore ha vinto la partita */
        private boolean isWinner;

        /**
         * Punteggio finale ottenuto dal giocatore nella partita. Corrisponde al punteggio usato dal GameEngine T56 per
         * determinare se il giocatore ha vinto.
         */
        private int score;
    }
}
