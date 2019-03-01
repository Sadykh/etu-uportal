package etu.uportal.web.dto.author;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDto {

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty

    private String lastName;

    @NotNull
    @NotEmpty
    private String middleName;

    @NotNull
    @NotEmpty
    private String firstNameEn;

    @NotNull
    @NotEmpty
    private String lastNameEn;

    @NotNull
    @NotEmpty
    private String middleNameEn;

    public AuthorDto(@NotNull @NotEmpty String firstName, @NotNull @NotEmpty String lastName, @NotNull @NotEmpty String middleName, @NotNull @NotEmpty String firstNameEn, @NotNull @NotEmpty String lastNameEn, @NotNull @NotEmpty String middleNameEn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.firstNameEn = firstNameEn;
        this.lastNameEn = lastNameEn;
        this.middleNameEn = middleNameEn;
    }
}
