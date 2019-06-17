package etu.uportal.web.controller;

import etu.uportal.domain.author.Author;
import etu.uportal.infrastructure.service.AuthorService;
import etu.uportal.infrastructure.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Optional;


@Controller
@RequestMapping("author")
public class AuthorController {

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private AuthorService authorService;

    @GetMapping("/")
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);
        Page<Author> authors = authorService.getAll(PageRequest.of(currentPage - 1, pageSize));
        HashMap<Long, Integer> qtyPublicationsByAuthor = new HashMap<>();
        authors.getContent().forEach(item -> {
            qtyPublicationsByAuthor.put(item.getId(), publicationService.getQtyPublicationsByAuthor(item));
        });
        model.addAttribute("qtyPublicationsByAuthor", qtyPublicationsByAuthor);
        model.addAttribute("authors", authors);
        model.addAttribute("title", "Список авторов");
        return "author/index";
    }
}
