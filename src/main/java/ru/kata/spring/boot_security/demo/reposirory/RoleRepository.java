package ru.kata.spring.boot_security.demo.reposirory;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Collection;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Collection<Role> findByRole(String role);
}
