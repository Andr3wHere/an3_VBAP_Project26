package cz.osu.backend.model.json;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequestDTO {
    @NotBlank(message = "Název kategorie je povinný")
    @Size(min = 5, max = 50, message = "Název musí mít 5 až 50 znaků")
    private String name;
}
