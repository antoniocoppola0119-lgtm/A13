package com.example.db_setup.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "game_progresses")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class GameProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @ManyToOne
    @JoinColumn(name = "players_progresses_id")
    private PlayerProgress playerProgress;

    @ManyToOne
    @JoinColumn(name = "opponent_id")
    private Opponent opponent;

    private boolean isWinner = false;

    @ElementCollection
    private Set<String> achievements = new HashSet<>();

    public GameProgress(PlayerProgress playerProgress, Opponent opponent) {
        this.playerProgress = playerProgress;
        this.opponent = opponent;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "Id=" + Id +
                ", opponent=" + opponent +
                ", isWinner=" + isWinner +
                ", achievements=" + achievements +
                '}';
    }
}
