package etu.uportal.web.dto.user;

import etu.uportal.infrastructure.validation.user.ValidPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    @NotNull
    private String email;

    @NotNull
    @ValidPassword
    private String password;

    @NotNull
    private int roleId;

    public UserDto(@NotNull String email, @NotNull String password, @NotNull int roleId) {
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }
}
