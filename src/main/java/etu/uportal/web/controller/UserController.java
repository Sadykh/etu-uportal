package etu.uportal.web.controller;

import etu.uportal.domain.Role;
import etu.uportal.domain.User;
import etu.uportal.infrastructure.service.UserService;
import etu.uportal.web.dto.user.UserCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size
    ) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(2);
        Page<User> users = userService.getAll(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("roles", Role.getMap());
        model.addAttribute("users", users);
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
