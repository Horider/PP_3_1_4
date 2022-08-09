package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Component
public class PostConstructInit {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public PostConstructInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

//    Загружаю в базу дефолтный набор юзеров
    @PostConstruct
    public void init() {
        List<User> users = userService.findAllUsers();

        if (users.isEmpty()) {
            Role admin = new Role("ROLE_ADMIN");
            Role user = new Role("ROLE_USER");
            Collection<Role> adminRole = new HashSet<>();
            Collection<Role> userRole = new HashSet<>();
            Collection<Role> anyRole = new HashSet<>();
            adminRole.add(admin);
            userRole.add(user);
            anyRole.add(admin);
            anyRole.add(user);
            roleService.saveRole(admin);
            roleService.saveRole(user);


            for (User user1 : Arrays.asList(
                    new User("Maha", "Smirnova", 33, "admin@mail.ru", "admin@mail.ru", adminRole),
                    new User("Misha", "Krokodilov", 24, "user@mail.ru", "user@mail.ru", userRole),
                    new User("Dima", "Borisov", 18, "dimab@mail.ru", "dimab@mail.ru", userRole),
                    new User("Vasya", "Pupkin", 16, "vasyap@mail.ru", "vasyap@mail.ru", userRole),
                    new User("Kostya", "Gradov", 52, "kostyag@mail.ru", "kostyag@mail.ru", anyRole))) {
                userService.addUser(user1);
            }
        }
    }
}
