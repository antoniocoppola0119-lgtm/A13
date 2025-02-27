package com.groom.manvsclass.controller.view;

import com.groom.manvsclass.service.AchievementService;
import com.groom.manvsclass.service.JwtService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@Controller
public class AdminViewController {

    private final JwtService jwtService;

    public AdminViewController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/loginAdmin")
    public ModelAndView showLoginForm(HttpServletRequest request, @CookieValue(name = "jwt", required = false) String jwt) {
        if (jwtService.isJwtValid(jwt)) return new ModelAndView("redirect:/home_adm");
        return new ModelAndView("login_admin");
    }

    @GetMapping("/registraAdmin")
    public ModelAndView showRegistraAdmin() {
        return new ModelAndView("registraAdmin");
    }

    @GetMapping("/home_adm")
    public ModelAndView showHomeAdmin(HttpServletRequest request, @CookieValue(name = "jwt", required = false) String jwt) {
        if (jwtService.isJwtValid(jwt)) return new ModelAndView("home_adm");
        return new ModelAndView("redirect:/loginAdmin");
    }

    @GetMapping("/info")
    public ModelAndView showInfo(HttpServletRequest request, @CookieValue(name = "jwt", required = false) String jwt) {
        if (jwtService.isJwtValid(jwt)) return new ModelAndView("info");
        return new ModelAndView("login_admin");
    }

    @GetMapping("/player")
    public ModelAndView showPlayer(HttpServletRequest request, @CookieValue(name = "jwt", required = false) String jwt) {
        if(jwtService.isJwtValid(jwt)) return new ModelAndView("player");
        return new ModelAndView("login_admin");
    }

    @GetMapping("/invite_admins")
    @ResponseBody
    public ModelAndView showInviteAdmins(HttpServletRequest request, @CookieValue(name = "jwt", required = false) String jwt) {
        if (jwtService.isJwtValid(jwt)) return new ModelAndView("invite_admins");
        return new ModelAndView("login_admin");
    }

    @GetMapping("/logout_admin")
    public ModelAndView logoutAdmin(HttpServletResponse response) {
        // Crea un cookie JWT nullo per invalidare il token
        Cookie jwtTokenCookie = new Cookie("jwt", null);
        jwtTokenCookie.setMaxAge(0); // Imposta l'età massima a 0 per eliminarlo
        response.addCookie(jwtTokenCookie);
        return new ModelAndView("login_admin");
    }

    @GetMapping("/login_with_invitation")
    @ResponseBody
    public ModelAndView showLoginWithInvitation(HttpServletRequest request, @CookieValue(name = "jwt", required = false) String jwt) {
        //if (jwtService.isJwtValid(jwt)) return new ModelAndView("redirect:/login_admin");
        return new ModelAndView("login_with_invitation");
    }

    @GetMapping("/password_change_admin")
    public ModelAndView showChangePswAdminForm(HttpServletRequest request, @CookieValue(name = "jwt", required = false) String jwt) {
        //if (jwtService.isJwtValid(jwt)) return new ModelAndView("redirect:/login_admin");
        return new ModelAndView("password_change_admin");
    }

    @GetMapping("/password_reset_admin")
    public ModelAndView showResetPswAdminForm(HttpServletRequest request, @CookieValue(name = "jwt", required = false) String jwt) {
        //if (jwtService.isJwtValid(jwt)) return new ModelAndView("redirect:/login_admin");
        return new ModelAndView("password_reset_admin");
    }
}
