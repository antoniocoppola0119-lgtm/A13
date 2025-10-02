package com.t4.gamerepo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import testrobotchallenge.commons.models.opponent.OpponentDifficulty;
import testrobotchallenge.commons.models.opponent.OpponentType;

import java.sql.Timestamp;
import java.util.*;

/**
 * Entità che rappresenta un singolo round all'interno di una partita.
 * <p>
 * Contiene informazioni sul round quali:
 * <ul>
 *     <li>Numero del round;</li>
 *     <li>Classe sotto test (classUT);</li>
 *     <li>Tipo e difficoltà dell’avversario;</li>
 *     <li>Turni giocati in questo round;</li>
 *     <li>Timestamp di apertura e chiusura.</li>
 * </ul>
 * </p>
 */
@Entity
@Table(name = "rounds")
@Getter
@Setter
@NoArgsConstructor
public class Round {

    /** Identificativo univoco del round */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** Nome della classe sotto test in questo round */
    private String classUT;

    /** Tipo dell’avversario */
    @Enumerated(EnumType.STRING)
    private OpponentType type;

    /** Difficoltà dell’avversario */
    @Enumerated(EnumType.STRING)
    private OpponentDifficulty difficulty;

    /** Numero progressivo del round all’interno della partita */
    private int roundNumber;

    /** Lista dei turni giocati nel round */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "round_id")
    @OrderBy("turnNumber asc")
    private List<Turn> turns = new ArrayList<>();

    /** Timestamp di apertura del round, valorizzato automaticamente */
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Timestamp startedAt;

    /** Timestamp di chiusura/completamento del round, null se il round è ancora aperto */
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp closedAt;

    /**
     * Costruttore completo.
     *
     * @param roundNumber   il numero del round
     * @param classUT       la classe sotto test
     * @param type          il tipo dell’avversario
     * @param difficulty    la difficoltà dell’avversario
     */
    public Round(int roundNumber, String classUT, OpponentType type, OpponentDifficulty difficulty) {
        this.roundNumber = roundNumber;
        this.classUT = classUT;
        this.type = type;
        this.difficulty = difficulty;
    }

    /**
     * Aggiunge un turno alla lista dei turni del round.
     *
     * @param turn      il turno da aggiungere
     */
    public void addTurn(Turn turn) {
        this.turns.add(turn);
    }

    /**
     * Restituisce l’ultimo turno giocato in questo round.
     *
     * @return      l'ultimo turno oppure {@code null} se non ci sono turni
     */
    @JsonIgnore
    public Turn getLastTurn() {
        if (turns == null || turns.isEmpty()) {
            return null;
        }
        return turns.get(turns.size() - 1);
    }
}

