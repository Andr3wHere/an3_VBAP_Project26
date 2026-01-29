package cz.osu.backend.model.dto.user;

import cz.osu.backend.model.enums.UserRole;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDTO {
    UUID id;
    String username;
    UserRole role;
}
