package com.example.hosthaven.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hosthaven.dto.ListingRequestDto;
import com.example.hosthaven.entity.PropertyListing;
import com.example.hosthaven.entity.SystemUser;
import com.example.hosthaven.exception.ResourceNotFoundException;
import com.example.hosthaven.repository.PropertyListingRepository;
import com.example.hosthaven.repository.SystemUserRepository;

@Service
public class ListingManagementService implements IListingManagementService {

    private final PropertyListingRepository propertyListingRepository;
    private final SystemUserRepository systemUserRepository;

    public ListingManagementService(PropertyListingRepository propertyListingRepository,
                                    SystemUserRepository systemUserRepository) {
        this.propertyListingRepository = propertyListingRepository;
        this.systemUserRepository = systemUserRepository;
    }

    @Override
    public PropertyListing createListing(ListingRequestDto listingRequestDto) {

        SystemUser owner = systemUserRepository.findById(listingRequestDto.getOwnerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Owner not found"));

        PropertyListing listing = new PropertyListing();

        listing.setOwner(owner);
        listing.setTitle(listingRequestDto.getTitle());
        listing.setDescription(listingRequestDto.getDescription());
        listing.setAddress(listingRequestDto.getAddress());
        listing.setBasePrice(listingRequestDto.getBasePrice());
        listing.setBenefits(listingRequestDto.getBenefits());
        listing.setStatus("ACTIVE");

        return propertyListingRepository.save(listing);
    }

    @Override
    public List<PropertyListing> getAllListings() {
        return propertyListingRepository.findAll();
    }

    @Override
    public PropertyListing getListingById(Long id) {

        return propertyListingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property Listing not found"));
    }

    @Override
    public PropertyListing updateListing(Long id,
                                         ListingRequestDto listingRequestDto) {

        PropertyListing listing = getListingById(id);

        listing.setTitle(listingRequestDto.getTitle());
        listing.setDescription(listingRequestDto.getDescription());
        listing.setAddress(listingRequestDto.getAddress());
        listing.setBasePrice(listingRequestDto.getBasePrice());
        listing.setBenefits(listingRequestDto.getBenefits());

        return propertyListingRepository.save(listing);
    }

    @Override
    public void deleteListing(Long id) {

        PropertyListing listing = getListingById(id);

        propertyListingRepository.delete(listing);
    }

    @Override
    public List<PropertyListing> getListingsByOwner(Long ownerId) {

        return propertyListingRepository.findByOwnerId(ownerId);
    }

    @Override
    public PropertyListing updateStatus(Long id, String status) {

        PropertyListing listing = getListingById(id);

        listing.setStatus(status);

        return propertyListingRepository.save(listing);
    }
}