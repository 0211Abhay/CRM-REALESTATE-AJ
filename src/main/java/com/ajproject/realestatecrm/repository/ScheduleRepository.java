package com.ajproject.realestatecrm.repository;

import com.ajproject.realestatecrm.beans.Schedule;
import com.ajproject.realestatecrm.beans.Property;
import com.ajproject.realestatecrm.beans.Client;
import com.ajproject.realestatecrm.beans.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    
    // Find schedules by property
    List<Schedule> findByProperty(Property property);
    
    // Find schedules by client
    List<Schedule> findByClient(Client client);
    
    // Find schedules by broker
    List<Schedule> findByBroker(Broker broker);
    
    // Find schedules by status
    List<Schedule> findByStatus(Schedule.ScheduleStatus status);
    
    // Find schedules by date range
    List<Schedule> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    // Find schedules by broker and status
    List<Schedule> findByBrokerAndStatus(Broker broker, Schedule.ScheduleStatus status);
    
    // Find schedules by property and status
    List<Schedule> findByPropertyAndStatus(Property property, Schedule.ScheduleStatus status);
    
    // Find schedules by client and status
    List<Schedule> findByClientAndStatus(Client client, Schedule.ScheduleStatus status);
    
    // Find schedules by broker and date range
    List<Schedule> findByBrokerAndDateBetween(Broker broker, LocalDateTime startDate, LocalDateTime endDate);
    
    // Find schedules by property and date range
    List<Schedule> findByPropertyAndDateBetween(Property property, LocalDateTime startDate, LocalDateTime endDate);
    
    // Find schedules by client and date range
    List<Schedule> findByClientAndDateBetween(Client client, LocalDateTime startDate, LocalDateTime endDate);
    
    // Find schedules by broker, status and date range
    List<Schedule> findByBrokerAndStatusAndDateBetween(Broker broker, Schedule.ScheduleStatus status, 
            LocalDateTime startDate, LocalDateTime endDate);
}
