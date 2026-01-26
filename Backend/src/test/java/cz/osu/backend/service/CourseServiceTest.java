package cz.osu.backend.service;

import cz.osu.backend.exception.ResourceNotFoundException;
import cz.osu.backend.model.db.Course;
import cz.osu.backend.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    void getCourseById_CourseExists_ShouldReturnCourse() {
        UUID courseId = UUID.randomUUID();
        Course fakeCourse = new Course();
        fakeCourse.setId(courseId);
        fakeCourse.setName("Java pro pokročilé");

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(fakeCourse));

        Course result = courseService.getCourseById(courseId);

        assertNotNull(result);
        assertEquals("Java pro pokročilé", result.getName());

        verify(courseRepository, times(1)).findById(courseId);
    }

    @Test
    void getCourseById_CourseDoesNotExist_ShouldThrowException() {
        UUID nonExistentId = UUID.randomUUID();

        when(courseRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            courseService.getCourseById(nonExistentId);
        });

        assertTrue(exception.getMessage().contains("nebyl nalezen"));
    }
}