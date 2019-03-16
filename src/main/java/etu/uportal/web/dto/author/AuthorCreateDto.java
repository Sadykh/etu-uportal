package etu.uportal.web.dto.author;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AuthorCreateDto {

    @ApiModelProperty(value = "ID автора, заполнять не нужно, он сам придет при успешном сохранении", example = "100500")
    private long id;

    @ApiModelProperty(value = "Имя автора на русском языке", example = "Василий", required = true)
    @NotNull
    @NotEmpty
    private String firstName;

    @ApiModelProperty(value = "Фамилия автора на русском языке", example = "Пупкин", required = true)
    @NotNull
    @NotEmpty
    private String lastName;

    @ApiModelProperty(value = "Отчество автора на русском языке", example = "Пупкинович", required = true)
    @NotNull
    @NotEmpty
    private String middleName;

    @ApiModelProperty(value = "Имя автора на английском языке", example = "Vasiliy", required = true)
    @NotNull
    @NotEmpty
    private String firstNameEn;

    @ApiModelProperty(value = "Фамилия автора на английском языке", example = "Pupkin", required = true)
    @NotNull
    @NotEmpty
    private String lastNameEn;

    @ApiModelProperty(value = "Отчество автора на английском языке", example = "Pupkinovich", required = true)
    @NotNull
    @NotEmpty
    private String middleNameEn;

    public AuthorCreateDto(@NotNull @NotEmpty String firstName, @NotNull @NotEmpty String lastName, @NotNull @NotEmpty String middleName, @NotNull @NotEmpty String firstNameEn, @NotNull @NotEmpty String lastNameEn, @NotNull @NotEmpty String middleNameEn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.firstNameEn = firstNameEn;
        this.lastNameEn = lastNameEn;
        this.middleNameEn = middleNameEn;
    }

    public AuthorCreateDto(long id, @NotNull @NotEmpty String firstName, @NotNull @NotEmpty String lastName, @NotNull @NotEmpty String middleName, @NotNull @NotEmpty String firstNameEn, @NotNull @NotEmpty String lastNameEn, @NotNull @NotEmpty String middleNameEn) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.firstNameEn = firstNameEn;
        this.lastNameEn = lastNameEn;
        this.middleNameEn = middleNameEn;
    }
}
