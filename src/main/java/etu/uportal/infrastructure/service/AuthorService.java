package etu.uportal.infrastructure.service;

import etu.uportal.Application;
import etu.uportal.domain.Author;
import etu.uportal.infrastructure.repository.AuthorRepository;
import etu.uportal.spring.OffsetLimitPageable;
import etu.uportal.web.dto.author.AuthorCreateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private AuthorRepository authorRepository;


    public AuthorCreateDto create(final AuthorCreateDto dto) {
        final Author author = new Author(dto.getFirstName(), dto.getLastName(), dto.getMiddleName(),
                dto.getFirstNameEn(), dto.getLastNameEn(), dto.getMiddleNameEn());
        authorRepository.save(author);
        return new AuthorCreateDto(author.getId(), author.getFirstName(), author.getLastName(), author.getMiddleName(),
                author.getFirstNameEn(), author.getLastNameEn(), author.getMiddleNameEn());
    }

    public Page<Author> getAll(PageRequest pageRequest) {
        return authorRepository.findAll(pageRequest);
    }

}
