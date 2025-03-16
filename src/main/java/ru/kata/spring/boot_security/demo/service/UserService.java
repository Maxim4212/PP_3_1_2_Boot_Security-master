package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserService {
    User getUserByLogin(String login);

    void saveUser(User user);

    UserDetails loadUserByUsername(String login);
}
