package com.codegym.controller;

import com.codegym.service.UserService;
import com.codegym.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    UserService userService = new UserServiceImpl();

    @GetMapping("/")
    public String showListAcount(Model model) {
        model.addAttribute("users", this.userService.findAll());
        return "index";
    }
}
