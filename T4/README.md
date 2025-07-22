# T4 Service - Game Repository
The T4 service is responsible for tracking and maintaining all information related to games played, with related victories and defeats.

## Data model and Rest endpoints
Games are managed using the following main entities:
- **Game**: describes an ongoing or completed game for a specific player;
- **Round**: represents a round within a game;
- **Turn**: represents a turn within a round.

The available REST endpoints are documented in the accompanying OpenAPI specification referenced in this README.

![gamerepo_er.png](documentation/gamerepo_er.png)

A **Game** entity is created when a new match begins. It includes:

1. The selected game mode;
2. One or more players associated with the match, allowing support for both single-player and multiplayer modes;
3. The final results for each player, including their scores and whether they won the game;
4. The current status of the game (e.g., created, in progress, etc.).

A Game can consist of one or more **Rounds**. Each **Round** specifies:

1. The class under test selected for the round;
2. The opponent type chosen among those available for that class;
3. The difficulty level of the chosen opponent;
4. A sequential number starting from 1, representing the unique index of the round within the associated game.

The class, opponent type, and difficulty are stored within each round to support game modes where the opponent may vary between rounds.

A Game can consist of one or more **Turns**. Each **Turn** records:

1. The ID of the player who took the turn;
2. The result achieved by the player, in terms of both JaCoCo and EvoSuite metrics;
3. A sequential number starting from 1, representing the unique index of the turn within the associated round.




