package com.example.db_setup.model.dto.gamification;

import lombok.*;
import testrobotchallenge.commons.models.opponent.GameMode;
import testrobotchallenge.commons.models.opponent.OpponentDifficulty;
import testrobotchallenge.commons.models.opponent.OpponentType;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OpponentDTO {
    private String classUT;
    private OpponentType type;
    private OpponentDifficulty difficulty;
    private GameMode gameMode;
}
