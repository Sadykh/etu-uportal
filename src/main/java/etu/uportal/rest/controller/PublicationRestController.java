package etu.uportal.rest.controller;

import etu.uportal.domain.publication.Publication;
import etu.uportal.infrastructure.service.PublicationService;
import etu.uportal.spring.OffsetLimitPageable;
import etu.uportal.web.dto.author.AuthorCreateDto;
import etu.uportal.web.dto.publication.PublicationCreateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@Api(value = "/api/publication", tags = "Publication")
@RequestMapping("/api/publication")
public class PublicationRestController {

    @Autowired
    private PublicationService publicationService;

    @ApiOperation(value = "Получить список всех публикаций")
    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public Page<PublicationCreateDto> getUser(OffsetLimitPageable pageRequest) {
        Page<Publication> items = publicationService.getAll(pageRequest);
        List<PublicationCreateDto> result = new ArrayList<>();
        items.getContent().forEach(item -> {
            result.add(new PublicationCreateDto(item.getId(), item.getTitle(), item.getIntroText(), item.getPublicationFields(), item.getPublicationAuthors()));
        });
        return new PageImpl<>(result);
    }

    @ApiOperation(value = "Добавить новую публикацию")
    @PreAuthorize("permitAll()")
    @PostMapping("/")
    public PublicationCreateDto addPublication(@RequestBody @Valid PublicationCreateDto publicationCreateDto) {
        return publicationService.create(publicationCreateDto);
    }

    @ApiOperation(value = "Обновление публикации")
    @PreAuthorize("permitAll()")
    @PostMapping("/{id}")
    public PublicationCreateDto updatePublication(@PathVariable Long id, @RequestBody @Valid PublicationCreateDto publicationCreateDto) {
        return publicationService.updateById(id, publicationCreateDto);
    }

    @ApiOperation(value = "Поиск публикаций по названию")
    @PreAuthorize("permitAll()")
    @GetMapping("/search")
    public Page<PublicationCreateDto> searchByTitle(@RequestParam("query") String query) {
        List<Publication> items = publicationService.findByTitle(query);
        List<PublicationCreateDto> result = new ArrayList<>();
        items.forEach(item -> result.add(new PublicationCreateDto(item.getId(), item.getTitle(), item.getIntroText(), item.getPublicationFields(), item.getPublicationAuthors())));
        return new PageImpl<>(result);
    }

    @ApiOperation(value = "Удаление публикации")
    @PreAuthorize("permitAll()")
    @DeleteMapping("/{id}")
    public Boolean remote(@PathVariable Long id) {
        publicationService.removeById(id);
        return true;
    }
}
