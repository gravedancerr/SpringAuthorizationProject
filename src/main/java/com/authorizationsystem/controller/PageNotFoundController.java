package com.authorizationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class PageNotFoundController {
    @GetMapping
    public String getErrorPage() {
        return "page-not-found";
    }
}
