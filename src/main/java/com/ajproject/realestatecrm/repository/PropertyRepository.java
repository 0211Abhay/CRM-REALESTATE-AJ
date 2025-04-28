package com.ajproject.realestatecrm.repository;

import com.ajproject.realestatecrm.beans.Property;
import com.ajproject.realestatecrm.beans.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PropertRepository extends JpaRepository<Property, Integer> {
    
    // Find properties by broker
    List<Property> findByBroker(Broker broker);
    
    // Find properties by status
    List<Property> findByStatus(Property.PropertyStatus status);
    
    // Find properties by property type
    List<Property> findByPropertyType(Property.PropertyType propertyType);
    
    // Find properties by property for (Rent/Sale)
    List<Property> findByPropertyFor(Property.PropertyFor propertyFor);
    
    // Find properties by price range
    List<Property> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    
    // Find properties by location containing search term
    List<Property> findByLocationContainingIgnoreCase(String location);
    
    // Find properties by name containing search term
    List<Property> findByNameContainingIgnoreCase(String name);
    
    // Find properties by number of bedrooms
    List<Property> findByBedrooms(Integer bedrooms);
    
    // Find properties by broker and status
    List<Property> findByBrokerAndStatus(Broker broker, Property.PropertyStatus status);
    
    // Find properties by broker and property type
    List<Property> findByBrokerAndPropertyType(Broker broker, Property.PropertyType propertyType);
    
    // Find properties by area range
    List<Property> findByAreaBetween(Integer minArea, Integer maxArea);
    
    // Find properties by year built range
    List<Property> findByYearBuiltBetween(Integer fromYear, Integer toYear);
}
