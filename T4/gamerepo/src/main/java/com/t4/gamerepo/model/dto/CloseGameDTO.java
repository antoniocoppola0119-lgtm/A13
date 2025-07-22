package com.t4.gamerepo.model.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CloseGameDTO {
    Map<Long, PlayerResultDTO> results;

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class PlayerResultDTO {
        private boolean isWinner;
        private int score;
    }
}
