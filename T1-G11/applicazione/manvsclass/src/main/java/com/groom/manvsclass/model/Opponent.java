package com.groom.manvsclass.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import testrobotchallenge.commons.models.opponent.OpponentDifficulty;
import testrobotchallenge.commons.models.opponent.OpponentType;
import testrobotchallenge.commons.models.score.EvosuiteScore;
import testrobotchallenge.commons.models.score.JacocoScore;

import javax.persistence.*;
import java.time.Instant;

@Document(collection = "opponents")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Opponent {

    @Id
    private String id;

    @CreatedDate
    private Instant createdAt;

    @Indexed
    private String classUT;

    @Indexed
    private OpponentDifficulty opponentDifficulty;

    @Indexed
    private OpponentType opponentType;

    private String coverage;

    private JacocoScore jacocoScore;
    private EvosuiteScore evosuiteScore;

}
