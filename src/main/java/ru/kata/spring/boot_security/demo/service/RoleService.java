package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRole(String userRole);

    void addRole(Role role);

    List<Role> getRolesByIds(List<Long> roleIds);

    Optional<Role> findByName(String name);
}
