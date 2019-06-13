package etu.uportal.rest.controller;

import etu.uportal.domain.author.Author;
import etu.uportal.domain.publication.Publication;
import etu.uportal.infrastructure.service.AuthorService;
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
@Api(value = "/api/author", tags = "Author")
@RequestMapping("/api/author")
public class AuthorRestController {

    @Autowired
    private AuthorService authorService;

    @ApiOperation(value = "Получить список всех авторов")
    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public Page<AuthorCreateDto> getUser(OffsetLimitPageable pageRequest) {
        Page<Author> items = authorService.getAll(pageRequest);
        List<AuthorCreateDto> result = new ArrayList<>();
        items.getContent().forEach(item -> {
            result.add(new AuthorCreateDto(item.getId(), item.getFirstName(), item.getLastName(), item.getMiddleName(), item.getFirstNameEn(), item.getLastNameEn(), item.getMiddleNameEn(), item.getAuthorFields()));
        });
        return new PageImpl<>(result);
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
}