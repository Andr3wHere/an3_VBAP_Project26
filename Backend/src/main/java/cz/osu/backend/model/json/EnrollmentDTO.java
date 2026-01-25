package cz.osu.backend.model.json;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EnrollmentDTO {
    @NotEmpty(message = "Uživatel musí být vyplněn")
    private java.util.UUID userId;
    @NotEmpty(message = "Kurz musí být vyplněn")
    private java.util.UUID course;
}
