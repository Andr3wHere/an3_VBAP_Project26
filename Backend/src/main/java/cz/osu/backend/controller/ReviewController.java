package cz.osu.backend.controller;

import cz.osu.backend.model.db.Review;
import cz.osu.backend.model.json.ReviewRequestDTO;
import cz.osu.backend.model.json.ReviewResponseDTO;
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
    public ResponseEntity<Review> createReview(@Valid @RequestBody ReviewRequestDTO request) {
        return ResponseEntity.ok(reviewService.createReview(request));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByCourse(@PathVariable UUID courseId) {
        return ResponseEntity.ok(reviewService.getReviewsByCourse(courseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable UUID id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
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
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(reviewService.getReviewsByUser(userId));
    }
}
