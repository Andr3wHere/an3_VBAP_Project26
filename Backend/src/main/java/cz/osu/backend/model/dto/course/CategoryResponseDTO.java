package cz.osu.backend.model.dto.course;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoryResponseDTO {
    private UUID id;
    private String name;
}
