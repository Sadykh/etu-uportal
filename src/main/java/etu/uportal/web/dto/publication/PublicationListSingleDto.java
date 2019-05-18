package etu.uportal.web.dto.publication;

import etu.uportal.domain.publication.Publication;
import etu.uportal.infrastructure.service.PublicationService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PublicationListSingleDto {


    private long id;

    private String title;

    private String authors;

    public PublicationListSingleDto(Publication publication, PublicationService publicationService) {
        this.id = publication.getId();
        this.title = publication.getTitle();
        this.authors = publicationService.getFormattedAuthorNames(publication);
    }
}
