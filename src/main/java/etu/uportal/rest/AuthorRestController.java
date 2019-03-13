package etu.uportal.rest;

import etu.uportal.domain.Author;
import etu.uportal.domain.User;
import etu.uportal.infrastructure.service.AuthorService;
import etu.uportal.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/author")
public class AuthorRestController {

    @Autowired
    private AuthorService authorService;

    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public Iterable<Author> getUser() {
        return authorService.getAll();
    }
}
