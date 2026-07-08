package com.example.hosthaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hosthaven.entity.StayReview;

public interface StayReviewRepository extends JpaRepository<StayReview, Long> {

    List<StayReview> findByBookingListingId(Long propertyId);

}