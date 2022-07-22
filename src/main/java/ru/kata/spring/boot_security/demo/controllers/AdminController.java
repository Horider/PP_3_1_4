package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = {"/"})
    public String allUsers(Model model) {
        List<User> AllUsers = userService.getAllUsers();
        model.addAttribute("allUsers", AllUsers);
        return "admin";
    }

    @PostMapping("/remove/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }

    @GetMapping(value = {"/add"})
    public String addUser(@ModelAttribute("newUser") User user, Model model) {
        model.addAttribute("userRole", roleService.findRole("ROLE_USER"));
        model.addAttribute("adminRole", roleService.findRole("ROLE_ADMIN"));
        return "addUser";
    }

    @PostMapping("/add")
    public String createNUser(@ModelAttribute("user") User user,
                              @RequestParam(value = "role") String[] roles) {
        userService.addUser(user, roles);
        return "redirect:/admin/";
    }

    @GetMapping(value = {"/edit/{id}"})
    public String editNewUserGet(Model model, @PathVariable(value = "id") Long id) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, User user, Model model) {
        userService.editUser(user);
        return "redirect:/admin/";
    }

}
