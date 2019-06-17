package etu.uportal.web.controller.panel;

import etu.uportal.domain.publication.Publication;
import etu.uportal.infrastructure.service.AuthorService;
import etu.uportal.infrastructure.service.PublicationService;
import etu.uportal.web.dto.publication.PublicationCreateDto;
import etu.uportal.web.dto.publication.PublicationFieldDto;
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
@RequestMapping("panel/publication")
public class PublicationPanelController {

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
        return "panel/publication/index";
    }

    @GetMapping("/create")
    public String getCreate(PublicationCreateDto publicationCreateDto, Model model) {
        model.addAttribute("title", "Добавление публикации");
        List<PublicationFieldDto> publicationFieldDto = new ArrayList<>();
        publicationFieldDto.add(new PublicationFieldDto());
        publicationFieldDto.add(new PublicationFieldDto());
        publicationFieldDto.add(new PublicationFieldDto());
        publicationCreateDto.setPublicationFields(publicationFieldDto);
        model.addAttribute("authorList", authorService.getAll(PageRequest.of(0, 20)));
        return "panel/publication/create";
    }

    @PostMapping("/create")
    public String postCreate(@ModelAttribute @Valid PublicationCreateDto publicationCreateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Добавление публикации");
            return "panel/publication/create";
        }
        publicationService.create(publicationCreateDto);
        return "redirect:/panel/publication/";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(Model model, @PathVariable Long id) {
        Publication publication = publicationService.getOneById(id);
        model.addAttribute("title", "Обновление публикации: " + publication.getTitle());
        model.addAttribute("authorList", authorService.getAll(PageRequest.of(0, 20)));
        model.addAttribute("publicationCreateDto",
                new PublicationCreateDto(
                        publication.getId(),
                        publication.getTitle(),
                        publication.getIntroText(),
                        publication.getPublicationFields(),
                        publication.getPublicationAuthors()
                )
        );
        return "panel/publication/create";
    }

    @PostMapping("/update/{id}")
    public String postUpdate(@PathVariable Long id, @ModelAttribute @Valid PublicationCreateDto publicationCreateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("authorList", authorService.getAll(PageRequest.of(0, 20)));
            model.addAttribute("title", "Обновление публикации: " + publicationCreateDto.getTitle());
            return "panel/publication/create";
        }
        publicationService.updateById(id, publicationCreateDto);
        return "redirect:/panel/publication/";
    }

    @GetMapping("/delete/{id}")
    public String removePublication(@PathVariable Long id) {
        publicationService.removeById(id);
        return "redirect:/panel/publication/";
    }

}
