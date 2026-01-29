package cz.osu.backend.controller.course;

import cz.osu.backend.model.db.Review;
import cz.osu.backend.model.dto.course.ReviewRequestDTO;
import cz.osu.backend.model.dto.course.ReviewResponseDTO;
import cz.osu.backend.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public Review createReview(@Valid @RequestBody ReviewRequestDTO request) {
        return reviewService.createReview(request);
    }

    @GetMapping("/course/{courseId}")
    public List<ReviewResponseDTO> getReviewsByCourse(@PathVariable UUID courseId) {
        return reviewService.getReviewsByCourse(courseId);
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable UUID id) {
        return reviewService.getReviewById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> updateReview(@PathVariable UUID id, @Valid @RequestBody ReviewRequestDTO request) {
        try {
            return ResponseEntity.ok(reviewService.updateReview(id, request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public List<ReviewResponseDTO> getReviewsByUser(@PathVariable UUID userId) {
        return reviewService.getReviewsByUser(userId);
    }
}
