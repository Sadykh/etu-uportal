package etu.uportal.rest;

import etu.uportal.domain.Author;
import etu.uportal.domain.User;
import etu.uportal.infrastructure.service.AuthorService;
import etu.uportal.infrastructure.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Author> getUser() {
        return authorService.getAll();
    }
}
