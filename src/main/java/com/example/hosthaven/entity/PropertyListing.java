package com.example.hosthaven.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "property_listings")
public class PropertyListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private SystemUser owner;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private String address;

    @Column(name = "base_price", nullable = false)
    private BigDecimal basePrice;

    @Column(nullable = false)
    private String status = "DRAFT";

    @Column(name = "average_rating")
    private BigDecimal averageRating = BigDecimal.ZERO;

    @Column(columnDefinition = "TEXT")
    private String benefits;

    // Default Constructor
    public PropertyListing() {
    }

    // Parameterized Constructor
    public PropertyListing(Long id, SystemUser owner, String title,
                           String description, String address,
                           BigDecimal basePrice, String status,
                           BigDecimal averageRating, String benefits) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.description = description;
        this.address = address;
        this.basePrice = basePrice;
        this.status = status;
        this.averageRating = averageRating;
        this.benefits = benefits;
    }

    // Constructor for creating listing
    public PropertyListing(SystemUser owner, String title,
                           String description, String address,
                           BigDecimal basePrice, String benefits) {
        this.owner = owner;
        this.title = title;
        this.description = description;
        this.address = address;
        this.basePrice = basePrice;
        this.benefits = benefits;
        this.status = "DRAFT";
        this.averageRating = BigDecimal.ZERO;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SystemUser getOwner() {
        return owner;
    }

    public void setOwner(SystemUser owner) {
        this.owner = owner;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    @Override
    public String toString() {
        return "PropertyListing{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", basePrice=" + basePrice +
                ", status='" + status + '\'' +
                ", averageRating=" + averageRating +
                ", benefits='" + benefits + '\'' +
                '}';
    }
}