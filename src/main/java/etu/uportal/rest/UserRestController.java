package etu.uportal.rest;

import etu.uportal.domain.User;
import etu.uportal.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public Iterable<User> getUser() {
        return userService.getAll();
    }
}
