package ru.kata.spring.boot_security.demo.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Collection;

@RestController
@RequestMapping("/rest")
public class RESTController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public ResponseEntity<User> getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        return new ResponseEntity<>(principal, HttpStatus.ACCEPTED);
    }

    @GetMapping("/users")
    public Collection<User> showUsers() {
        return userService.findAll();
    }

    @GetMapping("/roles")
    public Collection<Role> showRoles() {
        return roleService.findAll();
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getByID(id), HttpStatus.ACCEPTED);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@Valid @RequestBody User user) {
        User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.ACCEPTED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @Valid @RequestBody User user) {
        user.setId(id);
        User updatedUser = userService.update(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
