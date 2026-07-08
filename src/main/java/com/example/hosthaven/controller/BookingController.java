package com.example.hosthaven.controller;

import com.example.hosthaven.dto.BookingRequestDto;
import com.example.hosthaven.entity.BookingReservation;
import com.example.hosthaven.service.BookingOrchestrator;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingOrchestrator bookingService;

    public BookingController(BookingOrchestrator bookingService) {
        this.bookingService = bookingService;
    }

    // Create Booking
    @PreAuthorize("hasRole('GUEST')")
    @PostMapping
    public ResponseEntity<BookingReservation> createBooking(
            @Valid @RequestBody BookingRequestDto bookingRequestDto) {

        BookingReservation booking = bookingService.createBooking(bookingRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(booking);
    }

    // Get All Bookings (Guest)
    @PreAuthorize("hasRole('GUEST')")
    @GetMapping
    public ResponseEntity<List<BookingReservation>> getAllBookings() {

        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    // Get Booking By Id
    @PreAuthorize("hasAnyRole('GUEST','PROPERTY_OWNER','HOSPITALITY_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<BookingReservation> getBookingById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                bookingService.getBookingById(id));
    }

    // Update Booking
    @PreAuthorize("hasRole('GUEST')")
    @PutMapping("/{id}")
    public ResponseEntity<BookingReservation> updateBooking(
            @PathVariable Long id,
            @Valid @RequestBody BookingRequestDto bookingRequestDto) {

        return ResponseEntity.ok(
                bookingService.updateBooking(id, bookingRequestDto));
    }

    // Delete Booking
    @PreAuthorize("hasRole('HOSPITALITY_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(
            @PathVariable Long id) {

        bookingService.deleteBooking(id);

        return ResponseEntity.ok("Booking deleted successfully");
    }

    // Cancel Booking
    @PreAuthorize("hasAnyRole('GUEST','PROPERTY_OWNER')")
    @PostMapping("/{id}/cancel")
    public ResponseEntity<BookingReservation> cancelBooking(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                bookingService.cancelBooking(id));
    }

    // Confirm Booking
    @PreAuthorize("hasRole('PROPERTY_OWNER')")
    @PostMapping("/{id}/confirm")
    public ResponseEntity<BookingReservation> confirmBooking(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                bookingService.confirmBooking(id));
    }

    // Check-In
    @PreAuthorize("hasRole('PROPERTY_OWNER')")
    @PostMapping("/{id}/check-in")
    public ResponseEntity<BookingReservation> checkIn(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                bookingService.checkIn(id));
    }

    // Check-Out
    @PreAuthorize("hasRole('PROPERTY_OWNER')")
    @PostMapping("/{id}/check-out")
    public ResponseEntity<BookingReservation> checkOut(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                bookingService.checkOut(id));
    }
}