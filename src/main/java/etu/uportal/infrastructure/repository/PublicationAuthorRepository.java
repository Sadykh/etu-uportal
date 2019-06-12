package etu.uportal.infrastructure.repository;

import etu.uportal.domain.publication.Publication;
import etu.uportal.domain.publication.PublicationAuthor;
import etu.uportal.domain.publication.PublicationAuthorId;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PublicationAuthorRepository extends JpaRepository<PublicationAuthor, PublicationAuthorId> {


//    List<PublicationAuthor> findBypublication(Example<Publication> publication);
}
