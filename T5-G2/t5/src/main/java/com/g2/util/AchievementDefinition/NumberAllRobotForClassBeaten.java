package com.g2.util.AchievementDefinition;

import com.g2.Model.DTO.GameProgressDTO;
import com.g2.Model.OpponentSummary;
import com.g2.Model.UserGameProgress;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class NumberAllRobotForClassBeaten {

    public static Map<String, BiFunction<Map<String, List<GameProgressDTO>>, Map<String, List<OpponentSummary>>, Boolean>> getAchievementFunctions() {
        Map<String, BiFunction<Map<String, List<GameProgressDTO>>, Map<String, List<OpponentSummary>>, Boolean>> verifyBeaten = new HashMap<>();

        verifyBeaten.put("allBeatenOneClass", NumberAllRobotForClassBeaten::beatAllRobotForOneClassUT);
        verifyBeaten.put("allBeatenTwoClass", NumberAllRobotForClassBeaten::beatAllRobotForTwoClassUT);

        return verifyBeaten;
    }


    private static Boolean beatAllRobotForOneClassUT(Map<String, List<GameProgressDTO>> gameProgressesByClass, Map<String, List<OpponentSummary>> availableRobotsByClass) {
        for (String classUT : gameProgressesByClass.keySet()) {
            if (availableRobotsByClass.containsKey(classUT) && gameProgressesByClass.get(classUT).size() == availableRobotsByClass.get(classUT).size())
                return true;
        }
        return false;
    }

    private static Boolean beatAllRobotForTwoClassUT(Map<String, List<GameProgressDTO>> gameProgressesByClass, Map<String, List<OpponentSummary>> availableRobotsByClass) {
        int beaten = 0;
        for (String classUT : gameProgressesByClass.keySet()) {
            if (availableRobotsByClass.containsKey(classUT) && gameProgressesByClass.get(classUT).size() == availableRobotsByClass.get(classUT).size())
                beaten++;
        }
        return beaten >= 2;
    }
}
