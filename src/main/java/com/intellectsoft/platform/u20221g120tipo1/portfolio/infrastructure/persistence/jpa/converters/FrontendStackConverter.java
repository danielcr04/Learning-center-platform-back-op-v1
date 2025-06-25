package com.intellectsoft.platform.u20221g120tipo1.portfolio.infrastructure.persistence.jpa.converters;

import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.valueobjects.FrontendStack;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter(autoApply = true)
public class FrontendStackConverter implements AttributeConverter<FrontendStack, Integer> {

    @Override
    public Integer convertToDatabaseColumn(FrontendStack frontendStack) {
        return (frontendStack != null) ? frontendStack.getId() : null;
    }

    @Override
    public FrontendStack convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;

        return Arrays.stream(FrontendStack.values())
                .filter(stack -> stack.getId() == dbData)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Unknown FrontendStack id: " + dbData));
    }
}
