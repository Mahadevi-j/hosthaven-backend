package com.example.hosthaven.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hosthaven.entity.BookingReservation;
import com.example.hosthaven.entity.PropertyListing;
import com.example.hosthaven.entity.StayReview;
import com.example.hosthaven.entity.SystemUser;
import com.example.hosthaven.repository.BookingReservationRepository;
import com.example.hosthaven.repository.PropertyListingRepository;
import com.example.hosthaven.repository.StayReviewRepository;
import com.example.hosthaven.repository.SystemUserRepository;

@Service
public class OperationsService {

    private final SystemUserRepository systemUserRepository;
    private final PropertyListingRepository propertyListingRepository;
    private final BookingReservationRepository bookingReservationRepository;
    private final StayReviewRepository stayReviewRepository;

    public OperationsService(
            SystemUserRepository systemUserRepository,
            PropertyListingRepository propertyListingRepository,
            BookingReservationRepository bookingReservationRepository,
            StayReviewRepository stayReviewRepository) {

        this.systemUserRepository = systemUserRepository;
        this.propertyListingRepository = propertyListingRepository;
        this.bookingReservationRepository = bookingReservationRepository;
        this.stayReviewRepository = stayReviewRepository;
    }

    // Total Users
    public long getTotalUsers() {
        return systemUserRepository.count();
    }

    // Total Properties
    public long getTotalProperties() {
        return propertyListingRepository.count();
    }

    // Total Bookings
    public long getTotalBookings() {
        return bookingReservationRepository.count();
    }

    // Total Reviews
    public long getTotalReviews() {
        return stayReviewRepository.count();
    }

    // Get All Users
    public List<SystemUser> getAllUsers() {
        return systemUserRepository.findAll();
    }

    // Get All Properties
    public List<PropertyListing> getAllProperties() {
        return propertyListingRepository.findAll();
    }

    // Get All Bookings
    public List<BookingReservation> getAllBookings() {
        return bookingReservationRepository.findAll();
    }

    // Get All Reviews
    public List<StayReview> getAllReviews() {
        return stayReviewRepository.findAll();
    }
}