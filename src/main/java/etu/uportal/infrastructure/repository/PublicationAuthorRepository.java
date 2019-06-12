package etu.uportal.infrastructure.repository;

import etu.uportal.domain.publication.Publication;
import etu.uportal.domain.publication.PublicationAuthor;
import etu.uportal.domain.publication.PublicationAuthorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface PublicationAuthorRepository extends JpaRepository<PublicationAuthor, PublicationAuthorId> {

//    @Modifying
//    @Transactional
//    @Query("delete from PublicationAuthor p where p.publication_id = ?1")
//    void deleteTest(long publicationId);
}
