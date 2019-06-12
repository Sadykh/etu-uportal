package etu.uportal.web.controller.panel;

import etu.uportal.domain.Author;
import etu.uportal.infrastructure.service.AuthorService;
import etu.uportal.web.dto.author.AuthorCreateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/panel/author")
public class AuthorPanelController {

    @Autowired
    private AuthorService authorService;

    private static final Logger log = LoggerFactory.getLogger(AuthorPanelController.class);


    @RequestMapping("/")
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);
        Page<Author> authors = authorService.getAll(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("authors", authors);
        model.addAttribute("title", "Список авторов");
        return "panel/author/index";
    }

    @GetMapping("/create")
    public String getCreate(AuthorCreateDto authorCreateDto, Model model) {
        model.addAttribute("title", "Добавление автора");
        return "panel/author/create";
    }


    @PostMapping("/create")
    public String postCreate(@ModelAttribute @Valid AuthorCreateDto authorCreateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Добавление автора");
            return "panel/author/create";
        }
        authorService.create(authorCreateDto);
        return "redirect:/panel/author/";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(Model model, @PathVariable Long id) {
        Author author = authorService.getOneById(id);
        model.addAttribute("title", "Обновление автора: " + author.getLastName());
        model.addAttribute("authorCreateDto", new AuthorCreateDto(author.getId(), author.getFirstName(),
                author.getLastName(), author.getMiddleName(), author.getFirstNameEn(), author.getLastNameEn(),
                author.getMiddleNameEn(), author.getAuthorFields()));
        return "panel/author/create";
    }

    @PostMapping("/update/{id}")
    public String postUpdate(@PathVariable Long id, @ModelAttribute @Valid AuthorCreateDto authorCreateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Обновление автора: " + authorCreateDto.getLastName());
            return "panel/author/create";
        }
        authorService.updateById(id, authorCreateDto);
        return "redirect:/panel/author/";
    }

}
