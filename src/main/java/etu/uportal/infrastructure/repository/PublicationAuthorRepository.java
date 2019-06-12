package etu.uportal.infrastructure.repository;

import etu.uportal.domain.publication.PublicationAuthor;
import etu.uportal.domain.publication.PublicationAuthorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface PublicationAuthorRepository extends JpaRepository<PublicationAuthor, PublicationAuthorId> {


    @Transactional
    void deleteByIdPublicationId(long publicationId);
}
