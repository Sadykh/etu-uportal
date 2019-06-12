package etu.uportal.infrastructure.service;

import etu.uportal.Application;
import etu.uportal.domain.Author;
import etu.uportal.domain.AuthorField;
import etu.uportal.infrastructure.repository.AuthorFieldRepository;
import etu.uportal.infrastructure.repository.AuthorRepository;
import etu.uportal.web.dto.author.AuthorCreateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorFieldRepository authorFieldRepository;


    public AuthorCreateDto create(final AuthorCreateDto dto) {
        final Author author = new Author(dto.getFirstName(), dto.getLastName(), dto.getMiddleName(),
                dto.getFirstNameEn(), dto.getLastNameEn(), dto.getMiddleNameEn());
        List<AuthorField> authorFields = new ArrayList<>();
        setAuthorFields(dto, author, authorFields);
        authorRepository.save(author);
        return dto;
    }

    public AuthorCreateDto updateById(final long id, final AuthorCreateDto dto) {
        final Author author = authorRepository.getOne(id);
        author.setFirstName(dto.getFirstName());
        author.setFirstNameEn(dto.getFirstNameEn());
        author.setLastName(dto.getLastName());
        author.setLastNameEn(dto.getLastNameEn());
        author.setMiddleName(dto.getMiddleName());
        author.setMiddleNameEn(dto.getMiddleNameEn());

        authorFieldRepository.deleteAuthorFieldsByAuthor(author);
        List<AuthorField> authorFields = new ArrayList<>();
        setAuthorFields(dto, author, authorFields);
        authorRepository.save(author);
        return dto;
    }

    private void setAuthorFields(AuthorCreateDto dto, Author author, List<AuthorField> authorFields) {
        dto.getAuthorFields().forEach(item -> {
            authorFields.add(new AuthorField(author, item.getName(), item.getValue()));
        });
        author.setAuthorFields(authorFields);
    }

    public Author getOneById(long id) {
        return authorRepository.getOne(id);
    }

    public Page<Author> getAll(PageRequest pageRequest) {
        return authorRepository.findAll(pageRequest);
    }

}
