package com.groom.manvsclass.controller.view;

import com.groom.manvsclass.service.AchievementService;
import com.groom.manvsclass.service.JwtService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
public class AchievementViewController {

    private final AchievementService achievementService;

    public AchievementViewController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping("/achievements")
    public ModelAndView showAchievementsPage(HttpServletRequest request, @CookieValue(name = "jwt", required = false) String jwt) {
        System.out.println("(GET /achievements) Token JWT valido?");
        return achievementService.showAchievement(request, jwt);

    }
}
