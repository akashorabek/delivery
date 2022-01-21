package com.delivery.model.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterUserDTO {
    @Email(message = "email должен иметь формат почты.")
    @Size(min = 1, max = 128, message = "Почта должна состоять от 1 до 128 символов")
    @NotBlank(message = "Почта не может быть пустым")
    private String email;

    @Size(min = 6, max = 20, message = "Пароль должен состоять от 6 до 20 символов")
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;
}
