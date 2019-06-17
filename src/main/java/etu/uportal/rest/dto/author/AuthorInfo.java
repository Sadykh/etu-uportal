package etu.uportal.rest.dto.author;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class AuthorInfo {

    @ApiModelProperty(value = "ID автора", example = "100500")
    private long id;

    @ApiModelProperty(value = "Имя автора на русском языке", example = "Василий", required = true)
    private String firstName;

    @ApiModelProperty(value = "Фамилия автора на русском языке", example = "Пупкин", required = true)
    private String lastName;

    @ApiModelProperty(value = "Отчество автора на русском языке", example = "Пупкинович", required = true)
    private String middleName;

    @ApiModelProperty(value = "Имя автора на английском языке", example = "Vasiliy", required = true)
    private String firstNameEn;

    @ApiModelProperty(value = "Фамилия автора на английском языке", example = "Pupkin", required = true)
    private String lastNameEn;

    @ApiModelProperty(value = "Отчество автора на английском языке", example = "Pupkinovich", required = true)
    private String middleNameEn;

    @ApiModelProperty(value = "Количество публикаций автора", example = "2", required = true)
    private Integer publicationQty = 0;

    @ApiModelProperty(value = "Разрешено ли удалять", example = "2", required = true)
    private Boolean allowDelete = false;

    public AuthorInfo(long id, String firstName, String lastName, String middleName, String firstNameEn, String lastNameEn, String middleNameEn, Integer publicationQty) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.firstNameEn = firstNameEn;
        this.lastNameEn = lastNameEn;
        this.middleNameEn = middleNameEn;
        this.publicationQty = publicationQty;
        this.allowDelete = publicationQty == 0;
    }
}
