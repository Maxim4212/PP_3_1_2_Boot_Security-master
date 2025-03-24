package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    User getUserByLogin(String login);

    void saveUser(User user);

    List<User> getAllUsers();

    void deleteUserById(Long id);

    User getUserById(Long id);
}
