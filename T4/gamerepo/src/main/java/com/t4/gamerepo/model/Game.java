package com.t4.gamerepo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import testrobotchallenge.commons.models.opponent.GameMode;
import testrobotchallenge.commons.models.opponent.OpponentDifficulty;
import testrobotchallenge.commons.models.opponent.OpponentType;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "players", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "player_id")
    private List<Long> players;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    @Enumerated(EnumType.STRING)
    private GameMode gameMode;

    @ElementCollection
    @CollectionTable(name = "game_player_results", joinColumns = @JoinColumn(name = "game_id"))
    @MapKeyColumn(name = "player_id")
    private Map<Long, PlayerResult> playerResults = new HashMap<>();

    // Con cascade = CascadeType.ALL delego la gestione nel db dei Round a Game, ovvero se salvo/aggiorno/elimino il
    // genitore lo stesso avviene anche per i figli
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "game_id")
    @OrderBy("roundNumber ASC")
    private List<Round> rounds = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Timestamp startedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp closedAt;

    public Game(GameMode gameMode, List<Long> players) {
        this.gameMode = gameMode;
        this.players = players;
    }

    public void addRound(Round newRound) {
        rounds.add(newRound);
    }

    /*
     * Anche se annotato con @JsonIgnore, Jackson sembra provare a serializzare lo stesso il get per restituire il
     * createdGame nella risposta di createGame(). IN questa fase non sono ancora registrati round, quindi la serializzazione
     * genera un IndexOutOfBound
     */
    @JsonIgnore
    public Round getLastRound() {
        if (rounds == null || rounds.isEmpty()) {
            return null;
        }
        return rounds.get(rounds.size() - 1);
    }

}
