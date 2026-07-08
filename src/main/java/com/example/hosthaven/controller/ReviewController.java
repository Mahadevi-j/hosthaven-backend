package com.example.hosthaven.controller;

import java.util.List;

import com.example.hosthaven.dto.ReviewRequestDto;
import com.example.hosthaven.entity.StayReview;
import com.example.hosthaven.service.ReviewService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Create Review
    @PreAuthorize("hasRole('GUEST')")
    @PostMapping
    public ResponseEntity<StayReview> createReview(
            @Valid @RequestBody ReviewRequestDto reviewRequestDto) {

        StayReview review = reviewService.createReview(reviewRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(review);
    }

    // Get All Reviews
    @PreAuthorize("hasAnyRole('GUEST','PROPERTY_OWNER','HOSPITALITY_ADMIN')")
    @GetMapping
    public ResponseEntity<List<StayReview>> getAllReviews() {

        return ResponseEntity.ok(
                reviewService.getAllReviews());
    }

    // Get Review By Id
    @PreAuthorize("hasAnyRole('GUEST','PROPERTY_OWNER','HOSPITALITY_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<StayReview> getReviewById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                reviewService.getReviewById(id));
    }

    // Update Review
    @PreAuthorize("hasRole('GUEST')")
    @PutMapping("/{id}")
    public ResponseEntity<StayReview> updateReview(
            @PathVariable Long id,
            @Valid @RequestBody ReviewRequestDto reviewRequestDto) {

        return ResponseEntity.ok(
                reviewService.updateReview(id, reviewRequestDto));
    }

    // Delete Review
    @PreAuthorize("hasRole('HOSPITALITY_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(
            @PathVariable Long id) {

        reviewService.deleteReview(id);

        return ResponseEntity.ok("Review deleted successfully");
    }

    // Get Reviews By Property Id
    @PreAuthorize("hasAnyRole('GUEST','PROPERTY_OWNER','HOSPITALITY_ADMIN')")
    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<StayReview>> getReviewsByProperty(
            @PathVariable Long propertyId) {

        return ResponseEntity.ok(
                reviewService.getReviewsByProperty(propertyId));
    }
}