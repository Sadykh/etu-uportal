package etu.uportal.domain;

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    private String passwordHash;

    private Integer roleId;

    private Long createdAt;
    private Long updatedAt;

    public User(String username, String passwordHash, Integer roleId) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return String.format("User[id='%d', username = '%s', roleId = '%d']", id, username, roleId);
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
