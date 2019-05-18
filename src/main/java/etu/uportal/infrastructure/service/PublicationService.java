package etu.uportal.infrastructure.service;

import etu.uportal.Application;
import etu.uportal.domain.Author;
import etu.uportal.domain.publication.Publication;
import etu.uportal.domain.publication.PublicationAuthor;
import etu.uportal.infrastructure.repository.PublicationAuthorRepository;
import etu.uportal.infrastructure.repository.PublicationRepository;
import etu.uportal.web.dto.publication.PublicationCreateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PublicationService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private PublicationRepository publicationRepository;


    @Autowired
    private PublicationAuthorRepository publicationAuthorRepository;

    @Autowired
    private AuthorService authorService;

    public PublicationCreateDto create(final PublicationCreateDto dto) {
        final Publication publication = new Publication(dto.getTitle(), dto.getIntroText(), dto.getPublishedAt());
        publicationRepository.save(publication);

        int authorRank = 0;
        for (Integer authorId : dto.getAuthorListId()) {
            Author author = authorService.getOneById(authorId);
            PublicationAuthor publicationAuthor = new PublicationAuthor();
            publicationAuthor.setAuthor(author);
            publicationAuthor.setPublication(publication);
            publicationAuthor.setRank(authorRank++);
            publicationAuthorRepository.save(publicationAuthor);
        }

        return new PublicationCreateDto(publication.getId(), publication.getTitle(), publication.getIntroText(), publication.getPublishedAt());
    }

    public Page<Publication> getAll(PageRequest pageRequest) {
        return publicationRepository.findAll(pageRequest);
    }


    public String getFormattedAuthorNames(Publication publication) {
        String result = "";

        List<PublicationAuthor> sortedList = new ArrayList<>(publication.getPublicationAuthors());

        sortedList.sort(Comparator.comparing(PublicationAuthor::getRank));

        for (PublicationAuthor publicationAuthor : sortedList) {
            Author author = publicationAuthor.getAuthor();
            result = result.concat(author.getLastName());
            result = result.concat(" ");
            result = result.concat(author.getFirstName().substring(0, 1));
            result = result.concat(". ");
            result = result.concat(author.getMiddleName().substring(0, 1));
            result = result.concat(". ");
            result = result.concat(", ");
        }
        return result.substring(0, result.length() - 2);
    }

}
