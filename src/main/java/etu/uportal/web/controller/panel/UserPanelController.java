package etu.uportal.web.controller.panel;

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
@RequestMapping("/panel/user")
public class UserPanelController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size
    ) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);
        Page<User> users = userService.getAll(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("roles", Role.getMap());
        model.addAttribute("users", users);
        model.addAttribute("title", "Список пользователей");
        return "panel/user/index";
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Вход");
        return "panel/user/login";
    }


    @GetMapping("/create")
    public String getCreate(UserCreateDto userCreateDto, Model model) {
        model.addAttribute("title", "Регистрация");
        return "panel/user/create";
    }

    @PostMapping("/create")
    public String postCreate(@ModelAttribute @Valid UserCreateDto userCreateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Регистрация");
            return "panel/user/create";
        }
        userService.registerNewUserAccount(userCreateDto);
        return "redirect:/panel/user/";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(Model model, @PathVariable Long id) {
        User user = userService.getOneById(id);
        model.addAttribute("title", "Обновление пользователя: " + user.getEmail());
        model.addAttribute("userCreateDto", new UserCreateDto(user.getId(), user.getEmail(), "", user.getRoleId()));
        return "panel/user/create";
    }

    @PostMapping("/update/{id}")
    public String postUpdate(@PathVariable Long id, @ModelAttribute @Valid UserCreateDto userCreateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Обновление пользователя: " + userCreateDto.getEmail());
            return "panel/user/create";
        }
        userService.updateById(id, userCreateDto);
        return "redirect:/panel/user/";
    }
}
