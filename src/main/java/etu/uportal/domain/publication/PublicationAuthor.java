package etu.uportal.domain.publication;

import etu.uportal.domain.Author;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "publication_author")
public class PublicationAuthor implements Serializable {

    @Transient
    private Publication publication;

    @Transient
    private Author author;

    @EmbeddedId
    private PublicationAuthorId id = new PublicationAuthorId();

    @Transient
    public Publication getPublication() {
        return id.getPublication();
    }

    public void setPublication(Publication publication) {
        this.id.setPublication(publication);
    }

    @Transient
    public Author getAuthor() {
        return id.getAuthor();
    }

    public void setAuthor(Author author) {
        this.id.setAuthor(author);
    }


    @Column(name = "rank", nullable = false)
    public Integer rank;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        PublicationAuthor that = (PublicationAuthor) o;
        return Objects.equals(publication, that.publication) &&
                Objects.equals(author, that.author) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publication, author, id);
    }
}