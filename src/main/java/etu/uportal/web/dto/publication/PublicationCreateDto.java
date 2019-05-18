package etu.uportal.web.dto.publication;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class PublicationCreateDto {

    @ApiModelProperty(value = "ID автора, заполнять не нужно, он сам придет при успешном сохранении", example = "100500")
    private long id;

    @NotNull
    @NotEmpty
    @ApiModelProperty(value = "Название публикации", example = "C++ за 21 день", required = true)
    private String title;

    @NotNull
    @NotEmpty
    @ApiModelProperty(value = "Короткое описание (до 2000 знаков)", required = true, example = "Постулат притягивает контрпример. Функциональный анализ позитивно уравновешивает действительный вектор. Сравнивая две формулы, приходим к следующему заключению: эпсилон окрестность последовательно отражает график функции многих переменных, что известно даже школьникам. Открытое множество создает экспериментальный абсолютно сходящийся ряд.")
    private String introText;

    @NotNull
    @ApiModelProperty(value = "unixtime даты публикации", example = "1552714294", required = true)
    private long publishedAt;

    private ArrayList<Integer> authorListId = new ArrayList<>();

    public PublicationCreateDto(@NotNull @NotEmpty String title, @NotNull @NotEmpty String introText) {
        this.title = title;
        this.introText = introText;
        this.publishedAt = 100500L;
    }


    //    public PublicationCreateDto(@NotNull @NotEmpty String title, @NotNull @NotEmpty String introText, @NotNull @NotEmpty long publishedAt) {
//        this.title = title;
//        this.introText = introText;
//        this.publishedAt = publishedAt;
//    }
//
//
    public PublicationCreateDto(long id, @NotNull @NotEmpty String title, @NotNull @NotEmpty String introText, @NotNull @NotEmpty long publishedAt) {
        this.id = id;
        this.title = title;
        this.introText = introText;
        this.publishedAt = publishedAt;
    }
}
