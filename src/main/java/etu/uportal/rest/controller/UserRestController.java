package etu.uportal.rest.controller;

import etu.uportal.domain.User;
import etu.uportal.infrastructure.service.UserService;
import etu.uportal.spring.OffsetLimitPageable;
import etu.uportal.web.dto.user.UserCreateDto;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @ApiOperation(value = "Добавить пользователя")
    @PreAuthorize("permitAll()")
    @PostMapping("/")
    public UserCreateDto addUser(@RequestBody @Valid UserCreateDto userCreateDto) {
        return userService.registerNewUserAccount(userCreateDto);
    }

    @ApiOperation(value = "Обновление пользователя")
    @PreAuthorize("permitAll()")
    @PostMapping("/{id}")
    public UserCreateDto updateUser(@PathVariable Long id, @RequestBody @Valid UserCreateDto userCreateDto) {
        return userService.updateById(id, userCreateDto);
    }
}
