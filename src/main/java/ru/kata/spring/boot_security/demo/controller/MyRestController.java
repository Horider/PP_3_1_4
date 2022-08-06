package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public MyRestController(UserServiceImpl usersService, RoleServiceImpl roleService) {
        this.userService = usersService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/roles")
    public List<Role> showAllRoles() {
        return roleService.findAllRoles();
    }

    @GetMapping("/userinfo")
    public User showUserInfo(Principal principal) {
        return userService.findByName(principal.getName());
    }

    @PostMapping("/users/newuser")
    public User addNewUser(@RequestBody User user) {
        userService.addUser(user);
        return user;
    }

    @PutMapping("/users/edituser")
    public User editUser(@RequestBody User user) {
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {
        userService.removeUserById(id);
        return "User with id " + id + " was deleted.";
    }
}
