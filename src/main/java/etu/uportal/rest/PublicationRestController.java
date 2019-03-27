package etu.uportal.rest;

import etu.uportal.domain.Publication;
import etu.uportal.infrastructure.service.PublicationService;
import etu.uportal.spring.OffsetLimitPageable;
import etu.uportal.web.dto.publication.PublicationCreateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Api(value = "/api/publication", tags = "Publication")
@RequestMapping("/api/publication")
public class PublicationRestController {

    @Autowired
    private PublicationService publicationService;

    @ApiOperation(value = "Получить список всех публикаций")
    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public Page<Publication> getUser(OffsetLimitPageable pageRequest) {
        return publicationService.getAll(pageRequest);
    }

    @ApiOperation(value = "Добавить новую публикацию")
    @PreAuthorize("permitAll()")
    @PostMapping("/")
    public PublicationCreateDto addPublication(@RequestBody @Valid PublicationCreateDto publicationCreateDto) {
        return publicationService.create(publicationCreateDto);
    }
}
