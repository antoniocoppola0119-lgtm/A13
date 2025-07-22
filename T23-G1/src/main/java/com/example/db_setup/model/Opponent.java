package com.example.db_setup.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import testrobotchallenge.commons.models.opponent.GameMode;
import testrobotchallenge.commons.models.opponent.OpponentDifficulty;
import testrobotchallenge.commons.models.opponent.OpponentType;

import javax.persistence.*;

@Table(
        name = "opponents",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"gameMode", "classUT", "type", "difficulty"}
        )
)
@Entity
@Data
@NoArgsConstructor
public class Opponent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private GameMode gameMode;
    private String classUT;
    private OpponentType type;
    private OpponentDifficulty difficulty;

    private boolean active;

    public Opponent(GameMode gameMode, String classUT, OpponentType type, OpponentDifficulty difficulty) {
        this.gameMode = gameMode;
        this.classUT = classUT;
        this.type = type;
        this.difficulty = difficulty;
    }
}
