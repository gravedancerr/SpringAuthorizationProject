package com.authorizationsystem.controller;

import com.authorizationsystem.model.User;
import com.authorizationsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/delete")
@RequiredArgsConstructor
public class DeleteUserController {
    private final UserService userService;

    @GetMapping
    public String getUserToDelete(@RequestParam Long id) {
        User userById = userService.findById(id);
        userService.remove(userById);
        return "redirect:/administration";
    }
}
