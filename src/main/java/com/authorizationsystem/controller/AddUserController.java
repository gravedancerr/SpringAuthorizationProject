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

@Controller
@RequestMapping("/add")
@RequiredArgsConstructor
public class AddUserController {
    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public String getUserToAdd(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAll());
        return "add";
    }

    @PostMapping
    public String addUser(Model model, @Valid UserDto user,
            BindingResult bindingResult,
            @RequestParam("login") String login,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("name") String roleName) {
        user.setRole(roleService.findByName(roleName));
        user.setPassword(passwordEncoder.encode(password));
        if (userService.findByLogin(login) != null) {
            model.addAttribute("duplicatedLogin",
                    "User with such login already exists");
            model.addAttribute("roles", roleService.findAll());
            return "add";
        }
        if (userService.findByEmail(email) != null) {
            model.addAttribute("duplicatedEmail",
                    "User with such email already registered");
            return "add";
        }
        if (!userService.checkBirthday(new User(user))) {
            model.addAttribute("invalidBirthday", "Date of birth is invalid");
            model.addAttribute("roles", roleService.findAll());
            return "add";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            return "add";
        }
        userService.create(new User(user));
        return "redirect:/administration";
    }
}
