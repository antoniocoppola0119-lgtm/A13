package com.groom.manvsclass.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
@RequestMapping("team")
public class TeamViewController {

    @GetMapping("/main")
    @ResponseBody
    public ModelAndView showMain(){
        return new ModelAndView("/teams/team_main");
    }

    @GetMapping("/details/{teamId}")
    @ResponseBody
    public ModelAndView showDetails(){
        return new ModelAndView("/teams/team_details");
    }
}
