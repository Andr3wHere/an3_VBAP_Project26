package cz.osu.backend.model.json;

import lombok.Data;

@Data
public class ReviewResponseDTO {
    int rating;
    String username;
    String comment;
    String course;
}
