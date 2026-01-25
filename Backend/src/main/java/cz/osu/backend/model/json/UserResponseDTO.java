package cz.osu.backend.model.json;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDTO {
    UUID id;
    String username;
    UserRole role;
}
