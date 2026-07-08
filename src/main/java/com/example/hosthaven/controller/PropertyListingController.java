package com.example.hosthaven.controller;

import java.util.List;

import com.example.hosthaven.dto.ListingRequestDto;
import com.example.hosthaven.entity.PropertyListing;
import com.example.hosthaven.service.ListingManagementService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/listings")
@CrossOrigin(origins = "*")
public class PropertyListingController {

    private final ListingManagementService listingService;

    public PropertyListingController(ListingManagementService listingService) {
        this.listingService = listingService;
    }

    // Create Property Listing
    @PreAuthorize("hasRole('PROPERTY_OWNER')")
    @PostMapping
    public ResponseEntity<PropertyListing> createListing(
            @Valid @RequestBody ListingRequestDto listingRequestDto) {

        PropertyListing listing = listingService.createListing(listingRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(listing);
    }

    // Get All Property Listings
    @PreAuthorize("hasAnyRole('GUEST','PROPERTY_OWNER','HOSPITALITY_ADMIN')")
    @GetMapping
    public ResponseEntity<List<PropertyListing>> getAllListings() {

        return ResponseEntity.ok(
                listingService.getAllListings());
    }

    // Get Property Listing By Id
    @PreAuthorize("hasAnyRole('GUEST','PROPERTY_OWNER','HOSPITALITY_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PropertyListing> getListingById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                listingService.getListingById(id));
    }

    // Update Property Listing
    @PreAuthorize("hasRole('PROPERTY_OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<PropertyListing> updateListing(
            @PathVariable Long id,
            @Valid @RequestBody ListingRequestDto listingRequestDto) {

        return ResponseEntity.ok(
                listingService.updateListing(id, listingRequestDto));
    }

    // Delete Property Listing
    @PreAuthorize("hasRole('PROPERTY_OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteListing(
            @PathVariable Long id) {

        listingService.deleteListing(id);

        return ResponseEntity.ok(
                "Property Listing deleted successfully");
    }

    // Get Listings By Owner
    @PreAuthorize("hasAnyRole('PROPERTY_OWNER','HOSPITALITY_ADMIN')")
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<PropertyListing>> getListingsByOwner(
            @PathVariable Long ownerId) {

        return ResponseEntity.ok(
                listingService.getListingsByOwner(ownerId));
    }

    // Update Listing Status
    @PreAuthorize("hasRole('HOSPITALITY_ADMIN')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<PropertyListing> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                listingService.updateStatus(id, status));
    }
}