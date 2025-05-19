package com.ajproject.realestatecrm.repository;

import com.ajproject.realestatecrm.beans.RentPayment;
import com.ajproject.realestatecrm.beans.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentPaymentRepository extends JpaRepository<RentPayment, Integer> {
    
    // Find payments by rental
    List<RentPayment> findByRental(Rental rental);
    
    // Find payments by status
    List<RentPayment> findByStatus(String status);
    
    // Find payments by due date range
    List<RentPayment> findByDueDateBetween(LocalDate startDate, LocalDate endDate);
    
    // Find payments by payment date range
    List<RentPayment> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);
    
    // Find payments by rental and status
    List<RentPayment> findByRentalAndStatus(Rental rental, String status);
    
    // Find payments by rental and month
    List<RentPayment> findByRentalAndMonth(Rental rental, String month);
    
    // Find payments by broker ID (using JPQL to navigate through rental to broker)
    @Query("SELECT p FROM RentPayment p WHERE p.rental.broker.brokerId = :brokerId")
    List<RentPayment> findByBrokerId(@Param("brokerId") Integer brokerId);
    
    // Find paid payments by broker ID
    @Query("SELECT p FROM RentPayment p WHERE p.rental.broker.brokerId = :brokerId AND p.status = 'paid'")
    List<RentPayment> findPaidPaymentsByBrokerId(@Param("brokerId") Integer brokerId);
    
    // Find overdue payments by broker ID
    @Query("SELECT p FROM RentPayment p WHERE p.rental.broker.brokerId = :brokerId AND p.status = 'overdue'")
    List<RentPayment> findOverduePaymentsByBrokerId(@Param("brokerId") Integer brokerId);
    
    // Find due payments by broker ID
    @Query("SELECT p FROM RentPayment p WHERE p.rental.broker.brokerId = :brokerId AND p.status = 'due'")
    List<RentPayment> findDuePaymentsByBrokerId(@Param("brokerId") Integer brokerId);
    
    // Find payments by client ID
    @Query("SELECT p FROM RentPayment p WHERE p.rental.client.clientId = :clientId")
    List<RentPayment> findByClientId(@Param("clientId") Integer clientId);
    
    // Find payments by property ID
    @Query("SELECT p FROM RentPayment p WHERE p.rental.property.propertyId = :propertyId")
    List<RentPayment> findByPropertyId(@Param("propertyId") Integer propertyId);
}
