package cz.osu.backend.service;

import cz.osu.backend.exception.ResourceNotFoundException;
import cz.osu.backend.model.db.Course;
import cz.osu.backend.model.db.Lesson;
import cz.osu.backend.model.json.LessonRequestDTO;
import cz.osu.backend.repository.CourseRepository;
import cz.osu.backend.repository.LessonRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    CourseRepository courseRepository;

    public Lesson createLesson(@Valid LessonRequestDTO request) {
        Lesson lesson = new Lesson();
        lesson.setTitle(request.getTitle());
        lesson.setContentText(request.getContentText());
        lesson.setVideoUrl(request.getVideoUrl());
        Course course = courseRepository.findById(request.getCourseId()).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        lesson.setCourse(course);
        return lessonRepository.save(lesson);
    }

    public List<Lesson> getLessonsByCourse(UUID courseId) {
        return lessonRepository.findAllByCourseId(courseId);
    }

    public Lesson getLessonById(UUID id) {
        return lessonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));
    }

    public Lesson updateLesson(UUID id, @Valid LessonRequestDTO request) {
        Lesson lesson = getLessonById(id);
        lesson.setTitle(request.getTitle());
        lesson.setContentText(request.getContentText());
        lesson.setVideoUrl(request.getVideoUrl());
        return lessonRepository.save(lesson);
    }

    public void deleteLesson(UUID id) {
        lessonRepository.deleteById(id);
    }
}
