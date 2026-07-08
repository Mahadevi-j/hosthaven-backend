package com.example.hosthaven.service;

import java.util.List;

import com.example.hosthaven.dto.ListingRequestDto;
import com.example.hosthaven.entity.PropertyListing;

public interface IListingManagementService {

    // Create a new property listing
    PropertyListing createListing(ListingRequestDto listingRequestDto);

    // Get all property listings
    List<PropertyListing> getAllListings();

    // Get property listing by ID
    PropertyListing getListingById(Long id);

    // Update property listing
    PropertyListing updateListing(Long id, ListingRequestDto listingRequestDto);

    // Delete property listing
    void deleteListing(Long id);

    // Get all listings of a specific owner
    List<PropertyListing> getListingsByOwner(Long ownerId);

    // Update listing status
    PropertyListing updateStatus(Long id, String status);
}