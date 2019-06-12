package etu.uportal.web.dto.publication;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PublicationFieldDto {
    @ApiModelProperty(value = "Название кастомного поля", example = "Телефон", required = true)
    @NotNull
    @NotEmpty
    private String name;

    @ApiModelProperty(value = "Значение кастомного поля", example = "+7 911 111 12 12", required = true)
    @NotNull
    @NotEmpty
    private String value;

    public PublicationFieldDto(@NotNull @NotEmpty String name, @NotNull @NotEmpty String value) {
        this.name = name;
        this.value = value;
    }
}