package etu.uportal.infrastructure.repository;

import etu.uportal.domain.publication.PublicationAuthor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PublicationAuthorRepository extends JpaRepository<PublicationAuthor, Long> {
}
