package com.t4.gamerepo.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import testrobotchallenge.commons.models.dto.score.basic.EvosuiteScoreDTO;
import testrobotchallenge.commons.models.dto.score.basic.JacocoScoreDTO;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CloseTurnDTO {

    private Long playerId;
    private EvosuiteScoreDTO evosuiteScoreDTO;
    private JacocoScoreDTO jacocoScoreDTO;
}
