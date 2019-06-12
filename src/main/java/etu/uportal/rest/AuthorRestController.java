package etu.uportal.rest;

import etu.uportal.domain.author.Author;
import etu.uportal.infrastructure.service.AuthorService;
import etu.uportal.spring.OffsetLimitPageable;
import etu.uportal.web.dto.author.AuthorCreateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Api(value = "/api/author", tags = "Author")
@RequestMapping("/api/author")
public class AuthorRestController {

    @Autowired
    private AuthorService authorService;

    @ApiOperation(value = "Получить список всех авторов")
    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public Page<Author> getUser(OffsetLimitPageable pageRequest) {
        return authorService.getAll(pageRequest);
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
