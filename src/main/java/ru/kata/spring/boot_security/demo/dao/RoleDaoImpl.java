package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role findRole(String role) {
        return entityManager.createQuery("select role from Role role where role.name=:role", Role.class)
                .setParameter("role", role).getSingleResult();
    }

    @Override
    public void deleteRole(Role role) {
        entityManager.remove(role);
    }

    @Override
    public Set<Role> getAllRoles() {
        List<Role> list = entityManager
                .createQuery("select role from Role role", Role.class)
                .getResultList();
        return new HashSet<>(list);
    }

}
