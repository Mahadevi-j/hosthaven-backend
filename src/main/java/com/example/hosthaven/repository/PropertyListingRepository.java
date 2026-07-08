package com.example.hosthaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hosthaven.entity.PropertyListing;

public interface PropertyListingRepository extends JpaRepository<PropertyListing, Long> {

    List<PropertyListing> findByOwnerId(Long ownerId);

    List<PropertyListing> findByStatus(String status);

}