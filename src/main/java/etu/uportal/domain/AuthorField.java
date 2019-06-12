package etu.uportal.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AuthorField {

    @ApiModelProperty(value = "ID сущности, генерируется автоматические при создании", example = "100500")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ApiModelProperty(value = "Имя автора на русском языке", example = "Василий")
    @ManyToOne()
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Column(nullable = false)
    @ApiModelProperty(value = "Фамилия автора на русском языке", example = "Пупкин")
    private String name;

    @Column(nullable = false)
    @ApiModelProperty(value = "Отчество автора на русском языке", example = "Пупкинович")
    private String value;

    @ApiModelProperty(value = "unixtime времени создания сущности", example = "1552714294")
    private long createdAt;

    @ApiModelProperty(value = "unixtime времени изменения сущности", example = "1552714294")
    private long updatedAt;

    public AuthorField(Author author, String name, String value) {
        this.author = author;
        this.name = name;
        this.value = value;
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
