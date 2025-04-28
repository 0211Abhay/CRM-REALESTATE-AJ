package com.ajproject.realestatecrm.repository;

import com.ajproject.realestatecrm.beans.Client;
import com.ajproject.realestatecrm.beans.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    
    // Find client by email
    Optional<Client> findByEmail(String email);
    
    // Find client by phone number
    Optional<Client> findByPhone(String phone);
    
    // Find all clients for a specific broker
    List<Client> findByBroker(Broker broker);
    
    // Find clients by name containing the search term (case-insensitive)
    List<Client> findByNameContainingIgnoreCase(String name);
    
    // Check if email exists
    boolean existsByEmail(String email);
    
    // Find clients by broker and name containing search term
    List<Client> findByBrokerAndNameContainingIgnoreCase(Broker broker, String name);
    
    // Find clients by address containing search term
    List<Client> findByAddressContainingIgnoreCase(String address);
}
