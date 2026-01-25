package cz.osu.backend.repository;

import cz.osu.backend.model.db.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Category getCategoryByName(String name);
}
