package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.UserService;
import com.codegym.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {
    UserService userService = new UserServiceImpl();

    @GetMapping("/")
    public String showListAcount(Model model) {
        model.addAttribute("users", this.userService.findAll());
        return "index";
    }

    @GetMapping("/user/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping("/user/create")
    public String save(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        new User().validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "create";
        } else {
            int id = this.userService.findAll().size() + 1;
            user.setId(id);
            this.userService.save(user);
            redirectAttributes.addFlashAttribute("success", "Create new user successfully");
            return "redirect:/";
        }
    }

    @GetMapping("/user/{id}/update")
    public String update(Model model, @PathVariable int id) {
        User user = this.userService.findById(id);
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping("/user/update")
    public String update(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        new User().validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "update";
        } else {
            this.userService.update(user.getId(), user);
            redirectAttributes.addFlashAttribute("success", "Update customer's information successfully");
            return "redirect:/";
        }
    }
    @GetMapping("/user/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        User user = this.userService.findById(id);
        model.addAttribute("user", user);
        return "delete";
    }

    @PostMapping("/user/delete")
    public String delete(@ModelAttribute("user") User user, RedirectAttributes redirect) {
        this.userService.remove(user.getId());
        redirect.addFlashAttribute("success", "Delete successfully");
        return "redirect:/";
    }

    @GetMapping("/user/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("user", this.userService.findById(id));
        return "view";
    }

}
