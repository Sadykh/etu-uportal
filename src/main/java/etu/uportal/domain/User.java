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
public class User {

    @ApiModelProperty(value = "ID сущности, генерируется автоматические при создании", example = "100500")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ApiModelProperty(value = "Email пользователя по которому он входит и получает уведомления", example = "vasiliy.pupkin@yandex.ru")
    @Column(unique = true, nullable = false)
    private String email;

    @ApiModelProperty(value = "Хэш пароля пользователя, рано или поздно он будет скрыт", example = "это bcrypt, детка")
    private String passwordHash;

    @ApiModelProperty(value = "ID роли, к которой принадлежит пользователь", example = "1")
    private int roleId;

    @ApiModelProperty(value = "unixtime времени создания сущности", example = "1552714294")
    private long createdAt;

    @ApiModelProperty(value = "unixtime времени изменения сущности", example = "1552714294")
    private long updatedAt;

    public User(String email, String passwordHash, int roleId) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return String.format("User[id='%d', email = '%s', roleId = '%d']", id, email, roleId);
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
