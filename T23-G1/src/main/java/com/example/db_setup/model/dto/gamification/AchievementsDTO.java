package com.example.db_setup.model.dto.gamification;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class AchievementsDTO {
    Set<String> unlockedAchievements;
}
