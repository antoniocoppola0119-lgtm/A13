package com.groom.manvsclass.controller.view;

import com.groom.manvsclass.service.JwtService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
public class TeamViewController {

    private final JwtService jwtService;

    public TeamViewController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/teams")
    @ResponseBody
    public ModelAndView showGestioneTeams(HttpServletRequest request, @CookieValue(name = "jwt", required = false) String jwt){
        if (jwtService.isJwtValid(jwt)) {return new ModelAndView("gestione_teams");}
        return new ModelAndView("redirect:/loginAdmin");
    }

    @GetMapping("/visualizzaTeam/{idTeam}")
    @ResponseBody
    public ModelAndView showTeamDetails(HttpServletRequest request, @CookieValue(name = "jwt", required = false) String jwt){
        if (jwtService.isJwtValid(jwt)) {return new ModelAndView("teamDetail");}
        return new ModelAndView("redirect:/loginAdmin");
    }
}
