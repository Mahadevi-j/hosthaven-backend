package com.example.hosthaven.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "maintenance_tasks")
public class MaintenanceTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listing_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PropertyListing listing;

    @Column(name = "task_type", nullable = false)
    private String taskType;

    @Column(nullable = false)
    private String status = "TODO";

    @Column(name = "scheduled_date", nullable = false)
    private LocalDate scheduledDate;

    // Default Constructor
    public MaintenanceTask() {
    }

    // Parameterized Constructor
    public MaintenanceTask(Long id, PropertyListing listing,
                           String taskType, String status,
                           LocalDate scheduledDate) {
        this.id = id;
        this.listing = listing;
        this.taskType = taskType;
        this.status = status;
        this.scheduledDate = scheduledDate;
    }

    // Constructor for creating a task
    public MaintenanceTask(PropertyListing listing,
                           String taskType,
                           LocalDate scheduledDate) {
        this.listing = listing;
        this.taskType = taskType;
        this.status = "TODO";
        this.scheduledDate = scheduledDate;
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

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    @Override
    public String toString() {
        return "MaintenanceTask{" +
                "id=" + id +
                ", listing=" + listing +
                ", taskType='" + taskType + '\'' +
                ", status='" + status + '\'' +
                ", scheduledDate=" + scheduledDate +
                '}';
    }
}