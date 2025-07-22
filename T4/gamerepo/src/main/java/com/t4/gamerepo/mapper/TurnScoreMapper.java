package com.t4.gamerepo.mapper;

import com.t4.gamerepo.model.TurnScore;
import com.t4.gamerepo.model.dto.CloseTurnDTO;
import testrobotchallenge.commons.mappers.ScoreMapper;

public class TurnScoreMapper {

    public static TurnScore toEntity(CloseTurnDTO dto) {
        if (dto == null) return null;

        TurnScore entity = new TurnScore();

        // Jacoco
        if (dto.getJacocoScoreDTO() != null) {
            entity.setJacocoScore(ScoreMapper.toJacocoScore(dto.getJacocoScoreDTO()));
        }

        // Evosuite
        if (dto.getEvosuiteScoreDTO() != null) {
            entity.setEvosuiteScore(ScoreMapper.toEvosuiteScore(dto.getEvosuiteScoreDTO()));
        }

        return entity;
    }
}
