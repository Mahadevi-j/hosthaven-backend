package com.example.hosthaven.dto;

public class ReviewRequestDto {

    private Long bookingId;
    private Integer rating;
    private String comment;

    public ReviewRequestDto() {
    }

    public ReviewRequestDto(Long bookingId, Integer rating,
                            String comment) {
        this.bookingId = bookingId;
        this.rating = rating;
        this.comment = comment;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}