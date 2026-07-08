package com.example.hosthaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hosthaven.entity.MaintenanceTask;

public interface MaintenanceTaskRepository extends JpaRepository<MaintenanceTask, Long> {

    List<MaintenanceTask> findByStatus(String status);

    List<MaintenanceTask> findByListingId(Long listingId);

}