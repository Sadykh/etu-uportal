package etu.uportal.rest;

import etu.uportal.domain.User;
import etu.uportal.infrastructure.service.UserService;
import etu.uportal.spring.OffsetLimitPageable;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/api/user", tags = "User", authorizations = @Authorization(value = "Bearer"))
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Получить список всех пользователей")
    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public Page<User> getUser(OffsetLimitPageable pageRequest) {
        return userService.getAll(pageRequest);
    }
}
