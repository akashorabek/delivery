package com.delivery.model.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
public class PlaceDTO {
    @Size(min = 2, max = 128, message = "Название должно содержать от 2 до 128 символов")
    @NotBlank(message = "Название не может быть пустым")
    private String name;

    @Size(min = 5, max = 250, message = "Ссылка на картинку должна содержать от 5 до 250 символов")
    @NotBlank(message = "Ссылка на картинку не может быть пуста")
    private String image;

    @NotBlank(message = "Описание не может быть пустым")
    @Size(min = 10, max = 250, message = "Описание должно содержать от 10 до 250 символов")
    private String description;
}
