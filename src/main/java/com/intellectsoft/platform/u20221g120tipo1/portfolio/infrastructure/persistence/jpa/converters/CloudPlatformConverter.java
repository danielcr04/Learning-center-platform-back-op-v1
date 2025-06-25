package com.intellectsoft.platform.u20221g120tipo1.portfolio.infrastructure.persistence.jpa.converters;

import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.valueobjects.CloudPlatform;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter(autoApply = true)
public class CloudPlatformConverter implements AttributeConverter<CloudPlatform, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CloudPlatform cloudPlatform) {
        return (cloudPlatform != null) ? cloudPlatform.getId() : null;
    }

    @Override
    public CloudPlatform convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;

        return Arrays.stream(CloudPlatform.values())
                .filter(platform -> platform.getId() == dbData)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Unknown CloudPlatform id: " + dbData));
    }
}
