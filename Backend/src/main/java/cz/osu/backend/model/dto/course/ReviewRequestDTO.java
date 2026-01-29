package cz.osu.backend.model.dto.course;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ReviewRequestDTO {
    @Min(value = 1, message = "Hodnocení nesmí být větší než 0.")
    @Max(value = 5, message = "Hodnocení nesmí být menší než 6.")
    private int rating;
    @NotBlank(message = "Komentář nesmí být prázdný.")
    private String comment;
    @NotNull(message = "Course ID is required")
    private UUID courseId;
    @NotNull(message = "User ID is required")
    private UUID userId;
}
