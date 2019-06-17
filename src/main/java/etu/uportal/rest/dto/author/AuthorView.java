package etu.uportal.rest.dto.author;

import etu.uportal.domain.author.AuthorField;
import etu.uportal.domain.publication.Publication;
import etu.uportal.web.dto.author.AuthorFieldDto;
import etu.uportal.web.dto.publication.PublicationCreateDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class AuthorView {

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

    @ApiModelProperty(value = "Дополнительные поля автора", required = true)
    private List<AuthorFieldDto> fields = new ArrayList<>();

    @ApiModelProperty(value = "Публикации автора", required = true)
    private List<PublicationCreateDto> publications = new ArrayList<>();

    @ApiModelProperty(value = "Разрешено ли удалять", example = "2", required = true)
    private Boolean allowDelete = false;

    public void setAuthorFields(List<AuthorField> authorFields) {
        authorFields.forEach(item -> this.fields.add(new AuthorFieldDto(item.getName(), item.getValue())));
    }

    public void setPublications(List<Publication> publications) {
        publications.forEach(item -> this.publications.add(new PublicationCreateDto(item.getId(), item.getTitle(),
                item.getIntroText(), item.getPublicationFields(), item.getPublicationAuthors())));
    }

    public AuthorView(long id, String firstName, String lastName, String middleName, String firstNameEn,
                      String lastNameEn, String middleNameEn, Integer publicationQty) {
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
