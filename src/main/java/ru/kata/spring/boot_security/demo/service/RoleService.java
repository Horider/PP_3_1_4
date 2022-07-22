package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.Set;

public interface RoleService {
    void addRole(Role role);

    Role findRole(String role);

    void deleteRole(Role role);

    Set<Role> getAllRoles();
}
