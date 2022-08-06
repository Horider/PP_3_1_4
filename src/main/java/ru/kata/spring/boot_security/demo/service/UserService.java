package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void editUser(User user);

    List<User> findAllUsers();

    public void removeUserById(long id);

    public User findUserById(long id);

    public void updateUser(User user);

    public User findByName(String email);
}