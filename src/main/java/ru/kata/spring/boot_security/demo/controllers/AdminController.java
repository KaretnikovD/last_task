package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.servicies.RoleService;
import ru.kata.spring.boot_security.demo.servicies.UserService;



@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String restPage() {
        return "admin";
    }


    @GetMapping("/admin/old")
    public String adminPage (Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("principal", user);
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleService.findAll());
        return "old-admin";
    }

    @PatchMapping("admin/{id}")
    public String updateUser(@ModelAttribute ("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.delete(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/save_new_user")
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }
}
