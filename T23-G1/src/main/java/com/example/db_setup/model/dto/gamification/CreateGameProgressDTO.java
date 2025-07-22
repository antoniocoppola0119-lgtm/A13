package com.example.db_setup.model.dto.gamification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import testrobotchallenge.commons.models.opponent.GameMode;
import testrobotchallenge.commons.models.opponent.OpponentDifficulty;
import testrobotchallenge.commons.models.opponent.OpponentType;

@Getter
@Setter
@NoArgsConstructor
public class CreateGameProgressDTO {
    private String classUT;
    private GameMode gameMode;
    private OpponentType type;
    private OpponentDifficulty difficulty;
}
