package com.authorizationsystem.controller;

import com.authorizationsystem.dto.UserDto;
import com.authorizationsystem.model.User;
import com.authorizationsystem.service.RoleService;
import com.authorizationsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public String getUserToRegister(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping
    public String registerUser(Model model, @Valid UserDto user,
            @RequestParam("login") String login,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            BindingResult bindingResult) throws IOException {
        if (userService.findByLogin(login) != null) {
            model.addAttribute("duplicatedLogin",
                    "User with such login already exists");
            return "registration";
        }
        if (userService.findByEmail(email) != null) {
            model.addAttribute("duplicatedEmail",
                    "User with such email already registered");
            return "registration";
        }
        if (!userService.checkBirthday(new User(user))) {
            model.addAttribute("invalidBirthday", "Date of birth is invalid");
            model.addAttribute("roles", roleService.findAll());
            return "registration";
        }
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(roleService.findByName("user"));
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.create(new User(user));
        return "redirect:/login";
    }
}
