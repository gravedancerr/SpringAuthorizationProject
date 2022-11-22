package com.authorizationsystem.controller;

import com.authorizationsystem.model.User;
import com.authorizationsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/administration")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping
    public String getAdminPage(Model model, Principal principal) {
        User user = userService.findByLogin(principal.getName());
        model.addAttribute("usersTable", userService.findAll());
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        return "admin";
    }
}
