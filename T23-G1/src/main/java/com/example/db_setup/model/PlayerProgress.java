package com.example.db_setup.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "players_progresses")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class PlayerProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Player player;

    private int experiencePoints;

    @ElementCollection
    private Set<String> globalAchievements;

    @OneToMany(mappedBy = "playerProgress", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameProgress> progresses = new ArrayList<>();

    public PlayerProgress(Player player) {
        this.player = player;
        this.experiencePoints = 0;
        this.globalAchievements = new HashSet<>();
    }

    @Override
    public String toString() {
        return "PlayerProgress{" +
                "id=" + id +
                ", experiencePoints=" + experiencePoints +
                ", globalAchievements=" + globalAchievements +
                ", progresses=" + progresses +
                '}';
    }
}
