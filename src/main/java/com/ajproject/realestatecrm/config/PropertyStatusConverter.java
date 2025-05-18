package com.ajproject.realestatecrm.config;

import com.ajproject.realestatecrm.beans.Property.PropertyStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PropertyStatusConverter implements AttributeConverter<PropertyStatus, String> {

    @Override
    public String convertToDatabaseColumn(PropertyStatus attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.toString();
    }

    @Override
    public PropertyStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return PropertyStatus.fromString(dbData);
    }
}
