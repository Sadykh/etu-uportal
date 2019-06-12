package etu.uportal.domain.publication;

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
public class PublicationField {

    @ApiModelProperty(value = "ID сущности, генерируется автоматические при создании", example = "100500")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ApiModelProperty(value = "Какой публикации принадлежит")
    @ManyToOne()
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @Column(nullable = false)
    @ApiModelProperty(value = "Название кастомного поля", example = "Телефон", required = true)
    private String name;

    @Column(nullable = false)
    @ApiModelProperty(value = "Значение кастомного поля", example = "+7 911 111 12 12", required = true)
    private String value;

    @ApiModelProperty(value = "unixtime времени создания сущности", example = "1552714294")
    private long createdAt;

    @ApiModelProperty(value = "unixtime времени изменения сущности", example = "1552714294")
    private long updatedAt;

    public PublicationField(Publication publication, String name, String value) {
        this.publication = publication;
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
