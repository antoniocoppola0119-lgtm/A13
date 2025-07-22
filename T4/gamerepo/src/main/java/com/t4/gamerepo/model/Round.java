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

@Entity
@Table(name = "rounds")
@Getter
@Setter
@NoArgsConstructor
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String classUT;

    @Enumerated(EnumType.STRING)
    private OpponentType type;

    @Enumerated(EnumType.STRING)
    private OpponentDifficulty difficulty;

    private int roundNumber;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "round_id")
    @OrderBy("turnNumber asc")
    private List<Turn> turns = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Timestamp startedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp closedAt;

    public Round(int roundNumber, String classUT, OpponentType type, OpponentDifficulty difficulty) {
        this.roundNumber = roundNumber;
        this.classUT = classUT;
        this.type = type;
        this.difficulty = difficulty;
    }

    public void addTurn(Turn turn) {
        this.turns.add(turn);
    }

    @JsonIgnore
    public Turn getLastTurn() {
        if (turns == null || turns.isEmpty()) {
            return null;
        }
        return turns.get(turns.size() - 1);
    }
}

