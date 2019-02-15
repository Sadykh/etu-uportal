package etu.uportal.web.controller;

import etu.uportal.Application;
import etu.uportal.infrastructure.service.UserService;
import etu.uportal.web.dto.user.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Список пользователей");
        return "user/index";
    }


    @GetMapping("/create")
    public String getCreate(Model model) {
        model.addAttribute("title", "Регистрация");
        model.addAttribute("user", new UserDto());
        return "user/create";
    }

    @PostMapping("/create")
    public String postCreate(@ModelAttribute UserDto user, Model model) {
        model.addAttribute("title", "Регистрация");
        model.addAttribute("user", new UserDto());
        userService.registerNewUserAccount(user);
        return "user/create";
    }
}
