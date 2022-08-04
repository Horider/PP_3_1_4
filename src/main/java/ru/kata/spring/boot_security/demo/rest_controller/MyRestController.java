package ru.kata.spring.boot_security.demo.rest_controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.AppService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    private final AppService appService;

    @Autowired
    public MyRestController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return appService.getAllUsers();
    }

    @GetMapping("/roles")
    public List<Role> showAllRoles() {
        return appService.listRoles();
    }

    @GetMapping("/userinfo")
    public User showUserInfo(Principal principal) {
        return appService.getUserByEmail(principal.getName());
    }

    @PostMapping("/users/newuser")
    public User addNewUser(@RequestBody User user) {
        appService.saveUser(user);
        return user;
    }

    @PutMapping("/users/edituser")
    public User editUser(@RequestBody User user) {
        appService.saveRole(user);
        return user;
    }

    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {
        appService.removeUser(id);
        return "User with id " + id + " was deleted.";
    }
}
