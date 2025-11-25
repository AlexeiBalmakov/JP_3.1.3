package ru.kata.spring.boot_security.demo.initDb;


import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepo;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
public class InitDataBase {

    private final UserService userService;
    private final RoleRepo roleRepository;

    public InitDataBase(UserService userService, RoleRepo roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        roleRepository.save(new Role("ROLE_ADMIN"));
        roleRepository.save(new Role("ROLE_USER"));
        userService.save(new User("admin", "adminL", "admin", "admin", 23, roleRepository.findAll()));
        userService.save(new User("user", "userL", "user", "user", 23, roleRepository.findById((long) 2).stream().collect(Collectors.toList())));
    }
}
