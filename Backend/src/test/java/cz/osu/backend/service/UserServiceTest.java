package cz.osu.backend.service;

import cz.osu.backend.exception.ResourceNotFoundException;
import cz.osu.backend.model.db.Course;
import cz.osu.backend.model.db.User;
import cz.osu.backend.repository.CourseRepository;
import cz.osu.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void enrollUserToCourse_ValidInputs_ShouldAddCourseAndSaveUser() {
        UUID userId = UUID.randomUUID();
        UUID courseId = UUID.randomUUID();

        User fakeUser = new User();
        fakeUser.setId(userId);

        Course fakeCourse = new Course();
        fakeCourse.setId(courseId);

        when(userRepository.getUserById(userId)).thenReturn(Optional.of(fakeUser));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(fakeCourse));

        userService.enrollUserToCourse(userId, courseId);

        assertTrue(fakeUser.getCourses().contains(fakeCourse));

        verify(userRepository, times(1)).save(fakeUser);
    }

    @Test
    void enrollUserToCourse_InvalidUser_ShouldThrowException() {
        UUID userId = UUID.randomUUID();
        UUID courseId = UUID.randomUUID();

        Course fakeCourse = new Course();
        fakeCourse.setId(courseId);

        when(userRepository.getUserById(userId)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.enrollUserToCourse(userId, courseId));
    }

    @Test
    void enrollUserToCourse_InvalidCourse_ShouldThrowException() {
        UUID userId = UUID.randomUUID();
        UUID courseId = UUID.randomUUID();

        User fakeUser = new User();
        fakeUser.setId(userId);

        when(userRepository.getUserById(userId)).thenReturn(Optional.of(fakeUser));
        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.enrollUserToCourse(userId, courseId));
    }

    @Test
    void enrollUserToEnrolledCourse_ValidInputs_ShouldThrowException() {
        UUID userId = UUID.randomUUID();
        UUID courseId = UUID.randomUUID();

        Course fakeCourse = new Course();
        fakeCourse.setId(courseId);
        fakeCourse.setName("Java pro pokročilé");

        User fakeUser = new User();
        fakeUser.setId(userId);
        fakeUser.setUsername("testUser");

        Set<Course> existingCourses = new HashSet<>();
        existingCourses.add(fakeCourse);
        fakeUser.setCourses(existingCourses);

        when(userRepository.getUserById(userId)).thenReturn(Optional.of(fakeUser));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(fakeCourse));

        userService.enrollUserToCourse(userId, courseId);

        assertEquals(1, fakeUser.getCourses().size());

        verify(userRepository, times(1)).save(fakeUser);
    }
}