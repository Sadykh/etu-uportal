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
    private long id;

    @Column(unique = true, nullable = false)
    private String email;
    private String passwordHash;

    private int roleId;

    private long createdAt;
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
