package com.t4.gamerepo.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;
import lombok.NoArgsConstructor;
import testrobotchallenge.commons.models.score.EvosuiteScore;
import testrobotchallenge.commons.models.score.JacocoScore;

@Data
@NoArgsConstructor
@Embeddable
public class TurnScore {

    @Embedded
    private JacocoScore jacocoScore;

    @Embedded
    private EvosuiteScore evosuiteScore;

}
