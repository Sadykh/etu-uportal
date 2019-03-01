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
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String firstNameEn;

    @Column(nullable = false)
    private String lastNameEn;

    @Column(nullable = false)
    private String middleNameEn;

    private long createdAt;
    private long updatedAt;

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
