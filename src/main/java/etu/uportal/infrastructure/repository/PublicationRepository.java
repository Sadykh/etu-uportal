package etu.uportal.infrastructure.repository;

import etu.uportal.domain.publication.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication, Long> {

}
