package com.t4.gamerepo.mapper;

import com.t4.gamerepo.model.TurnScore;
import com.t4.gamerepo.model.dto.CloseTurnDTO;
import testrobotchallenge.commons.mappers.ScoreMapper;

/**
 * Mapper che converte un oggetto {@link CloseTurnDTO} in un'entità {@link TurnScore}.
 */
public class TurnScoreMapper {

    private TurnScoreMapper() {
        throw new IllegalStateException("Classe mapper che converte un CloseTurnDTO in un TurnScore.");
    }

    /**
     * Converte un {@link CloseTurnDTO} in un {@link TurnScore}.
     *
     * @param dto   il DTO da convertire
     * @return      entità TurnScore corrispondente, o {@code null} se il DTO è {@code null}
     */
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
