package etu.uportal.infrastructure.service;

import etu.uportal.Application;
import etu.uportal.domain.Publication;
import etu.uportal.infrastructure.repository.PublicationRepository;
import etu.uportal.spring.OffsetLimitPageable;
import etu.uportal.web.dto.publication.PublicationCreateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PublicationService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private PublicationRepository publicationRepository;


    public PublicationCreateDto create(final PublicationCreateDto dto) {
        final Publication publication = new Publication(dto.getTitle(), dto.getIntroText(), dto.getPublishedAt());
        publicationRepository.save(publication);
        return new PublicationCreateDto(publication.getId(), publication.getTitle(), publication.getIntroText(), publication.getPublishedAt());
    }

    public Page<Publication> getAll(OffsetLimitPageable pageRequest) {
        return publicationRepository.findAll(pageRequest);
    }

}
