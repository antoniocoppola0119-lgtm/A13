package com.groom.manvsclass.controller;

import com.groom.manvsclass.model.Achievement;
import com.groom.manvsclass.model.Statistic;
import com.groom.manvsclass.service.AchievementService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
public class AchievementController {

    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping("/achievements/list")
    @ResponseBody
    public ResponseEntity<?> listAchievements() {
        return achievementService.listAchievements();
    }

    @PostMapping("/achievements")
    public Object createAchievement(Achievement achievement, @CookieValue(name = "jwt", required = false) String jwt, HttpServletRequest request) {
        return achievementService.createAchievement(achievement, jwt, request);
    }

    @GetMapping("/statistics/list")
    @ResponseBody
    public ResponseEntity<?> listStatistics() {
        return achievementService.listStatistics();
    }

    @PostMapping("/statistics")
    public Object createStatistic(Statistic statistic, @CookieValue(name = "jwt", required = false) String jwt, HttpServletRequest request) {
        return achievementService.createStatistic(statistic, jwt, request);
    }

    @DeleteMapping("/statistics/{Id}")
    public Object deleteStatistic(@PathVariable("Id") String Id, @CookieValue(name = "jwt", required = false) String jwt, HttpServletRequest request) {
        return achievementService.deleteStatistic(Id, jwt, request);
    }
}
