package com.t4.gamerepo.model.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import testrobotchallenge.commons.models.opponent.OpponentDifficulty;
import testrobotchallenge.commons.models.opponent.OpponentType;

/**
 * DTO usato per la creazione di un nuovo round.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateRoundDTO {
    /**
     * Classe sotto test da testare nel round.
     */
    private String classUT;

    /**
     * Tipo di avversario da affrontare nel round.
     */
    private OpponentType type;

    /**
     * Difficoltà dell'avversario.
     */
    private OpponentDifficulty difficulty;

    /**
     * Numero del round da creare all'interno della partita.
     */
    private int roundNumber;
}
