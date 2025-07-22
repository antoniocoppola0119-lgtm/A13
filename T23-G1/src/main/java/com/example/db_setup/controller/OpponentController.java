package com.example.db_setup.controller;

import com.example.db_setup.model.Opponent;
import com.example.db_setup.model.dto.gamification.OpponentDTO;
import com.example.db_setup.service.OpponentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/opponents")
public class OpponentController {

    private final OpponentService opponentService;
    private final Logger logger = LoggerFactory.getLogger(OpponentController.class);


    public OpponentController(OpponentService opponentService) {
        this.opponentService = opponentService;
    }

    @PostMapping("")
    public ResponseEntity<Opponent> addNewOpponent(@RequestBody @Validated OpponentDTO opponentDTO) {
        logger.info("[POST /] Received request to add new opponent with requestBody {}", opponentDTO);
        return ResponseEntity.ok(opponentService.addNewOpponent(opponentDTO.getGameMode(), opponentDTO.getClassUT(),
                opponentDTO.getType(), opponentDTO.getDifficulty()));
    }

    @DeleteMapping("/{classUT}")
    public ResponseEntity<Integer> deleteAllOpponentsForClassUT(@PathVariable("classUT") String classUT) {
        return ResponseEntity.ok(opponentService.deleteAllOpponentsForClassUT(classUT));
    }
}
