package com.authorizationsystem.controller;

import com.authorizationsystem.dto.EditUserDto;
import com.authorizationsystem.model.User;
import com.authorizationsystem.service.RoleService;
import com.authorizationsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/edit")
@RequiredArgsConstructor
public class EditUserController {
    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public String getUserToEdit(Model model, @RequestParam Long id) {
        User userById = userService.findById(id);
        if (userById == null) {
            return "id-error";
        }
        model.addAttribute("user", userById);
        model.addAttribute("roles", roleService.findAll());
        return "edit";
    }

    @PostMapping
    public String editUser(Model model, @Valid @ModelAttribute("user") EditUserDto user,
            BindingResult bindingResult,
            @RequestParam("password") String newPassword,
            @RequestParam("prev_password") String previousPassword,
            @RequestParam("name") String roleName) {
        user.setId(userService.findByLogin(user.getLogin()).getId());
        user.setRole(roleService.findByName(roleName));
        if (newPassword == null || newPassword.isEmpty()) {
            user.setPassword(previousPassword);
        } else {
            user.setPassword(passwordEncoder.encode(newPassword));
        }
        if (!userService.checkBirthday(new User(user))) {
            model.addAttribute("invalidBirthday", "Date of birth is invalid");
            model.addAttribute("roles", roleService.findAll());
            return "edit";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            return "edit";
        }
        userService.update(new User(user));
        return "redirect:/administration";
    }
}
