package com.example.hosthaven.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hosthaven.dto.BookingRequestDto;
import com.example.hosthaven.entity.BookingReservation;
import com.example.hosthaven.entity.PropertyListing;
import com.example.hosthaven.entity.SystemUser;
import com.example.hosthaven.exception.ResourceNotFoundException;
import com.example.hosthaven.repository.BookingReservationRepository;
import com.example.hosthaven.repository.PropertyListingRepository;
import com.example.hosthaven.repository.SystemUserRepository;

@Service
public class BookingOrchestrator implements IBookingOrchestrator {

    private final BookingReservationRepository bookingRepository;
    private final PropertyListingRepository propertyRepository;
    private final SystemUserRepository userRepository;

    public BookingOrchestrator(
            BookingReservationRepository bookingRepository,
            PropertyListingRepository propertyRepository,
            SystemUserRepository userRepository) {

        this.bookingRepository = bookingRepository;
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BookingReservation createBooking(BookingRequestDto dto) {

        PropertyListing listing = propertyRepository.findById(dto.getListingId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property not found"));

        SystemUser guest = userRepository.findById(dto.getGuestId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Guest not found"));

        BookingReservation booking = new BookingReservation();

        booking.setListing(listing);
        booking.setGuest(guest);
        booking.setCheckInDate(dto.getCheckInDate());
        booking.setCheckOutDate(dto.getCheckOutDate());
        booking.setTotalPrice(dto.getTotalPrice());
        booking.setStatus("PENDING");
        booking.setIsRated(false);

        return bookingRepository.save(booking);
    }

    @Override
    public List<BookingReservation> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public BookingReservation getBookingById(Long id) {

        return bookingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found"));
    }

    @Override
    public BookingReservation updateBooking(Long id,
                                            BookingRequestDto dto) {

        BookingReservation booking = getBookingById(id);

        booking.setCheckInDate(dto.getCheckInDate());
        booking.setCheckOutDate(dto.getCheckOutDate());
        booking.setTotalPrice(dto.getTotalPrice());

        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long id) {

        BookingReservation booking = getBookingById(id);

        bookingRepository.delete(booking);
    }

    @Override
    public BookingReservation cancelBooking(Long id) {

        BookingReservation booking = getBookingById(id);

        booking.setStatus("CANCELLED");

        return bookingRepository.save(booking);
    }

    @Override
    public BookingReservation confirmBooking(Long id) {

        BookingReservation booking = getBookingById(id);

        booking.setStatus("CONFIRMED");

        return bookingRepository.save(booking);
    }

    @Override
    public BookingReservation checkIn(Long id) {

        BookingReservation booking = getBookingById(id);

        booking.setStatus("CHECKED_IN");

        return bookingRepository.save(booking);
    }

    @Override
    public BookingReservation checkOut(Long id) {

        BookingReservation booking = getBookingById(id);

        booking.setStatus("COMPLETED");

        return bookingRepository.save(booking);
    }
}