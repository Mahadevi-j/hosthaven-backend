package com.example.hosthaven.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "booking_reservations")
public class BookingReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listing_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PropertyListing listing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private SystemUser guest;

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    private String status = "PENDING";

    @Column(name = "is_rated")
    private Boolean isRated = false;

    // Default Constructor
    public BookingReservation() {
    }

    // Parameterized Constructor
    public BookingReservation(Long id, PropertyListing listing,
                              SystemUser guest, LocalDate checkInDate,
                              LocalDate checkOutDate, BigDecimal totalPrice,
                              String status, Boolean isRated) {
        this.id = id;
        this.listing = listing;
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.status = status;
        this.isRated = isRated;
    }

    // Constructor for creating booking
    public BookingReservation(PropertyListing listing,
                              SystemUser guest,
                              LocalDate checkInDate,
                              LocalDate checkOutDate,
                              BigDecimal totalPrice) {
        this.listing = listing;
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.status = "PENDING";
        this.isRated = false;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PropertyListing getListing() {
        return listing;
    }

    public void setListing(PropertyListing listing) {
        this.listing = listing;
    }

    public SystemUser getGuest() {
        return guest;
    }

    public void setGuest(SystemUser guest) {
        this.guest = guest;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsRated() {
        return isRated;
    }

    public void setIsRated(Boolean isRated) {
        this.isRated = isRated;
    }

    @Override
    public String toString() {
        return "BookingReservation{" +
                "id=" + id +
                ", listing=" + listing +
                ", guest=" + guest +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", isRated=" + isRated +
                '}';
    }
}