package etu.uportal.web.controller;

import etu.uportal.domain.Author;
import etu.uportal.domain.Role;
import etu.uportal.infrastructure.service.AuthorService;
import etu.uportal.web.dto.author.AuthorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    private static final Logger log = LoggerFactory.getLogger(AuthorController.class);


    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Author> authors = authorService.getAll();
        model.addAttribute("roles", Role.getMap());
        model.addAttribute("authors", authors);
        model.addAttribute("title", "Список авторов");
        return "author/index";
    }

    @GetMapping("/create")
    public String getCreate(AuthorDto authorDto, Model model) {
        model.addAttribute("title", "Добавление автора");
        return "author/create";
    }


    @PostMapping("/create")
    public String postCreate(@ModelAttribute @Valid AuthorDto authorDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Добавление автора");
            return "author/create";
        }
        authorService.create(authorDto);
        return "redirect:/author/";
    }

}
