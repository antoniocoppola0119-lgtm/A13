package com.groom.manvsclass.controller;

import com.groom.manvsclass.model.Admin;
import com.groom.manvsclass.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/invite_admins")
    public ResponseEntity<?> inviteAdmins(@RequestBody Admin admin1, @CookieValue(name = "jwt", required = false) String jwt, HttpServletRequest request) {
        return adminService.inviteAdmins(admin1, jwt);
    }

    @PostMapping("/login_with_invitation")
    public ResponseEntity<?> loginWithInvitation(@RequestBody Admin admin1, @CookieValue(name = "jwt", required = false) String jwt, HttpServletRequest request) {
        return adminService.loginWithInvitation(admin1, jwt);
    }

    @GetMapping("/admins/{username}")
    public ResponseEntity<Admin> getAdminByUsername(@PathVariable String username, @CookieValue(name = "jwt", required = false) String jwt) {
        return adminService.getAdminByUsername(username, jwt);
    }



}
