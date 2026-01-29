package cz.osu.backend.model.dto.course;

import lombok.Data;

@Data
public class ReviewResponseDTO {
    int rating;
    String username;
    String comment;
    String course;
}
