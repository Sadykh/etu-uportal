package etu.uportal.web.dto.user;

import etu.uportal.infrastructure.validation.user.PasswordConstraintValidator;
import etu.uportal.infrastructure.validation.user.ValidPassword;
import io.swagger.annotations.ApiModelProperty;
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
public class UserCreateDto {

    @ApiModelProperty(value = "ID пользователя, заполнять не нужно, он сам придет при успешной регистрации", example = "100500")
    private long id;

    @ApiModelProperty(value = "Email пользователя", example = "vasiliy.pupkin@yandex.ru", required = true)
    @NotNull
    @NotEmpty
    @Email
    private String email;

    @ApiModelProperty(value = "Пароль пользователя, нужен сложный пароль. При успешной регистрации это поле придет пустым", example = "QxoI7l21kLHNOky4tyvT", required = true)
    @ValidPassword
    private String password;

    @ApiModelProperty(value = "Роль пользователя, пока тут кроме чисел ничего не проверяется", example = "1", allowableValues = "1, 2, 3", required = true)
    @NotNull
    private int roleId;

    public UserCreateDto(@NotNull @NotEmpty @Email String email, @NotNull @NotEmpty @Size(min = 6) String password, @NotNull int roleId) {
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    public UserCreateDto(long id, @NotNull @NotEmpty @Email String email, @Size(min = 6) String password, @NotNull int roleId) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }
}
