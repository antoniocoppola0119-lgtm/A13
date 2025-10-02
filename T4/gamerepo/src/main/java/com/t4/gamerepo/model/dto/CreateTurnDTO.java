package com.t4.gamerepo.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO usato per la creazione di un nuovo turno.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateTurnDTO {
    /** ID del giocatore che gioca il turno. */
    private Long playerId;
}
