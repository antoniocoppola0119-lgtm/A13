package com.t4.gamerepo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "turns")
@Getter
@Setter
@ToString
public class Turn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "player_id", nullable = false)
    private Long playerId;

    private int turnNumber;

    @Embedded
    private TurnScore score = null;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Timestamp startedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp closedAt = null;
}
