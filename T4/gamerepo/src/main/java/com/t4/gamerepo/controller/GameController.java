package com.t4.gamerepo.controller;

import com.t4.gamerepo.mapper.PlayerResultMapper;
import com.t4.gamerepo.mapper.TurnScoreMapper;
import com.t4.gamerepo.model.Game;
import com.t4.gamerepo.model.Round;
import com.t4.gamerepo.model.Turn;
import com.t4.gamerepo.model.dto.*;
import com.t4.gamerepo.model.PlayerResult;
import com.t4.gamerepo.service.GameService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    private final Logger logger = LoggerFactory.getLogger(GameController.class);


    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Operation(
            summary = "Get a game by its Id",
            description = "Returns a single Game object identified by its unique Id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Game found and returned",
                    content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Game.class)
            )),
            @ApiResponse(responseCode = "404", description = "Game not found"),
    })
    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable("gameId") Long gameId) {
        logger.debug("[GET /games/{}] Received request", gameId);
        Game game = gameService.getGameById(gameId);
        logger.debug("[GET /games/{}] Game returned: {}", gameId, game);
        return ResponseEntity.ok(game);
    }

    @Operation(
            summary = "Get all games associated with a player by ID",
            description = "Returns a list of Game objects in which the specified player appears in the 'players' field"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of games returned successfully or empty if there are no games",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Game.class))
                    )
            )
    })
    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<Game>> getAllPlayerGames(
            @Parameter(name = "playerId", description = "Id of the player", required = true)
            @PathVariable("playerId") Long playerId) {
        logger.debug("[GET /games/{}] Received request", playerId);
        List<Game> games = gameService.getAllPlayerGames(playerId);
        logger.debug("[GET /games/{}] Games returned: {}", playerId, games);
        return ResponseEntity.ok(games);
    }

    @Operation(
            summary = "Get all games",
            description = "Returns a list of all available Game objects"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of games returned successfully or empty if there are no games",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Game.class))
                    )
            )
    })
    @GetMapping("")
    public ResponseEntity<List<Game>> getAllGames() {
        logger.debug("[GET /games] Received request");
        List<Game> games = gameService.getAllGames();
        logger.debug("[GET /games] Games returned: {}", games);
        return ResponseEntity.ok(games);
    }

    @Operation(
            summary = "Create a new Game",
            description = "Returns the created Game object",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "DTO containing details to create a new Game",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreateGameDTO.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "The created Game Object",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Game.class)
                    )
            )
    })
    @PostMapping("")
    public ResponseEntity<Object> createGame(@Validated @RequestBody CreateGameDTO createGameDTO) {
        logger.debug("[POST /games] Received request with body: {}", createGameDTO);
        Game createdGame = gameService.createGame(createGameDTO.getGameMode(), createGameDTO.getPlayers());
        logger.debug("[POST /games] Created game: {}", createdGame);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
    }

    @Operation(
            summary = "Create a new Round for the specified Game",
            description = "Returns the created Round object",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "DTO containing details to create a new Round",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreateRoundDTO.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "The created Round Object",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Game.class)
                    )
            )
    })
    @PostMapping("/{gameId}/rounds")
    public ResponseEntity<Round> startRound(
            @Parameter(name = "gameId", description = "Id of the game", required = true)
            @PathVariable("gameId") Long gameId,
            @Validated @RequestBody CreateRoundDTO createRoundDTO) {
        logger.debug("[POST /games/{}/rounds] Received request with body: {}", gameId, createRoundDTO);
        Round newRound = gameService.startRound(gameId, createRoundDTO.getClassUT(), createRoundDTO.getType(), createRoundDTO.getDifficulty());
        logger.debug("[POST /games/{}/rounds] Round created: {}", gameId, newRound);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRound);
    }

    @Operation(
            summary = "Create a new Turn for the last Round in the specified Game",
            description = "Returns the turnNumber of the created Turn",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "DTO containing details to create a new Turn",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreateTurnDTO.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "The turnNumber of the created Turn",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Integer.class)
                    )
            )
    })
    @PostMapping("/{gameId}/rounds/last/turns")
    public ResponseEntity<Integer> startTurn(
            @Parameter(name = "gameId", description = "Id of the game", required = true)
            @PathVariable("gameId") Long gameId,
            @Validated @RequestBody CreateTurnDTO turnDTO) {
        logger.debug("[POST /games/{}/rounds/last/turns}] Received request with body {}", gameId, turnDTO);
        Turn newTurn = gameService.startTurn(gameId, turnDTO.getPlayerId());
        logger.debug("[POST /games/{}/rounds/last/turns}] Created new turn {}", gameId, newTurn);

        return ResponseEntity.status(HttpStatus.CREATED).body(newTurn.getTurnNumber());
    }

    @Operation(
            summary = "Close the specified turn in the last turn of the specified Game",
            description = "Returns the updated Turn",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "DTO containing the score of the player in the turn",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CloseTurnDTO.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The updated Turn",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Integer.class)
                    )
            )
    })
    @PutMapping("/{gameId}/rounds/last/turns/{turnNumber}")
    public ResponseEntity<Turn> closeTurn(
            @Parameter(name = "gameId", description = "Id of the game", required = true)
            @PathVariable("gameId") Long gameId,
            @Parameter(name = "turnNumber", description = "The number of the turn of the last round to close", required = true)
            @PathVariable("turnNumber") int turnNumber,
            @Validated @RequestBody CloseTurnDTO closeTurnDTO) {
        logger.debug("[PUT /games/{}/rounds/last/turns/{}] Received request with body: {}", gameId, turnNumber, closeTurnDTO);
        Turn closedTurn = gameService.endTurn(gameId, closeTurnDTO.getPlayerId(), turnNumber, TurnScoreMapper.toEntity(closeTurnDTO));
        logger.debug("[PUT /games/{}/rounds/last/turns/{}] Closed turn as: {}", gameId, turnNumber, closedTurn);

        return ResponseEntity.ok(closedTurn);
    }

    @Operation(
            summary = "Close the last round in the specified Game",
            description = "Returns the updated Round"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The updated Round",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Round.class)
                    )
            )
    })
    @PutMapping("/{gameId}/rounds/last")
    public ResponseEntity<Round> closeRound(
            @Parameter(name = "gameId", description = "Id of the game", required = true)
            @PathVariable(value = "gameId") Long gameId) {
        logger.debug("[PUT /games/{}/rounds/last] Received request", gameId);
        Round closedRound = gameService.endRound(gameId);
        logger.debug("[PUT /games/{}/rounds/last] Closed round as", closedRound);
        return ResponseEntity.ok(closedRound);
    }

    @Operation(
            summary = "Close a Game specified by its Id",
            description = "Returns the updated Game",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "DTO containing the score of the players in the Game and if they have won",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CloseGameDTO.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The updated Game",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Game.class)
                    )
            )
    })
    @PutMapping("/{gameId}")
    public ResponseEntity<Game> closeGame(
            @Parameter(name = "gameId", description = "Id of the game", required = true)
            @PathVariable(value = "gameId") Long gameId,
            @Validated @RequestBody CloseGameDTO closeGameDTO) {
        logger.debug("[PUT /games/{}] Received request with body: {}", gameId, closeGameDTO);
        Map<Long, PlayerResult> results = new HashMap<>();
        for (Long playerId : closeGameDTO.getResults().keySet())
            results.put(playerId, PlayerResultMapper.toEntity(closeGameDTO.getResults().get(playerId)));

        Game closedGame = gameService.endGame(gameId, results);
        logger.debug("[PUT /games/{}] Closed game as: {}", gameId, closedGame);
        return ResponseEntity.ok(closedGame);
    }
}
