package etu.uportal.infrastructure.repository.author;

import etu.uportal.domain.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
