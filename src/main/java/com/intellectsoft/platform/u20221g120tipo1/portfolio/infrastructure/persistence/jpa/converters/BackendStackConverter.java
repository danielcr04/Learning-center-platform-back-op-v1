package com.intellectsoft.platform.u20221g120tipo1.portfolio.infrastructure.persistence.jpa.converters;


import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.valueobjects.BackendStack;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class BackendStackConverter implements AttributeConverter<BackendStack, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BackendStack backendStack) {
        return (backendStack != null) ? backendStack.getId() : null;
    }

    @Override
    public BackendStack convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;

        return Arrays.stream(BackendStack.values())
                .filter(stack -> stack.getId() == dbData)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Unknown BackendStack id: " + dbData));
    }
}