package etu.uportal.infrastructure.repository;

import etu.uportal.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
