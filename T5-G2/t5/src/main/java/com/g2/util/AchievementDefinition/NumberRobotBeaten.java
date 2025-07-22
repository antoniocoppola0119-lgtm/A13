package com.g2.util.AchievementDefinition;

import com.g2.Model.DTO.GameProgressDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class NumberRobotBeaten {

    public static Map<String, Function<List<GameProgressDTO>, Boolean>> getAchievementFunctions() {
        Map<String, Function<List<GameProgressDTO>, Boolean>> verifyBeaten = new HashMap<>();
        verifyBeaten.put("firstMatchWon", NumberRobotBeaten::firstMatchWon);
        verifyBeaten.put("thirdMatchWon", NumberRobotBeaten::thirdMatchWon);

        return verifyBeaten;
    }

    private static Boolean firstMatchWon(List<GameProgressDTO> gameProgresses) {
        return gameProgresses != null && gameProgresses.stream().anyMatch(GameProgressDTO::isWon);
    }

    private static Boolean thirdMatchWon(List<GameProgressDTO> gameProgresses) {
        return gameProgresses != null && gameProgresses.stream().filter(GameProgressDTO::isWon).count() >= 3;
    }
}
