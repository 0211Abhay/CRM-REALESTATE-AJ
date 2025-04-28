package com.ajproject.realestatecrm.controller;

import com.ajproject.realestatecrm.beans.Broker;
import com.ajproject.realestatecrm.repository.BrokerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brokers")
public class BrokerController {
    
    @Autowired
    private BrokerRepository brokerRepository;
    
    // Create a new broker
    @PostMapping
    public ResponseEntity<Broker> createBroker(@RequestBody Broker broker) {
        // Validate required fields
        if (broker.getName() == null || broker.getName().trim().isEmpty() ||
            broker.getEmail() == null || broker.getEmail().trim().isEmpty() ||
            broker.getPasswordHash() == null || broker.getPasswordHash().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Check if email already exists
        if (brokerRepository.existsByEmail(broker.getEmail())) {
            return ResponseEntity.badRequest().build();
        }

        // Set creation and update timestamps
        broker.setCreatedAt(LocalDateTime.now());
        broker.setUpdatedAt(LocalDateTime.now());
        
        try {
            Broker savedBroker = brokerRepository.save(broker);
            return ResponseEntity.ok(savedBroker);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Get all brokers
    @GetMapping
    public ResponseEntity<List<Broker>> getAllBrokers() {
        List<Broker> brokers = brokerRepository.findAll();
        return ResponseEntity.ok(brokers);
    }
    
    // Get broker by ID
    @GetMapping("/{id}")
    public ResponseEntity<Broker> getBrokerById(@PathVariable Integer id) {
        Optional<Broker> broker = brokerRepository.findById(id);
        return broker.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    
    // Update broker
    @PutMapping("/{id}")
    public ResponseEntity<Broker> updateBroker(@PathVariable Integer id, @RequestBody Broker brokerDetails) {
        return brokerRepository.findById(id)
            .map(existingBroker -> {
                existingBroker.setName(brokerDetails.getName());
                existingBroker.setEmail(brokerDetails.getEmail());
                existingBroker.setPhone(brokerDetails.getPhone());
                existingBroker.setPasswordHash(brokerDetails.getPasswordHash());
                existingBroker.setUpdatedAt(LocalDateTime.now());
                
                Broker updatedBroker = brokerRepository.save(existingBroker);
                return ResponseEntity.ok(updatedBroker);
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    // Delete broker
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBroker(@PathVariable Integer id) {
        return brokerRepository.findById(id)
            .map(broker -> {
                brokerRepository.delete(broker);
                return ResponseEntity.ok().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    // Find broker by email
    @GetMapping("/email/{email}")
    public ResponseEntity<Broker> getBrokerByEmail(@PathVariable String email) {
        Optional<Broker> broker = brokerRepository.findByEmail(email);
        return broker.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    
    // Find broker by phone
    @GetMapping("/phone/{phone}")
    public ResponseEntity<Broker> getBrokerByPhone(@PathVariable String phone) {
        Optional<Broker> broker = brokerRepository.findByPhone(phone);
        return broker.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    
    // Search brokers by name
    @GetMapping("/search")
    public ResponseEntity<List<Broker>> searchBrokersByName(@RequestParam String name) {
        List<Broker> brokers = brokerRepository.findByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(brokers);
    }
    
    // Check if email exists
    @GetMapping("/check-email/{email}")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        boolean exists = brokerRepository.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }
}
