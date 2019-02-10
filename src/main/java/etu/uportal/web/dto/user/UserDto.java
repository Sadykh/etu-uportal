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
    private String username;

    @NotNull
    @ValidPassword
    private String password;

    public UserDto(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.password = password;
    }
}
