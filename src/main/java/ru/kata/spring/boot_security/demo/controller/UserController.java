package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userRepository;

    public UserController(UserService userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public String getUser(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("userAuth", user);
        model.addAttribute("users", user);
        return "index";
    }
}
