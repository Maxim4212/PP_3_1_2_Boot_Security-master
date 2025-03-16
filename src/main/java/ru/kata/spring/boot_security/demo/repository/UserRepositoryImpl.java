package ru.kata.spring.boot_security.demo.repository;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByLogin(String login) {
        User user = entityManager.createQuery("select u from User u where u.username =: username", User.class)
                .setParameter("username", login).getSingleResult();
        return user;
    }

    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
    }
}
