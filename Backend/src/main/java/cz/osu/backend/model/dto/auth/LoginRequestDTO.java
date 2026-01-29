package cz.osu.backend.model.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @NotBlank(message = "Uživatelské jméno je povinné")
    @Size(min = 5, max = 20, message = "Uživatelské jméno musí mít 5 až 20 znaků")
    private String username;
    @NotBlank(message = "Heslo je povinné")
    @Size(min = 10, max = 100, message = "Heslo musí mít 10 až 100 znaků")
    private String password;
}
