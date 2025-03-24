package ru.kata.spring.boot_security.demo.repository;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Role getRole(String userRole) {
        return entityManager.createQuery("select r from Role r where r.user_role =: userRole", Role.class)
                .setParameter("userRole", userRole).getSingleResult();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> findByIds(List<Long> roleIds) {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.id IN :roleIds", Role.class)
                .setParameter("roleIds", roleIds)
                .getResultList();
    }

    @Override
    public Optional<Role> findByName(String name) {
        String jpql = "SELECT r FROM Role r WHERE r.user_role = :name";
        TypedQuery<Role> query = entityManager.createQuery(jpql, Role.class);
        query.setParameter("name", name);

        try {
            Role role = query.getSingleResult();
            return Optional.of(role);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
