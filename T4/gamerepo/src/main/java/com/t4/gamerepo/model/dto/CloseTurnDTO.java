package com.t4.gamerepo.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import testrobotchallenge.commons.models.dto.score.basic.EvosuiteScoreDTO;
import testrobotchallenge.commons.models.dto.score.basic.JacocoScoreDTO;

/**
 * DTO usato per la chiusura (completamento) di un turno.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CloseTurnDTO {
    /** ID del giocatore che ha giocato il turno. */
    private Long playerId;

    /** Punteggio di EvoSuite ottenuto dal giocatore nel turno. */
    private EvosuiteScoreDTO evosuiteScoreDTO;

    /** Punteggio di JaCoCo ottenuto dal giocatore nel turno. */
    private JacocoScoreDTO jacocoScoreDTO;
}
