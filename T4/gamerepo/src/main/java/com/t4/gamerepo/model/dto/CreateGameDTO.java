package com.t4.gamerepo.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import testrobotchallenge.commons.models.opponent.GameMode;
import testrobotchallenge.commons.models.opponent.OpponentDifficulty;
import testrobotchallenge.commons.models.opponent.OpponentType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateGameDTO {
    private GameMode gameMode;
    private List<Long> players;
}
