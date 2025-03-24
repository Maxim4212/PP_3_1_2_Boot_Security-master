package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleRepository.getAllRoles();
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRole(String userRole) {
        return roleRepository.getRole(userRole);
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleRepository.addRole(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getRolesByIds(List<Long> roleIds) {
        return roleRepository.findByIds(roleIds);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
