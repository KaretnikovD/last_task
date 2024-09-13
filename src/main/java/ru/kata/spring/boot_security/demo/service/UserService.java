package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.controller.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    public List<User> findAll();

    public User save(User user);

    public void delete(Long id);

    public User update(User user);

    public User getByID(Long id);
}

