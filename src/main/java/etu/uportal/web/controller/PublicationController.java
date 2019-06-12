package etu.uportal.web.controller;

import etu.uportal.domain.publication.Publication;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("publication")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private AuthorService authorService;

    @GetMapping("/")
    public String index(Model model, @
            RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);
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
}
