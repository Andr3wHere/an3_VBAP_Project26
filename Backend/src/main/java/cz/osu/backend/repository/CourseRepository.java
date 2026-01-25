package cz.osu.backend.repository;

import cz.osu.backend.model.db.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
    Course getCourseByName(String name);

    List<Course> findAllByTeacherId(UUID userId);
}
