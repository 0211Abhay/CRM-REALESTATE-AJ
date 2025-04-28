package com.ajproject.realestatecrm.controller;

import com.ajproject.realestatecrm.beans.Property;
import com.ajproject.realestatecrm.beans.Broker;
import com.ajproject.realestatecrm.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    
    @Autowired
    private PropertyRepository propertyRepository;
    
    // Create a new property
    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        // Validate required fields
        if (property.getName() == null || property.getName().trim().isEmpty() ||
            property.getLocation() == null || property.getLocation().trim().isEmpty() ||
            property.getPrice() == null || property.getBroker() == null ||
            property.getPropertyFor() == null) {
            return ResponseEntity.badRequest().build();
        }

        // Set creation timestamp and default status if not provided
        property.setCreatedAt(LocalDateTime.now());
        if (property.getStatus() == null) {
            property.setStatus(Property.PropertyStatus.Available);
        }
        
        try {
            Property savedProperty = propertyRepository.save(property);
            return ResponseEntity.ok(savedProperty);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Get all properties
    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = propertyRepository.findAll();
        return ResponseEntity.ok(properties);
    }
    
    // Get property by ID
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Integer id) {
        Optional<Property> property = propertyRepository.findById(id);
        return property.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    
    // Update property
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Integer id, @RequestBody Property propertyDetails) {
        return propertyRepository.findById(id)
            .map(existingProperty -> {
                existingProperty.setName(propertyDetails.getName());
                existingProperty.setLocation(propertyDetails.getLocation());
                existingProperty.setPrice(propertyDetails.getPrice());
                existingProperty.setPropertyFor(propertyDetails.getPropertyFor());
                existingProperty.setBedrooms(propertyDetails.getBedrooms());
                existingProperty.setBathrooms(propertyDetails.getBathrooms());
                existingProperty.setArea(propertyDetails.getArea());
                existingProperty.setPropertyType(propertyDetails.getPropertyType());
                existingProperty.setContactAgent(propertyDetails.getContactAgent());
                existingProperty.setYearBuilt(propertyDetails.getYearBuilt());
                existingProperty.setStatus(propertyDetails.getStatus());
                existingProperty.setDescription(propertyDetails.getDescription());
                existingProperty.setAmenities(propertyDetails.getAmenities());
                
                Property updatedProperty = propertyRepository.save(existingProperty);
                return ResponseEntity.ok(updatedProperty);
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    // Delete property
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Integer id) {
        return propertyRepository.findById(id)
            .map(property -> {
                propertyRepository.delete(property);
                return ResponseEntity.ok().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    // Find properties by broker
    @GetMapping("/broker/{brokerId}")
    public ResponseEntity<List<Property>> getPropertiesByBroker(@PathVariable Integer brokerId) {
        Broker broker = new Broker();
        broker.setBrokerId(brokerId);
        List<Property> properties = propertyRepository.findByBroker(broker);
        return ResponseEntity.ok(properties);
    }
    
    // Find properties by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Property>> getPropertiesByStatus(@PathVariable Property.PropertyStatus status) {
        List<Property> properties = propertyRepository.findByStatus(status);
        return ResponseEntity.ok(properties);
    }
    
    // Find properties by type
    @GetMapping("/type/{propertyType}")
    public ResponseEntity<List<Property>> getPropertiesByType(@PathVariable Property.PropertyType propertyType) {
        List<Property> properties = propertyRepository.findByPropertyType(propertyType);
        return ResponseEntity.ok(properties);
    }
    
    // Find properties by purpose (Rent/Sale)
    @GetMapping("/for/{propertyFor}")
    public ResponseEntity<List<Property>> getPropertiesByPurpose(@PathVariable Property.PropertyFor propertyFor) {
        List<Property> properties = propertyRepository.findByPropertyFor(propertyFor);
        return ResponseEntity.ok(properties);
    }
    
    // Find properties by price range
    @GetMapping("/price-range")
    public ResponseEntity<List<Property>> getPropertiesByPriceRange(
            @RequestParam BigDecimal minPrice, 
            @RequestParam BigDecimal maxPrice) {
        List<Property> properties = propertyRepository.findByPriceBetween(minPrice, maxPrice);
        return ResponseEntity.ok(properties);
    }
    
    // Search properties by location
    @GetMapping("/search/location")
    public ResponseEntity<List<Property>> searchPropertiesByLocation(@RequestParam String location) {
        List<Property> properties = propertyRepository.findByLocationContainingIgnoreCase(location);
        return ResponseEntity.ok(properties);
    }
    
    // Search properties by name
    @GetMapping("/search/name")
    public ResponseEntity<List<Property>> searchPropertiesByName(@RequestParam String name) {
        List<Property> properties = propertyRepository.findByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(properties);
    }
    
    // Find properties by number of bedrooms
    @GetMapping("/bedrooms/{bedrooms}")
    public ResponseEntity<List<Property>> getPropertiesByBedrooms(@PathVariable Integer bedrooms) {
        List<Property> properties = propertyRepository.findByBedrooms(bedrooms);
        return ResponseEntity.ok(properties);
    }
    
    // Find properties by area range
    @GetMapping("/area-range")
    public ResponseEntity<List<Property>> getPropertiesByAreaRange(
            @RequestParam Integer minArea, 
            @RequestParam Integer maxArea) {
        List<Property> properties = propertyRepository.findByAreaBetween(minArea, maxArea);
        return ResponseEntity.ok(properties);
    }
    
    // Find properties by year built range
    @GetMapping("/year-range")
    public ResponseEntity<List<Property>> getPropertiesByYearRange(
            @RequestParam Integer fromYear, 
            @RequestParam Integer toYear) {
        List<Property> properties = propertyRepository.findByYearBuiltBetween(fromYear, toYear);
        return ResponseEntity.ok(properties);
    }
}
