package etu.uportal.infrastructure.repository;

import etu.uportal.domain.Author;
import etu.uportal.domain.AuthorField;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface AuthorFieldRepository extends JpaRepository<AuthorField, Long> {

    @Transactional
    void deleteAuthorFieldsByAuthor(Author author);
}
