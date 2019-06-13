package etu.uportal.infrastructure.repository;

import etu.uportal.domain.publication.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Long> {

    List<Publication> findByTitleContaining(String title);
}
