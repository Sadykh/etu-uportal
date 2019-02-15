package etu.uportal.web.dto.author;


import etu.uportal.infrastructure.validation.user.ValidPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDto {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String middleName;

    @NotNull
    private String firstNameEn;

    @NotNull
    private String lastNameEn;

    @NotNull
    private String middleNameEn;

    public AuthorDto(@NotNull String firstName, @NotNull String lastName, @NotNull String middleName, @NotNull String firstNameEn, @NotNull String lastNameEn, @NotNull String middleNameEn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.firstNameEn = firstNameEn;
        this.lastNameEn = lastNameEn;
        this.middleNameEn = middleNameEn;
    }
}
