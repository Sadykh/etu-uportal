package etu.uportal.infrastructure.repository.author;

import etu.uportal.domain.author.Author;
import etu.uportal.domain.author.AuthorField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AuthorFieldRepository extends JpaRepository<AuthorField, Long> {

    @Transactional
    void deleteAuthorFieldsByAuthor(Author author);
}
