package com.ajproject.realestatecrm.controller;

import com.ajproject.realestatecrm.beans.Rental;
import com.ajproject.realestatecrm.beans.Property;
import com.ajproject.realestatecrm.beans.Client;
import com.ajproject.realestatecrm.beans.Broker;
import com.ajproject.realestatecrm.repository.RentalRepository;
import com.ajproject.realestatecrm.repository.PropertyRepository;
import com.ajproject.realestatecrm.repository.ClientRepository;
import com.ajproject.realestatecrm.repository.BrokerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/rentals")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RentalController {
    
    @Autowired
    private RentalRepository rentalRepository;
    
    @Autowired
    private PropertyRepository propertyRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private BrokerRepository brokerRepository;
    
    // Create a new rental
    @PostMapping("/createRental")
    public ResponseEntity<?> createRental(@RequestBody Rental rental) {
        // Validate required fields
        if (rental.getProperty() == null || rental.getClient() == null ||
            rental.getBroker() == null || rental.getStartDate() == null ||
            rental.getEndDate() == null || rental.getRentAmount() == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Missing required fields");
            return ResponseEntity.badRequest().body(response);
        }
        
        try {
            // Check if property exists
            Optional<Property> property = propertyRepository.findById(rental.getProperty().getPropertyId());
            if (!property.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Property not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            // Check if client exists
            Optional<Client> client = clientRepository.findById(rental.getClient().getClientId());
            if (!client.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Client not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            // Check if broker exists
            Optional<Broker> broker = brokerRepository.findById(rental.getBroker().getBrokerId());
            if (!broker.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Broker not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            // Check for overlapping rentals
            List<Rental> overlappingRentals1 = rentalRepository.findOverlappingRentals(
                    rental.getClient().getClientId(), rental.getStartDate(), rental.getEndDate());
            
            List<Rental> overlappingRentals2 = rentalRepository.findEncompassedRentals(
                    rental.getClient().getClientId(), rental.getStartDate(), rental.getEndDate());
            
            if (!overlappingRentals1.isEmpty() || !overlappingRentals2.isEmpty()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Redundant rental entry");
                response.put("message", "This client already has a rental agreement that overlaps with the specified dates.");
                response.put("details", "Please check the client's existing rentals and choose different dates.");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Set creation timestamp
            rental.setCreatedAt(LocalDate.now());
            
            // Set default status if not provided
            if (rental.getStatus() == null) {
                rental.setStatus(Rental.RentalStatus.Active);
            }
            
            Rental savedRental = rentalRepository.save(rental);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Rental agreement created successfully");
            response.put("rental", savedRental);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error creating rental: " + e.getMessage());
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Get all rentals
    @GetMapping("/getAllRentals")
    public ResponseEntity<?> getAllRentals() {
        try {
            List<Rental> rentals = rentalRepository.findAll();
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Rental agreements fetched successfully");
            response.put("rentals", rentals);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Get rental by ID
    @GetMapping("/getRental/{rental_id}")
    public ResponseEntity<?> getRentalById(@PathVariable("rental_id") Integer id) {
        try {
            Optional<Rental> rental = rentalRepository.findById(id);
            
            if (!rental.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Rental agreement not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Rental agreement fetched successfully");
            response.put("rental", rental.get());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Update rental
    @PutMapping("/updateRental/{rental_id}")
    public ResponseEntity<?> updateRental(@PathVariable("rental_id") Integer id, @RequestBody Rental rentalDetails) {
        try {
            Optional<Rental> rentalOpt = rentalRepository.findById(id);
            
            if (!rentalOpt.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Rental agreement not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            Rental rental = rentalOpt.get();
            
            // Verify related entities if they're being updated
            if (rentalDetails.getProperty() != null) {
                Optional<Property> property = propertyRepository.findById(rentalDetails.getProperty().getPropertyId());
                if (!property.isPresent()) {
                    Map<String, String> response = new HashMap<>();
                    response.put("error", "Property not found");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                }
                rental.setProperty(rentalDetails.getProperty());
            }
            
            if (rentalDetails.getClient() != null) {
                Optional<Client> client = clientRepository.findById(rentalDetails.getClient().getClientId());
                if (!client.isPresent()) {
                    Map<String, String> response = new HashMap<>();
                    response.put("error", "Client not found");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                }
                rental.setClient(rentalDetails.getClient());
            }
            
            if (rentalDetails.getBroker() != null) {
                Optional<Broker> broker = brokerRepository.findById(rentalDetails.getBroker().getBrokerId());
                if (!broker.isPresent()) {
                    Map<String, String> response = new HashMap<>();
                    response.put("error", "Broker not found");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                }
                rental.setBroker(rentalDetails.getBroker());
            }
            
            // Update other fields
            if (rentalDetails.getStartDate() != null) {
                rental.setStartDate(rentalDetails.getStartDate());
            }
            if (rentalDetails.getEndDate() != null) {
                rental.setEndDate(rentalDetails.getEndDate());
            }
            if (rentalDetails.getRentAmount() != null) {
                rental.setRentAmount(rentalDetails.getRentAmount());
            }
            if (rentalDetails.getStatus() != null) {
                rental.setStatus(rentalDetails.getStatus());
            }
            if (rentalDetails.getNotes() != null) {
                rental.setNotes(rentalDetails.getNotes());
            }
            
            Rental updatedRental = rentalRepository.save(rental);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Rental agreement updated successfully");
            response.put("rental", updatedRental);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Delete rental
    @DeleteMapping("/deleteRental/{rental_id}")
    public ResponseEntity<?> deleteRental(@PathVariable("rental_id") Integer id) {
        try {
            Optional<Rental> rental = rentalRepository.findById(id);
            
            if (!rental.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Rental agreement not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            rentalRepository.delete(rental.get());
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Rental agreement deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Change rental status
    @PatchMapping("/changeRentalStatus/{rental_id}")
    public ResponseEntity<?> changeRentalStatus(@PathVariable("rental_id") Integer id, @RequestBody Map<String, String> statusRequest) {
        try {
            String status = statusRequest.get("status");
            
            if (status == null || !isValidStatus(status)) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Invalid status value");
                return ResponseEntity.badRequest().body(response);
            }
            
            Optional<Rental> rentalOpt = rentalRepository.findById(id);
            
            if (!rentalOpt.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Rental agreement not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            Rental rental = rentalOpt.get();
            rental.setStatus(Rental.RentalStatus.valueOf(status));
            
            Rental updatedRental = rentalRepository.save(rental);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Rental status updated successfully");
            response.put("rental", updatedRental);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Helper method to validate status
    private boolean isValidStatus(String status) {
        try {
            Rental.RentalStatus.valueOf(status);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    // Find rentals by broker
    @GetMapping("/getRentalsByBroker/{broker_id}")
    public ResponseEntity<?> getRentalsByBroker(@PathVariable("broker_id") Integer brokerId) {
        try {
            Optional<Broker> broker = brokerRepository.findById(brokerId);
            if (!broker.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Broker not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            List<Rental> rentals = rentalRepository.findByBroker(broker.get());
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Broker rental agreements fetched successfully");
            response.put("rentals", rentals);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Find rentals by client
    @GetMapping("/getRentalsByClient/{client_id}")
    public ResponseEntity<?> getRentalsByClient(@PathVariable("client_id") Integer clientId) {
        try {
            Optional<Client> client = clientRepository.findById(clientId);
            if (!client.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Client not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            List<Rental> rentals = rentalRepository.findByClient(client.get());
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Client rental agreements fetched successfully");
            response.put("rentals", rentals);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Find rentals by property
    @GetMapping("/getRentalsByProperty/{property_id}")
    public ResponseEntity<?> getRentalsByProperty(@PathVariable("property_id") Integer propertyId) {
        try {
            Optional<Property> property = propertyRepository.findById(propertyId);
            if (!property.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Property not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            List<Rental> rentals = rentalRepository.findByProperty(property.get());
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Property rental agreements fetched successfully");
            response.put("rentals", rentals);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Find rentals by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Rental>> getRentalsByStatus(@PathVariable Rental.RentalStatus status) {
        List<Rental> rentals = rentalRepository.findByStatus(status);
        return ResponseEntity.ok(rentals);
    }
    
    // Find rentals by date range (start date)
    @GetMapping("/startDateRange")
    public ResponseEntity<List<Rental>> getRentalsByStartDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<Rental> rentals = rentalRepository.findByStartDateBetween(startDate, endDate);
        return ResponseEntity.ok(rentals);
    }
    
    // Find rentals by date range (end date)
    @GetMapping("/endDateRange")
    public ResponseEntity<List<Rental>> getRentalsByEndDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<Rental> rentals = rentalRepository.findByEndDateBetween(startDate, endDate);
        return ResponseEntity.ok(rentals);
    }
    
    // Find active rentals for a broker
    @GetMapping("/broker/{brokerId}/status/{status}")
    public ResponseEntity<List<Rental>> getRentalsByBrokerAndStatus(
            @PathVariable Integer brokerId,
            @PathVariable Rental.RentalStatus status) {
        Optional<Broker> broker = brokerRepository.findById(brokerId);
        if (!broker.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        List<Rental> rentals = rentalRepository.findByBrokerAndStatus(broker.get(), status);
        return ResponseEntity.ok(rentals);
    }
}
