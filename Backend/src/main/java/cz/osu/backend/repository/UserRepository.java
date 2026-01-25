package cz.osu.backend.repository;

import cz.osu.backend.model.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> getUserByUsername(String username);

    Optional<User> getUserById(UUID id);
}
