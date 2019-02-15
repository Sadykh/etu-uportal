package etu.uportal.web.dto.user;

import etu.uportal.infrastructure.validation.user.ValidPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;

    @NotNull
    private int roleId;

    public UserDto(@NotNull @NotEmpty @Email String email, @NotNull @NotEmpty @Size(min = 6) String password, @NotNull int roleId) {
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }
}
