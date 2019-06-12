package etu.uportal.domain.publication;

import etu.uportal.domain.author.Author;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class PublicationAuthorId implements Serializable {

    @ManyToOne
    private Publication publication;

    @ManyToOne
    private Author author;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PublicationAuthorId that = (PublicationAuthorId) o;
        return Objects.equals(publication, that.publication) &&
                Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publication, author);
    }
}