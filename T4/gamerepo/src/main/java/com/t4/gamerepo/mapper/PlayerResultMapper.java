package com.t4.gamerepo.mapper;

import com.t4.gamerepo.model.dto.CloseGameDTO;
import com.t4.gamerepo.model.PlayerResult;

/**
 * Mapper che converte un oggetto {@link CloseGameDTO.PlayerResultDTO} in un'entità {@link PlayerResult}.
 */
public class PlayerResultMapper {

    private PlayerResultMapper() {
        throw new IllegalStateException("Classe mapper che converte un un PlayerResultDTO in un PlayerResult.");
    }

    /**
     * Converte un {@link CloseGameDTO.PlayerResultDTO} in un {@link PlayerResult}
     *
     * @param playerResultDTO       il DTO da convertire
     * @return                      l'entità PlayerResult corrispondente, o {@code null} se il DTO è {@code null}
     */
    public static PlayerResult toEntity(CloseGameDTO.PlayerResultDTO playerResultDTO) {
        if (playerResultDTO == null)
            return null;

        PlayerResult playerResult = new PlayerResult();
        playerResult.setWinner(playerResultDTO.isWinner());
        playerResult.setScore(playerResultDTO.getScore());

        return playerResult;
    }
}
