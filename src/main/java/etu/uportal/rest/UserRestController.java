package etu.uportal.rest;

import etu.uportal.domain.User;
import etu.uportal.infrastructure.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "/api/user", tags = "User", authorizations = @Authorization(value = "Bearer"))
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Получить список всех пользователей")
    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public List<User> getUser() {
        return userService.getAll();
    }
}
