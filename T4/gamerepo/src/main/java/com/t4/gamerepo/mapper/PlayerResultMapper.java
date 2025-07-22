package com.t4.gamerepo.mapper;

import com.t4.gamerepo.model.dto.CloseGameDTO;
import com.t4.gamerepo.model.PlayerResult;

public class PlayerResultMapper {

    public static PlayerResult toEntity(CloseGameDTO.PlayerResultDTO playerResultDTO) {
        if (playerResultDTO == null)
            return null;

        PlayerResult playerResult = new PlayerResult();
        playerResult.setWinner(playerResultDTO.isWinner());
        playerResult.setScore(playerResultDTO.getScore());

        return playerResult;
    }
}
