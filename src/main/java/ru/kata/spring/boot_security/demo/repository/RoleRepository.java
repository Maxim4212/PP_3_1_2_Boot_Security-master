package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    List<Role> getAllRoles();

    Role getRole(String userRole);

    void addRole(Role role);

    List<Role> findByIds(List<Long> roleIds);

    Optional<Role> findByName(String name);
}
