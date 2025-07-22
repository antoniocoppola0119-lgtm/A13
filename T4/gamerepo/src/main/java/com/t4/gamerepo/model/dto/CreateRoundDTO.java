package com.t4.gamerepo.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import testrobotchallenge.commons.models.opponent.OpponentDifficulty;
import testrobotchallenge.commons.models.opponent.OpponentType;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateRoundDTO {
    private String classUT;
    private OpponentType type;
    private OpponentDifficulty difficulty;
}
