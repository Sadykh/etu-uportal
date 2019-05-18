package etu.uportal.domain;

import etu.uportal.domain.publication.PublicationAuthor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Author {

    @ApiModelProperty(value = "ID сущности, генерируется автоматические при создании", example = "100500")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @ApiModelProperty(value = "Имя автора на русском языке", example = "Василий")
    private String firstName;

    @Column(nullable = false)
    @ApiModelProperty(value = "Фамилия автора на русском языке", example = "Пупкин")
    private String lastName;

    @Column(nullable = false)
    @ApiModelProperty(value = "Отчество автора на русском языке", example = "Пупкинович")
    private String middleName;

    @Column(nullable = false)
    @ApiModelProperty(value = "Имя автора на английском языке", example = "Vasiliy")
    private String firstNameEn;

    @Column(nullable = false)
    @ApiModelProperty(value = "Фамилия автора на английском языке", example = "Pupkin")
    private String lastNameEn;

    @Column(nullable = false)
    @ApiModelProperty(value = "Отчество автора на английском языке", example = "Pupkinovich")
    private String middleNameEn;

    @ApiModelProperty(value = "unixtime времени создания сущности", example = "1552714294")
    private long createdAt;

    @ApiModelProperty(value = "unixtime времени изменения сущности", example = "1552714294")
    private long updatedAt;

    @OneToMany(mappedBy = "id.author", cascade = CascadeType.ALL)
    private Set<PublicationAuthor> authorPublications = new HashSet<>(0);

    public Author(String firstName, String lastName, String middleName, String firstNameEn, String lastNameEn, String middleNameEn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.firstNameEn = firstNameEn;
        this.lastNameEn = lastNameEn;
        this.middleNameEn = middleNameEn;
    }

    @Override
    public String toString() {
        return String.format("Author[id='%d', firstName = '%s', lastName = '%s', middleName = '%s']", id, firstName, lastName, middleName);
    }

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now().getEpochSecond();
        updatedAt = Instant.now().getEpochSecond();
    }


    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now().getEpochSecond();
    }
}
