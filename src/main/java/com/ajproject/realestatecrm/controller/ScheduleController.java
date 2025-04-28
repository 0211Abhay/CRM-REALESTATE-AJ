package com.ajproject.realestatecrm.controller;

import com.ajproject.realestatecrm.beans.Schedule;
import com.ajproject.realestatecrm.beans.Property;
import com.ajproject.realestatecrm.beans.Client;
import com.ajproject.realestatecrm.beans.Broker;
import com.ajproject.realestatecrm.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    
    @Autowired
    private ScheduleRepository scheduleRepository;
    
    // Create a new schedule
    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        // Validate required fields
        if (schedule.getProperty() == null || schedule.getClient() == null ||
            schedule.getBroker() == null || schedule.getDate() == null ||
            schedule.getTime() == null) {
            return ResponseEntity.badRequest().build();
        }

        // Set creation and update timestamps
        LocalDateTime now = LocalDateTime.now();
        schedule.setCreatedAt(now);
        schedule.setUpdatedAt(now);
        
        // Set default status if not provided
        if (schedule.getStatus() == null) {
            schedule.setStatus(Schedule.ScheduleStatus.Pending);
        }
        
        try {
            Schedule savedSchedule = scheduleRepository.save(schedule);
            return ResponseEntity.ok(savedSchedule);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Get all schedules
    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return ResponseEntity.ok(schedules);
    }
    
    // Get schedule by ID
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Integer id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        return schedule.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    
    // Update schedule
    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Integer id, @RequestBody Schedule scheduleDetails) {
        return scheduleRepository.findById(id)
            .map(existingSchedule -> {
                existingSchedule.setProperty(scheduleDetails.getProperty());
                existingSchedule.setClient(scheduleDetails.getClient());
                existingSchedule.setBroker(scheduleDetails.getBroker());
                existingSchedule.setDate(scheduleDetails.getDate());
                existingSchedule.setTime(scheduleDetails.getTime());
                existingSchedule.setStatus(scheduleDetails.getStatus());
                existingSchedule.setDescription(scheduleDetails.getDescription());
                existingSchedule.setUpdatedAt(LocalDateTime.now());
                
                Schedule updatedSchedule = scheduleRepository.save(existingSchedule);
                return ResponseEntity.ok(updatedSchedule);
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    // Delete schedule
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Integer id) {
        return scheduleRepository.findById(id)
            .map(schedule -> {
                scheduleRepository.delete(schedule);
                return ResponseEntity.ok().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    // Find schedules by property
    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<Schedule>> getSchedulesByProperty(@PathVariable Integer propertyId) {
        Property property = new Property();
        property.setPropertyId(propertyId);
        List<Schedule> schedules = scheduleRepository.findByProperty(property);
        return ResponseEntity.ok(schedules);
    }
    
    // Find schedules by client
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Schedule>> getSchedulesByClient(@PathVariable Integer clientId) {
        Client client = new Client();
        client.setClientId(clientId);
        List<Schedule> schedules = scheduleRepository.findByClient(client);
        return ResponseEntity.ok(schedules);
    }
    
    // Find schedules by broker
    @GetMapping("/broker/{brokerId}")
    public ResponseEntity<List<Schedule>> getSchedulesByBroker(@PathVariable Integer brokerId) {
        Broker broker = new Broker();
        broker.setBrokerId(brokerId);
        List<Schedule> schedules = scheduleRepository.findByBroker(broker);
        return ResponseEntity.ok(schedules);
    }
    
    // Find schedules by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Schedule>> getSchedulesByStatus(@PathVariable Schedule.ScheduleStatus status) {
        List<Schedule> schedules = scheduleRepository.findByStatus(status);
        return ResponseEntity.ok(schedules);
    }
    
    // Find schedules by date range
    @GetMapping("/date-range")
    public ResponseEntity<List<Schedule>> getSchedulesByDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        List<Schedule> schedules = scheduleRepository.findByDateBetween(startDate, endDate);
        return ResponseEntity.ok(schedules);
    }
    
    // Find schedules by broker and status
    @GetMapping("/broker/{brokerId}/status/{status}")
    public ResponseEntity<List<Schedule>> getSchedulesByBrokerAndStatus(
            @PathVariable Integer brokerId,
            @PathVariable Schedule.ScheduleStatus status) {
        Broker broker = new Broker();
        broker.setBrokerId(brokerId);
        List<Schedule> schedules = scheduleRepository.findByBrokerAndStatus(broker, status);
        return ResponseEntity.ok(schedules);
    }
    
    // Find schedules by broker and date range
    @GetMapping("/broker/{brokerId}/date-range")
    public ResponseEntity<List<Schedule>> getSchedulesByBrokerAndDateRange(
            @PathVariable Integer brokerId,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        Broker broker = new Broker();
        broker.setBrokerId(brokerId);
        List<Schedule> schedules = scheduleRepository.findByBrokerAndDateBetween(broker, startDate, endDate);
        return ResponseEntity.ok(schedules);
    }
    
    // Find schedules by broker, status and date range
    @GetMapping("/broker/{brokerId}/status/{status}/date-range")
    public ResponseEntity<List<Schedule>> getSchedulesByBrokerStatusAndDateRange(
            @PathVariable Integer brokerId,
            @PathVariable Schedule.ScheduleStatus status,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        Broker broker = new Broker();
        broker.setBrokerId(brokerId);
        List<Schedule> schedules = scheduleRepository.findByBrokerAndStatusAndDateBetween(
            broker, status, startDate, endDate);
        return ResponseEntity.ok(schedules);
    }
}
