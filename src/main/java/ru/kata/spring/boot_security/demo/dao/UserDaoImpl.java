package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser (Long id) {
        User user = entityManager.find(User.class, id);
        if (null == user) {
            throw new NullPointerException("Пользователь не найден");
        } else {
            entityManager.remove(user);
        }
        entityManager.flush();
    }

    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByName(String name) {
        return entityManager.createQuery("select user from User user where user.name=:name", User.class)
                .setParameter("name", name).getSingleResult();
    }

    @Override
    public void editUser(User userUpdate) {
        entityManager.merge(userUpdate);
        entityManager.flush();
    }
}

