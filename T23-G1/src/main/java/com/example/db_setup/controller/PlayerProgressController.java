package com.example.db_setup.controller;

import com.example.db_setup.model.GameProgress;
import com.example.db_setup.model.PlayerProgress;
import com.example.db_setup.model.dto.gamification.*;
import com.example.db_setup.service.PlayerProgressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import testrobotchallenge.commons.models.opponent.GameMode;
import testrobotchallenge.commons.models.opponent.OpponentDifficulty;
import testrobotchallenge.commons.models.opponent.OpponentType;

import java.util.Set;

@RestController
@CrossOrigin
public class PlayerProgressController {

    private final PlayerProgressService playerProgressService;
    private final Logger logger = LoggerFactory.getLogger(PlayerProgressController.class);

    public PlayerProgressController(PlayerProgressService playerProgressService) {
        this.playerProgressService = playerProgressService;
    }

    //@GetMapping("/{playerId}")
    @GetMapping("/players/{playerId}/progression")
    public ResponseEntity<PlayerProgressDTO> getProgressByPlayerId(@PathVariable("playerId") long playerId) {
        logger.info("[GET /{}] Received request to retrieve PlayerProgress by playerId {}", playerId, playerId);
        PlayerProgress playerProgress = playerProgressService.getProgressByPlayerId(playerId);
        logger.info(playerProgress.toString());
        PlayerProgressDTO playerProgressDTO = new PlayerProgressDTO(
                playerProgress.getExperiencePoints(),
                playerProgress.getGlobalAchievements(),
                playerProgress.getProgresses());
        logger.info("[GET /{}] Returned PlayerProgressDTO: {}", playerId, playerProgressDTO);
        return ResponseEntity.ok(playerProgressDTO);
    }

    //@GetMapping("/{playerId}/{gameMode}/{classUT}/{opponentType}/{opponentDifficulty}")
    @GetMapping("/players/{playerId}/progression/against/{gameMode}/{classUT}/{opponentType}/{opponentDifficulty}")
    public ResponseEntity<GameProgressDTO> getPlayerProgressAgainstOpponent(@PathVariable("playerId") long playerId,
                                                                            @PathVariable("gameMode") GameMode gameMode,
                                                                            @PathVariable("classUT") String classUT,
                                                                            @PathVariable("opponentType") OpponentType type,
                                                                            @PathVariable("opponentDifficulty") OpponentDifficulty difficulty) {

        GameProgress gameProgress = playerProgressService.getPlayerGameProgressAgainstOpponent(playerId, gameMode, classUT, type, difficulty);
        return ResponseEntity.ok(new GameProgressDTO(gameProgress.getOpponent().getClassUT(), gameProgress.getOpponent().getGameMode(),
                gameProgress.getOpponent().getType(), gameProgress.getOpponent().getDifficulty(),
                gameProgress.isWinner(), gameProgress.getAchievements()));
    }

    //@GetMapping("/{playerId}/experience")
    @GetMapping("/players/{playerId}/progression/experience")
    public ResponseEntity<Integer> getPlayerExperience(@PathVariable("playerId") long playerId) {
        return ResponseEntity.ok(playerProgressService.getPlayerExperience(playerId));
    }

    //@GetMapping("/players/{playerId}/achievements/global")
    @GetMapping("/players/{playerId}/progression/achievements/global")
    public ResponseEntity<Set<String>> getPlayerGlobalAchievements(@PathVariable("playerId") long playerId) {
        return ResponseEntity.ok(playerProgressService.getPlayerGlobalAchievements(playerId));
    }

    //@PostMapping("/{playerId}")
    @PostMapping("/players/{playerId}/progression/against")
    public ResponseEntity<GameProgressDTO> createPlayerProgressAgainstOpponent(@PathVariable("playerId") long playerId,
                                                                               @Validated @RequestBody CreateGameProgressDTO dto) {
        GameProgress gameProgress = playerProgressService.
                createPlayerGameProgressAgainstOpponent(playerId, dto.getGameMode(), dto.getClassUT(), dto.getType(), dto.getDifficulty());
        return ResponseEntity.ok(new GameProgressDTO(gameProgress.getOpponent().getClassUT(), gameProgress.getOpponent().getGameMode(),
                gameProgress.getOpponent().getType(), gameProgress.getOpponent().getDifficulty(),
                gameProgress.isWinner(), gameProgress.getAchievements()));
    }

    //@PutMapping("/{playerId}/experience")
    @PutMapping("/players/{playerId}/progression/experience")
    public ResponseEntity<Integer> updatePlayerExperience(@PathVariable("playerId") long playerId, @RequestBody @Validated ExperienceDTO expDTO) {
        return ResponseEntity.ok(playerProgressService.updatePlayerExperience(playerId, expDTO.getExperiencePoints()));
    }

    //@PutMapping("/{playerId}/achievements/global")
    @PutMapping("/players/{playerId}/progression/achievements/global")
    public ResponseEntity<Set<String>> updatePlayerGlobalAchievements(@PathVariable("playerId") long playerId, @RequestBody @Validated AchievementsDTO achievementsDTO) {
        logger.info("[PUT /{}/achievements/global] Received request with body {}", playerId, achievementsDTO);
        return ResponseEntity.ok(playerProgressService.updatePlayerGlobalAchievements(playerId, achievementsDTO.getUnlockedAchievements()));
    }

    //@PutMapping("/{playerId}/{gameMode}/{classUT}/{opponentType}/{opponentDifficulty}")
    @PutMapping("/players/{playerId}/progression/against/{gameMode}/{classUT}/{opponentType}/{opponentDifficulty}")
    public ResponseEntity<UpdateGameProgressDTO> updatePlayerProgressAgainstOpponent(@PathVariable("playerId") long playerId,
                                                                            @PathVariable("gameMode") GameMode gameMode,
                                                                            @PathVariable("classUT") String classUT,
                                                                            @PathVariable("opponentType") OpponentType type,
                                                                            @PathVariable("opponentDifficulty") OpponentDifficulty difficulty,
                                                                            @RequestBody @Validated UpdateGameProgressDTO dto) {
        logger.info("[PUT /{}/{}/{}/{}/{}] Received request with body: {}", playerId, gameMode, classUT, type, difficulty, dto);
        Pair<Boolean, Set<String>> result = playerProgressService.
                updatePlayerGameProgressAgainstOpponent(playerId, gameMode, classUT, type, difficulty,
                        dto.isWon(), dto.getAchievements());
        return ResponseEntity.ok(new UpdateGameProgressDTO(result.getFirst(), result.getSecond()));
    }
}
