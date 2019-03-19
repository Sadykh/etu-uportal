package etu.uportal.web.controller;

import etu.uportal.domain.Role;
import etu.uportal.domain.User;
import etu.uportal.infrastructure.service.UserService;
import etu.uportal.spring.OffsetLimitPageable;
import etu.uportal.web.dto.user.UserCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model, OffsetLimitPageable pageRequest) {
        Page<User> users = userService.getAll(pageRequest);
        model.addAttribute("roles", Role.getMap());
        model.addAttribute("users", users.getContent());
        model.addAttribute("title", "Список пользователей");
        return "user/index";
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Вход");
        return "user/login";
    }


    @GetMapping("/create")
    public String getCreate(UserCreateDto userCreateDto, Model model) {
        model.addAttribute("title", "Регистрация");
        return "user/create";
    }

    @PostMapping("/create")
    public String postCreate(@ModelAttribute @Valid UserCreateDto userCreateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Регистрация");
            return "user/create";
        }
        userService.registerNewUserAccount(userCreateDto);
        return "redirect:/user/";
    }
}
