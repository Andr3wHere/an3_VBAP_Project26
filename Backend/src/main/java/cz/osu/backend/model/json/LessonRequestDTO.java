package cz.osu.backend.model.json;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class LessonRequestDTO {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Content text is required")
    private String contentText;
    private String videoUrl;
    @NotNull(message = "Course ID is required")
    private UUID courseId;
}
