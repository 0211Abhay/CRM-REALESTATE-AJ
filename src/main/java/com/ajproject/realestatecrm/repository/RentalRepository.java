package com.ajproject.realestatecrm.repository;

import com.ajproject.realestatecrm.beans.Rental;
import com.ajproject.realestatecrm.beans.Broker;
import com.ajproject.realestatecrm.beans.Client;
import com.ajproject.realestatecrm.beans.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    // Find rentals by broker
    List<Rental> findByBroker(Broker broker);

    // Find rentals by client
    List<Rental> findByClient(Client client);

    // Find rentals by property
    List<Rental> findByProperty(Property property);

    // Find rentals by status
    List<Rental> findByStatus(Rental.RentalStatus status);

    // Find rentals by date range (start date)
    List<Rental> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    // Find rentals by date range (end date)
    List<Rental> findByEndDateBetween(LocalDate startDate, LocalDate endDate);

    // Find active rentals for a broker
    List<Rental> findByBrokerAndStatus(Broker broker, Rental.RentalStatus status);

    // Find rentals by broker and date range
    List<Rental> findByBrokerAndStartDateBetween(Broker broker, LocalDate startDate, LocalDate endDate);

    // Find rentals by property and status
    List<Rental> findByPropertyAndStatus(Property property, Rental.RentalStatus status);

    // Find active rentals for a client
    List<Rental> findByClientAndStatus(Client client, Rental.RentalStatus status);

    // Find overlapping rentals for a client
    @Query("SELECT r FROM Rental r WHERE r.client.clientId = :clientId AND r.startDate <= :endDate AND r.endDate >= :startDate")
    List<Rental> findOverlappingRentals(
            @Param("clientId") Integer clientId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    // Find rentals where new rental encompasses an existing rental
    @Query("SELECT r FROM Rental r WHERE r.client.clientId = :clientId AND r.startDate >= :startDate AND r.endDate <= :endDate")
    List<Rental> findEncompassedRentals(
            @Param("clientId") Integer clientId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}
