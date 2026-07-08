package com.example.hosthaven.service;

import java.util.List;

import com.example.hosthaven.dto.BookingRequestDto;
import com.example.hosthaven.entity.BookingReservation;

public interface IBookingOrchestrator {

    // Create a booking
    BookingReservation createBooking(BookingRequestDto bookingRequestDto);

    // Get all bookings
    List<BookingReservation> getAllBookings();

    // Get booking by ID
    BookingReservation getBookingById(Long id);

    // Update booking
    BookingReservation updateBooking(Long id, BookingRequestDto bookingRequestDto);

    // Delete booking
    void deleteBooking(Long id);

    // Cancel booking
    BookingReservation cancelBooking(Long id);

    // Confirm booking
    BookingReservation confirmBooking(Long id);

    // Check-in
    BookingReservation checkIn(Long id);

    // Check-out
    BookingReservation checkOut(Long id);
}