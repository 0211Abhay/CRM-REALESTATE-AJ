package com.ajproject.realestatecrm.controller;

import com.ajproject.realestatecrm.beans.RentPayment;
import com.ajproject.realestatecrm.beans.Rental;
import com.ajproject.realestatecrm.repository.RentPaymentRepository;
import com.ajproject.realestatecrm.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RentPaymentController {

    @Autowired
    private RentPaymentRepository rentPaymentRepository;
    
    @Autowired
    private RentalRepository rentalRepository;
    
    // Add a new rent payment (matches Node.js addRentPayment)
    @PostMapping("/addRentPayment")
    public ResponseEntity<?> addRentPayment(@RequestBody Map<String, Object> paymentData) {
        try {
            // Extract data from request
            Integer rentalId = (Integer) paymentData.get("rental_id");
            String paymentDateStr = (String) paymentData.get("payment_date");
            String amountStr = String.valueOf(paymentData.get("amount"));
            String month = (String) paymentData.get("month");
            String dueDateStr = (String) paymentData.get("due_date");
            
            // Validate required fields
            if (rentalId == null || paymentDateStr == null || amountStr == null) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Missing required fields");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Parse dates
            LocalDate paymentDate = LocalDate.parse(paymentDateStr);
            LocalDate dueDate = dueDateStr != null ? LocalDate.parse(dueDateStr) : paymentDate;
            
            // Format the month name if month data is available
            String formattedMonth = month;
            if (formattedMonth == null) {
                formattedMonth = paymentDate.getMonth().toString() + " " + paymentDate.getYear();
            }
            
            // Check if rental exists
            Optional<Rental> rentalOpt = rentalRepository.findById(rentalId);
            if (!rentalOpt.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Rental not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            Rental rental = rentalOpt.get();
            
            // Check for existing payment for this rental and month
            List<RentPayment> existingPayments = rentPaymentRepository.findByRentalAndMonth(rental, formattedMonth);
            if (!existingPayments.isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put("error", "Duplicate payment");
                response.put("message", "A payment for " + formattedMonth + " has already been recorded for this rental.");
                response.put("details", "Each rental period can only have one payment entry.");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Create new payment
            RentPayment payment = new RentPayment();
            payment.setRental(rental);
            payment.setMonth(formattedMonth);
            payment.setDueDate(dueDate);
            
            // Parse amount
            BigDecimal amount = new BigDecimal(amountStr);
            payment.setAmountDue(amount);
            payment.setAmountPaid(amount);
            
            payment.setPaymentDate(paymentDate);
            payment.setStatus("paid");
            payment.setNotes("Payment recorded via rental management system");
            payment.setCreatedAt(LocalDateTime.now());
            payment.setUpdatedAt(LocalDateTime.now());
            
            // Save the payment
            RentPayment savedPayment = rentPaymentRepository.save(payment);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("payment_id", savedPayment.getPaymentId());
            response.put("message", "Payment recorded successfully");
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Get all payments
    @GetMapping("/getAllPayments")
    public ResponseEntity<?> getAllPayments() {
        try {
            List<RentPayment> payments = rentPaymentRepository.findAll();
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Payments fetched successfully");
            response.put("payments", payments);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Get payment by ID
    @GetMapping("/getPayment/{payment_id}")
    public ResponseEntity<?> getPaymentById(@PathVariable("payment_id") Integer id) {
        try {
            Optional<RentPayment> payment = rentPaymentRepository.findById(id);
            
            if (!payment.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Payment not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Payment fetched successfully");
            response.put("payment", payment.get());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Update payment
    @PutMapping("/updatePayment/{payment_id}")
    public ResponseEntity<?> updatePayment(@PathVariable("payment_id") Integer id, @RequestBody RentPayment paymentDetails) {
        try {
            Optional<RentPayment> paymentOpt = rentPaymentRepository.findById(id);
            
            if (!paymentOpt.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Payment not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            RentPayment payment = paymentOpt.get();
            
            // Update fields if provided
            if (paymentDetails.getRental() != null) {
                Optional<Rental> rental = rentalRepository.findById(paymentDetails.getRental().getRentalId());
                if (!rental.isPresent()) {
                    Map<String, String> response = new HashMap<>();
                    response.put("error", "Rental not found");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                }
                payment.setRental(rental.get());
            }
            
            if (paymentDetails.getMonth() != null) {
                payment.setMonth(paymentDetails.getMonth());
            }
            
            if (paymentDetails.getDueDate() != null) {
                payment.setDueDate(paymentDetails.getDueDate());
            }
            
            if (paymentDetails.getAmountDue() != null) {
                payment.setAmountDue(paymentDetails.getAmountDue());
            }
            
            if (paymentDetails.getAmountPaid() != null) {
                payment.setAmountPaid(paymentDetails.getAmountPaid());
            }
            
            if (paymentDetails.getPaymentDate() != null) {
                payment.setPaymentDate(paymentDetails.getPaymentDate());
            }
            
            if (paymentDetails.getStatus() != null) {
                payment.setStatus(paymentDetails.getStatus());
            }
            
            if (paymentDetails.getNotes() != null) {
                payment.setNotes(paymentDetails.getNotes());
            }
            
            // Update timestamp
            payment.setUpdatedAt(LocalDateTime.now());
            
            // Save updated payment
            RentPayment updatedPayment = rentPaymentRepository.save(payment);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Payment updated successfully");
            response.put("payment", updatedPayment);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Delete payment
    @DeleteMapping("/deletePayment/{payment_id}")
    public ResponseEntity<?> deletePayment(@PathVariable("payment_id") Integer id) {
        try {
            Optional<RentPayment> payment = rentPaymentRepository.findById(id);
            
            if (!payment.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Payment not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            rentPaymentRepository.delete(payment.get());
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Payment deleted successfully");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Get payments for a specific rental (matches Node.js getRentalPayments)
    @GetMapping("/getRentalPayments/{rental_id}")
    public ResponseEntity<?> getRentalPayments(@PathVariable("rental_id") Integer rentalId) {
        try {
            Optional<Rental> rental = rentalRepository.findById(rentalId);
            
            if (!rental.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Rental not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            List<RentPayment> payments = rentPaymentRepository.findByRental(rental.get());
            
            // Sort by payment date ascending
            payments.sort((p1, p2) -> p1.getPaymentDate().compareTo(p2.getPaymentDate()));
            
            Map<String, Object> response = new HashMap<>();
            response.put("payments", payments);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", "Server error");
            response.put("details", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Get payments by status
    @GetMapping("/getPaymentsByStatus/{status}")
    public ResponseEntity<?> getPaymentsByStatus(@PathVariable String status) {
        try {
            List<RentPayment> payments = rentPaymentRepository.findByStatus(status);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Payments fetched successfully");
            response.put("payments", payments);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Get all paid payments by broker ID (matches Node.js getAllPaidPayments)
    @GetMapping("/getAllPaidPayments/{broker_id}")
    public ResponseEntity<?> getAllPaidPayments(@PathVariable("broker_id") Integer brokerId) {
        try {
            // Log the request
            System.out.println("Fetching paid payments for broker ID: " + brokerId);
            
            if (brokerId == null) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Broker ID is required");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Get all paid payments for this broker
            List<RentPayment> payments = rentPaymentRepository.findPaidPaymentsByBrokerId(brokerId);
            
            // Sort by payment date descending
            payments.sort((p1, p2) -> p2.getPaymentDate().compareTo(p1.getPaymentDate()));
            
            Map<String, Object> response = new HashMap<>();
            response.put("payments", payments);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", "Server error");
            response.put("details", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Legacy route (deprecated)
    @GetMapping("/getAllPaidPayments")
    public ResponseEntity<?> getAllPaidPaymentsDeprecated() {
        Map<String, String> response = new HashMap<>();
        response.put("error", "This endpoint is deprecated. Please use /getAllPaidPayments/:broker_id instead");
        return ResponseEntity.badRequest().body(response);
    }
    
    // Get all overdue payments by broker ID
    @GetMapping("/getAllOverduePayments/{broker_id}")
    public ResponseEntity<?> getAllOverduePayments(@PathVariable("broker_id") Integer brokerId) {
        try {
            List<RentPayment> payments = rentPaymentRepository.findOverduePaymentsByBrokerId(brokerId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Overdue payments fetched successfully");
            response.put("payments", payments);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Get all due payments by broker ID
    @GetMapping("/getAllDuePayments/{broker_id}")
    public ResponseEntity<?> getAllDuePayments(@PathVariable("broker_id") Integer brokerId) {
        try {
            List<RentPayment> payments = rentPaymentRepository.findDuePaymentsByBrokerId(brokerId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Due payments fetched successfully");
            response.put("payments", payments);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Get payments by client ID
    @GetMapping("/getPaymentsByClient/{client_id}")
    public ResponseEntity<?> getPaymentsByClient(@PathVariable("client_id") Integer clientId) {
        try {
            List<RentPayment> payments = rentPaymentRepository.findByClientId(clientId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Payments fetched successfully");
            response.put("payments", payments);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Get payments by property ID
    @GetMapping("/getPaymentsByProperty/{property_id}")
    public ResponseEntity<?> getPaymentsByProperty(@PathVariable("property_id") Integer propertyId) {
        try {
            List<RentPayment> payments = rentPaymentRepository.findByPropertyId(propertyId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Payments fetched successfully");
            response.put("payments", payments);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Record a payment
    @PostMapping("/recordPayment")
    public ResponseEntity<?> recordPayment(@RequestBody Map<String, Object> paymentData) {
        try {
            // Extract data from request
            Integer rentalId = (Integer) paymentData.get("rental_id");
            String month = (String) paymentData.get("month");
            String amountPaidStr = String.valueOf(paymentData.get("amount_paid"));
            String status = (String) paymentData.get("status");
            String notes = (String) paymentData.get("notes");
            
            // Validate required fields
            if (rentalId == null || amountPaidStr == null || status == null) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Missing required fields");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Check if rental exists
            Optional<Rental> rental = rentalRepository.findById(rentalId);
            if (!rental.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Rental not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            // Create new payment record
            RentPayment payment = new RentPayment();
            payment.setRental(rental.get());
            payment.setMonth(month);
            payment.setDueDate(LocalDate.now()); // Default to today
            payment.setAmountDue(rental.get().getRentAmount()); // Use rental amount as due amount
            payment.setAmountPaid(java.math.BigDecimal.valueOf(Double.parseDouble(amountPaidStr)));
            payment.setPaymentDate(LocalDate.now());
            payment.setStatus(status);
            payment.setNotes(notes);
            payment.setCreatedAt(LocalDateTime.now());
            payment.setUpdatedAt(LocalDateTime.now());
            
            // Save the payment
            RentPayment savedPayment = rentPaymentRepository.save(payment);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Payment recorded successfully");
            response.put("payment", savedPayment);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
