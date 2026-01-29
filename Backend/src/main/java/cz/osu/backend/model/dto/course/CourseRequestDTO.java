package cz.osu.backend.model.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class CourseRequestDTO {
    @NotBlank(message = "Název kurzu je povinný")
    @Size(min = 3, max = 100, message = "Název musí mít 3 až 100 znaků")
    private String name;

    @NotBlank(message = "Popis kurzu nesmí být prázdný")
    private String description;

    @NotNull(message = "ID kategorie kurzu je povinná")
    private UUID categoryId;

    @NotNull(message = "ID učitele je povinné")
    private UUID teacherId;
}
