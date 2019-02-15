package etu.uportal.web.controller;

import etu.uportal.web.dto.author.AuthorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("author")
public class AuthorController {
    private static final Logger log = LoggerFactory.getLogger(AuthorController.class);

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Список авторов");
        return "author/index";
    }

    @GetMapping("/create")
    public String getCreate(Model model) {
        model.addAttribute("title", "Добавление автора");
        model.addAttribute("author", new AuthorDto());
        return "author/create";
    }

}
