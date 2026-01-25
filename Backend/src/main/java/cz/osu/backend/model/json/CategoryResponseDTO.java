package cz.osu.backend.model.json;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoryResponseDTO {
    private UUID id;
    private String name;
}
