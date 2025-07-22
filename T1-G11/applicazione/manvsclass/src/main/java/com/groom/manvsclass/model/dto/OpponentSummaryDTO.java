package com.groom.manvsclass.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import testrobotchallenge.commons.models.opponent.OpponentDifficulty;
import testrobotchallenge.commons.models.opponent.OpponentType;

@Data
@AllArgsConstructor
public class OpponentSummaryDTO {
    private String classUT;
    private OpponentType opponentType;
    private OpponentDifficulty opponentDifficulty;
}
