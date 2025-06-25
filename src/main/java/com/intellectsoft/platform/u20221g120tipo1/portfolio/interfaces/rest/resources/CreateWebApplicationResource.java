package com.intellectsoft.platform.u20221g120tipo1.portfolio.interfaces.rest.resources;

public record CreateWebApplicationResource(
        String name,
        String frontendStack,
        String frontendUrl,
        String backendStack,
        String backendUrl,
        String cloudPlatform,
        String description,
        Integer availabilityPercentage,
        String launchDate
) {
}