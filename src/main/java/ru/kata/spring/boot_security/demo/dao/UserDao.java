package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void addUser(User user);

    void deleteUser(Long id);

    User getUser(Long id);

    User getUserByName(String name);

    void editUser(User user);
}