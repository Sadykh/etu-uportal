package etu.uportal.infrastructure.service;

import etu.uportal.Application;
import etu.uportal.domain.Author;
import etu.uportal.infrastructure.repository.AuthorRepository;
import etu.uportal.web.dto.author.AuthorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private AuthorRepository authorRepository;


    public Author create(final AuthorDto dto) {
        final Author author = new Author(dto.getFirstName(), dto.getLastName(), dto.getMiddleName(), dto.getFirstNameEn(), dto.getLastNameEn(), dto.getMiddleNameEn());
        return authorRepository.save(author);
    }

    public Iterable<Author> getAll() {
        return authorRepository.findAll();
    }

}
