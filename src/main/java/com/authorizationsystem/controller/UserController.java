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
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public String getUserPage(Model model, Principal principal) {
        User user = userService.findByLogin(principal.getName());
        model.addAttribute("firstName", user.getFirstName());
        return "user";
    }
}
