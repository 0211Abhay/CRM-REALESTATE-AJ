package com.ajproject.realestatecrm.controller;

import com.ajproject.realestatecrm.beans.Client;
import com.ajproject.realestatecrm.beans.Broker;
import com.ajproject.realestatecrm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    
    @Autowired
    private ClientRepository clientRepository;
    
    // Create a new client
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        // Validate required fields
        if (client.getName() == null || client.getName().trim().isEmpty() ||
            client.getBroker() == null) {
            return ResponseEntity.badRequest().build();
        }

        // Check if email already exists (if provided)
        if (client.getEmail() != null && !client.getEmail().trim().isEmpty() && 
            clientRepository.existsByEmail(client.getEmail())) {
            return ResponseEntity.badRequest().build();
        }

        // Set creation timestamp
        client.setCreatedAt(LocalDateTime.now());
        
        try {
            Client savedClient = clientRepository.save(client);
            return ResponseEntity.ok(savedClient);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Get all clients
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return ResponseEntity.ok(clients);
    }
    
    // Get client by ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    
    // Update client
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody Client clientDetails) {
        return clientRepository.findById(id)
            .map(existingClient -> {
                existingClient.setName(clientDetails.getName());
                existingClient.setEmail(clientDetails.getEmail());
                existingClient.setPhone(clientDetails.getPhone());
                existingClient.setAddress(clientDetails.getAddress());
                existingClient.setBroker(clientDetails.getBroker());
                
                Client updatedClient = clientRepository.save(existingClient);
                return ResponseEntity.ok(updatedClient);
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    // Get the schedule repository
    @Autowired
    private com.ajproject.realestatecrm.repository.ScheduleRepository scheduleRepository;
    
    // Delete client
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        return clientRepository.findById(id)
            .map(client -> {
                try {
                    // First, delete all schedules associated with this client
                    scheduleRepository.deleteByClient(client);
                    
                    // Then delete the client
                    clientRepository.delete(client);
                    return ResponseEntity.ok().<Void>build();
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.status(500).<Void>build();
                }
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    // Find client by email
    @GetMapping("/email/{email}")
    public ResponseEntity<Client> getClientByEmail(@PathVariable String email) {
        Optional<Client> client = clientRepository.findByEmail(email);
        return client.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    
    // Find client by phone
    @GetMapping("/phone/{phone}")
    public ResponseEntity<Client> getClientByPhone(@PathVariable String phone) {
        Optional<Client> client = clientRepository.findByPhone(phone);
        return client.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    
    // Find clients by broker
    @GetMapping("/broker/{brokerId}")
    public ResponseEntity<List<Client>> getClientsByBroker(@PathVariable Integer brokerId) {
        Broker broker = new Broker();
        broker.setBrokerId(brokerId);
        List<Client> clients = clientRepository.findByBroker(broker);
        return ResponseEntity.ok(clients);
    }
    
    // Search clients by name
    @GetMapping("/search/name")
    public ResponseEntity<List<Client>> searchClientsByName(@RequestParam String name) {
        List<Client> clients = clientRepository.findByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(clients);
    }
    
    // Search clients by address
    @GetMapping("/search/address")
    public ResponseEntity<List<Client>> searchClientsByAddress(@RequestParam String address) {
        List<Client> clients = clientRepository.findByAddressContainingIgnoreCase(address);
        return ResponseEntity.ok(clients);
    }
    
    // Search clients by broker and name
    @GetMapping("/search/broker/{brokerId}/name")
    public ResponseEntity<List<Client>> searchClientsByBrokerAndName(
            @PathVariable Integer brokerId, 
            @RequestParam String name) {
        Broker broker = new Broker();
        broker.setBrokerId(brokerId);
        List<Client> clients = clientRepository.findByBrokerAndNameContainingIgnoreCase(broker, name);
        return ResponseEntity.ok(clients);
    }
    
    // Check if email exists
    @GetMapping("/check-email/{email}")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        boolean exists = clientRepository.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }
}
