package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserRepository {
    User getUserByLogin(String login);

    void saveUser(User user);

    List<User> getAllUsers();

    void deleteUserById(Long id);

    User findById(Long id);
}
