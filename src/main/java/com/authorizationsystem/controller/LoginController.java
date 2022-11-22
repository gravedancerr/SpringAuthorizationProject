package com.authorizationsystem.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class LoginController {
    @GetMapping("/")
    public String getPathDependingOnRole(Authentication auth) {
        if (auth == null) {
            return "login";
        } else if (auth.getAuthorities()
                .contains(new SimpleGrantedAuthority("admin"))) {
            return "redirect:/administration";
        } else if (auth.getAuthorities()
                .contains(new SimpleGrantedAuthority("user"))) {
            return "redirect:/user";
        }
        return "login";
    }

    @GetMapping(value = "/login")
    public String getLoginPage(Model model,
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        String message = null;
        if (Objects.nonNull(error)) {
            message = "Incorrect login or password. Please, try again";
        }
        if (Objects.nonNull(logout)) {
            message = "You've been successfully logged out";
        }
        model.addAttribute("message", message);
        return "login";
    }
}
