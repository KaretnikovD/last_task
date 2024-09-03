package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    public List<User> findAll();

    public void save(User user);

    public void delete(Long id);

    public void update(User user);

    public User getByID(Long id);
}

