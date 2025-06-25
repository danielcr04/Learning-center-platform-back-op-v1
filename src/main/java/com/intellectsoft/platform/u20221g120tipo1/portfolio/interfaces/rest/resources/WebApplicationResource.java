package com.intellectsoft.platform.u20221g120tipo1.portfolio.interfaces.rest.resources;

public record WebApplicationResource(
        Long id,
        String name,
        Long clientId,
        Integer frontendStack,
        String frontendUrl,
        Integer backendStack,
        String backendUrl,
        Integer cloudPlatform,
        String description,
        Integer availabilityPercentage,
        String launchDate
) {
}