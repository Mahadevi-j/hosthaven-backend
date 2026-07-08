package com.example.hosthaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hosthaven.entity.BookingReservation;

public interface BookingReservationRepository extends JpaRepository<BookingReservation, Long> {

    List<BookingReservation> findByGuestId(Long guestId);

    List<BookingReservation> findByListingId(Long listingId);

    List<BookingReservation> findByStatus(String status);

}