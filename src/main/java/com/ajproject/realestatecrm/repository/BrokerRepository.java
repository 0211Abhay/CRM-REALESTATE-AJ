package com.ajproject.realestatecrm.repository;

import com.ajproject.realestatecrm.beans.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrokerRepository extends JpaRepository<Broker, Integer> {
    
    // Find broker by email (useful for login/authentication)
    Optional<Broker> findByEmail(String email);
    
    // Check if email exists (useful for registration)
    boolean existsByEmail(String email);
    
    // Find broker by phone number
    Optional<Broker> findByPhone(String phone);
    
    // Find brokers by name containing the search term (case-insensitive)
    List<Broker> findByNameContainingIgnoreCase(String name);
}
