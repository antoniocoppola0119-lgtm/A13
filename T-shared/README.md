# T-shared Module
`T_shared` is a shared module of the Test Robot Challenge project. It contains reusable components, classes, and utilities 
for other modules, such as common DTOs, internal domain classes, score helpers, enumerations, and constants.

This module does not provide standalone business logic but offers common tools to ensure consistency and maintainability 
across the project.

The `T_shared` module aims to:

* Centralize shared classes to reduce duplication
* Provide a consistent model for scores, metrics, and DTOs
* Facilitate integration between microservices

## Current Main Contents
| Component                     | Package / Class                                 | Purpose                                                                                                                                                                   |
|-------------------------------|-------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **DTOs for coverage results** | `testrobotchallenge.commons.models.dto.score.*` | DTO for communication between modules or with external clients. Includes: `JacocoCoverageDTO`, `EvosuiteCoverageDTO`, `JacocoScoreDTO`, `EvosuiteScoreDTO`, `CoverageDTO` |
| **Internal score classes**    | `testrobotchallenge.commons.models.score.*`     | Internal value objects representing coverage and scoring. Includes: `JacocoScore`, `EvosuiteScore`, `Coverage`                                                            |
| **Enumerations**              | `testrobotchallenge.commons.models.opponent.*`  | Represent types of opponents, game modes, difficulty levels and system roles. Includes: `OpponentType`, `OpponentDifficulty`, `GameMode`, `Role`                          |
| **Utilities**                 | `testrobotchallenge.commons.util.ExtractScore`  | Methods to parse coverage results from EvoSuite CSV or JaCoCo XML files (`fromEvosuite`, `fromJacoco`)                                                                    |
| **Mappers**                   | `testrobotchallenge.commons.mappers`            | "Utility classes for easily mapping between internal coverage classes and their corresponding coverage DTOs.                                                                              |

## How to Use

1. Add the `T_shared` module as a dependency in modules that need DTOs or shared classes.
2. Import the necessary classes or enumerations:
   ```java
   import testrobotchallenge.commons.models.dto.score.JacocoCoverageDTO;
   import testrobotchallenge.commons.models.score.Coverage;
   import testrobotchallenge.commons.util.ExtractScore;
   ```

## Notes
* All **score** classes (`JacocoScore`, `EvosuiteScore`, `Coverage`) are designed as **internal value objects**, not DTOs for external exposure.
* All DTOs are contained in `models.dto.*` and are intended for communication between modules or with external clients.
