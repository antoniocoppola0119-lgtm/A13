package com.groom.manvsclass.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
