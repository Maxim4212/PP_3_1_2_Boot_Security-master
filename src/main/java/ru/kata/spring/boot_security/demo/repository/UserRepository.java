package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.model.User;

public interface UserRepository {
    User getUserByLogin(String login);

    void saveUser(User user);
}
