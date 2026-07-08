package com.example.hosthaven.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hosthaven.dto.ReviewRequestDto;
import com.example.hosthaven.entity.BookingReservation;
import com.example.hosthaven.entity.StayReview;
import com.example.hosthaven.exception.ResourceNotFoundException;
import com.example.hosthaven.repository.BookingReservationRepository;
import com.example.hosthaven.repository.StayReviewRepository;

@Service
public class ReviewService {

    private final StayReviewRepository reviewRepository;
    private final BookingReservationRepository bookingRepository;

    public ReviewService(StayReviewRepository reviewRepository,
                         BookingReservationRepository bookingRepository) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
    }

    // Create Review
    public StayReview createReview(ReviewRequestDto dto) {

        BookingReservation booking = bookingRepository.findById(dto.getBookingId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found"));

        StayReview review = new StayReview();

        review.setBooking(booking);
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());

        booking.setIsRated(true);
        bookingRepository.save(booking);

        return reviewRepository.save(review);
    }

    // Get All Reviews
    public List<StayReview> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Get Review By Id
    public StayReview getReviewById(Long id) {

        return reviewRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Review not found"));
    }

    // Update Review
    public StayReview updateReview(Long id, ReviewRequestDto dto) {

        StayReview review = getReviewById(id);

        review.setRating(dto.getRating());
        review.setComment(dto.getComment());

        return reviewRepository.save(review);
    }

    // Delete Review
    public void deleteReview(Long id) {

        StayReview review = getReviewById(id);

        reviewRepository.delete(review);
    }

    // Get Reviews By Property
    public List<StayReview> getReviewsByProperty(Long propertyId) {

        return reviewRepository.findByBookingListingId(propertyId);
    }
}