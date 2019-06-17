package etu.uportal.rest.controller;

import etu.uportal.domain.author.Author;
import etu.uportal.infrastructure.service.AuthorService;
import etu.uportal.infrastructure.service.PublicationService;
import etu.uportal.rest.dto.author.AuthorInfo;
import etu.uportal.rest.dto.author.AuthorView;
import etu.uportal.spring.OffsetLimitPageable;
import etu.uportal.web.dto.author.AuthorCreateDto;
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
@Api(value = "/api/author", tags = "Author")
@RequestMapping("/api/author")
public class AuthorRestController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PublicationService publicationService;

    @ApiOperation(value = "Получить список всех авторов")
    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public Page<AuthorInfo> getUser(OffsetLimitPageable pageRequest) {
        Page<Author> items = authorService.getAll(pageRequest);
        List<AuthorInfo> result = new ArrayList<>();
        items.getContent().forEach(item -> {
            result.add(new AuthorInfo(item.getId(), item.getFirstName(), item.getLastName(), item.getMiddleName(), item.getFirstNameEn(), item.getLastNameEn(), item.getMiddleNameEn(), publicationService.getQtyPublicationsByAuthor(item)));
        });
        return new PageImpl<>(result);
    }

    @ApiOperation(value = "Получить информацию по автору")
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public AuthorView getAuthor(@PathVariable Long id) {
        Author item = authorService.getOneById(id);
        AuthorView result = new AuthorView(item.getId(), item.getFirstName(), item.getLastName(), item.getMiddleName(), item.getFirstNameEn(), item.getLastNameEn(), item.getMiddleNameEn(), publicationService.getQtyPublicationsByAuthor(item));
        result.setAuthorFields(item.getAuthorFields());
        result.setPublications(publicationService.getPublicationsByAuthor(item));
        return result;
    }

    @ApiOperation(value = "Добавить нового автора")
    @PreAuthorize("permitAll()")
    @PostMapping("/")
    public AuthorCreateDto addAuthor(@RequestBody @Valid AuthorCreateDto authorCreateDto) {
        return authorService.create(authorCreateDto);
    }

    @ApiOperation(value = "Обновление автора")
    @PreAuthorize("permitAll()")
    @PostMapping("/{id}")
    public AuthorCreateDto updateAuthor(@PathVariable Long id, @RequestBody @Valid AuthorCreateDto authorCreateDto) {
        return authorService.updateById(id, authorCreateDto);
    }

    @ApiOperation(value = "Удаление автора")
    @PreAuthorize("permitAll()")
    @DeleteMapping("/{id}")
    public AuthorCreateDto deleteAuthor(@PathVariable Long id) {
        authorService.removeAuthorById(id);
        return new AuthorCreateDto();
    }
}
