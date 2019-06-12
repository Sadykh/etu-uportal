package etu.uportal.domain.publication;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Publication {

    @ApiModelProperty(value = "ID сущности, генерируется автоматические при создании, заполнять не нужно,", example = "100500")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @ApiModelProperty(value = "Название публикации", example = "C++ за 21 день")
    private String title;

    @Column(nullable = false, length = 2000)
    @ApiModelProperty(value = "Короткое описание (до 2000 знаков)", example = "Постулат притягивает контрпример. Функциональный анализ позитивно уравновешивает действительный вектор. Сравнивая две формулы, приходим к следующему заключению: эпсилон окрестность последовательно отражает график функции многих переменных, что известно даже школьникам. Открытое множество создает экспериментальный абсолютно сходящийся ряд.")
    private String introText;

    @Column(nullable = false)
    @ApiModelProperty(value = "unixtime даты публикации", example = "1552714294")
    private long publishedAt;

    @ApiModelProperty(value = "unixtime времени создания сущности", example = "1552714294")
    private long createdAt;

    @ApiModelProperty(value = "unixtime времени изменения сущности", example = "1552714294")
    private long updatedAt;

    @OneToMany(mappedBy = "id.publication", cascade = CascadeType.ALL)
    private Set<PublicationAuthor> publicationAuthors;

    @ApiModelProperty(value = "Дополнительные поля публикации")
    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
    private List<PublicationField> publicationFields = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now().getEpochSecond();
        updatedAt = Instant.now().getEpochSecond();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now().getEpochSecond();
    }


    public Publication(String title, String introText, long publishedAt) {
        this.title = title;
        this.introText = introText;
        this.publishedAt = publishedAt;
    }
}
