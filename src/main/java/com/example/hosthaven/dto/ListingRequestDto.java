package com.example.hosthaven.dto;

import java.math.BigDecimal;

public class ListingRequestDto {

    private Long ownerId;
    private String title;
    private String description;
    private String address;
    private BigDecimal basePrice;
    private String benefits;

    public ListingRequestDto() {
    }

    public ListingRequestDto(Long ownerId, String title, String description,
                             String address, BigDecimal basePrice,
                             String benefits) {
        this.ownerId = ownerId;
        this.title = title;
        this.description = description;
        this.address = address;
        this.basePrice = basePrice;
        this.benefits = benefits;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }
}