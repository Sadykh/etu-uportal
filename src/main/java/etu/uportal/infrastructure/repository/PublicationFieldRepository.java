package etu.uportal.infrastructure.repository;

import etu.uportal.domain.publication.Publication;
import etu.uportal.domain.publication.PublicationField;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface PublicationFieldRepository extends JpaRepository<PublicationField, Long> {

    @Transactional
    void deletePublicationFieldsByPublication(Publication publication);
}
