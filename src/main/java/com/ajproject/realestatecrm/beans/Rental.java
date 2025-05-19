package com.ajproject.realestatecrm.beans;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "rental_management")
public class Rental {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Integer rentalId;
    
    @ManyToOne
    @JoinColumn(name = "broker_id", nullable = false)
    private Broker broker;
    
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    
    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;
    
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    
    @Column(name = "rent_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal rentAmount;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RentalStatus status = RentalStatus.Active;
    
    @Column(name = "notes", columnDefinition = "text")
    private String notes;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;
    
    // Enum for rental status
    public enum RentalStatus {
        Active, Completed, Terminated
    }
    
    // Default constructor
    public Rental() {
    }
    
    // Getters and Setters
    public Integer getRentalId() {
        return rentalId;
    }
    
    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
    }
    
    public Broker getBroker() {
        return broker;
    }
    
    public void setBroker(Broker broker) {
        this.broker = broker;
    }
    
    public Client getClient() {
        return client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public Property getProperty() {
        return property;
    }
    
    public void setProperty(Property property) {
        this.property = property;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public BigDecimal getRentAmount() {
        return rentAmount;
    }
    
    public void setRentAmount(BigDecimal rentAmount) {
        this.rentAmount = rentAmount;
    }
    
    public RentalStatus getStatus() {
        return status;
    }
    
    public void setStatus(RentalStatus status) {
        this.status = status;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
    
    // PrePersist method to set createdAt before saving
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }
}
