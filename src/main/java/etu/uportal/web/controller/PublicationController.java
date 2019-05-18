package etu.uportal.web.controller;

import etu.uportal.domain.Author;
import etu.uportal.domain.publication.Publication;
import etu.uportal.domain.publication.PublicationAuthor;
import etu.uportal.infrastructure.service.AuthorService;
import etu.uportal.infrastructure.service.PublicationService;
import etu.uportal.web.dto.publication.PublicationCreateDto;
import etu.uportal.web.dto.publication.PublicationListSingleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/publication")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private AuthorService authorService;

    @GetMapping("/")
    public String index(Model model, @
            RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(2);
        Page<Publication> publications = publicationService.getAll(PageRequest.of(currentPage - 1, pageSize));
        List<PublicationListSingleDto> publicationListSingleDtos = publications
                .stream()
                .map(publication -> new PublicationListSingleDto(publication, publicationService))
                .collect(Collectors.toList());

        model.addAttribute("publications", publications);
        model.addAttribute("publicationsList", publicationListSingleDtos);
        model.addAttribute("title", "Список публикаций");
        return "publication/index";
    }

    @GetMapping("/create")
    public String getCreate(PublicationCreateDto publicationCreateDto, Model model) {
        model.addAttribute("title", "Добавление публикации");
        model.addAttribute("authorList", authorService.getAll(PageRequest.of(0, 20)));
        return "publication/create";
    }

    @PostMapping("/create")
    public String postCreate(@ModelAttribute @Valid PublicationCreateDto publicationCreateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Добавление публикации");
            return "publication/create";
        }
        publicationService.create(publicationCreateDto);
        return "redirect:/publication/";
    }
}
