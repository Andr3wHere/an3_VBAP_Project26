package cz.osu.backend.repository;

import cz.osu.backend.model.db.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void testGetCourseByName() {
        Course course = new Course();
        course.setName("Java 101");
        course.setDescription("Test");
        courseRepository.save(course);

        Course found = courseRepository.getCourseByName("Java 101");
        assertNotNull(found);
    }
}
