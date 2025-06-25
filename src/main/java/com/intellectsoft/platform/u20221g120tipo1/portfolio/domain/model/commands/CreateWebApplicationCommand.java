package com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.commands;

/**
 * Create Web Application Command
 * @summary Command to create a new web application with primitive data types only
 * @param name the name of the web application
 * @param clientId the client identifier
 * @param frontendStack the frontend stack name
 * @param frontendUrl the frontend URL
 * @param backendStack the backend stack name
 * @param backendUrl the backend URL
 * @param cloudPlatform the cloud platform name
 * @param description the description of the web application
 * @param availabilityPercentage the availability percentage
 * @param launchDate the launch date of the web application (as string in ISO format)
 * @author Daniel Crispin Ramos
 */
public record CreateWebApplicationCommand(
        String name,
        Long clientId,
        String frontendStack,
        String frontendUrl,
        String backendStack,
        String backendUrl,
        String cloudPlatform,
        String description,
        Integer availabilityPercentage,
        String launchDate
) {
    /**
     * Constructor that validates the command
     * @param name the name of the web application
     * @param clientId the client identifier
     * @param frontendStack the frontend stack name
     * @param frontendUrl the frontend URL
     * @param backendStack the backend stack name
     * @param backendUrl the backend URL
     * @param cloudPlatform the cloud platform name
     * @param description the description of the web application
     * @param availabilityPercentage the availability percentage
     * @param launchDate the launch date of the web application (as string)
     */
    public CreateWebApplicationCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (name.length() > 80) {
            throw new IllegalArgumentException("Name cannot exceed 80 characters");
        }
        if (clientId == null || clientId <= 0) {
            throw new IllegalArgumentException("Client ID must be positive");
        }
        if (frontendStack == null || frontendStack.isBlank()) {
            throw new IllegalArgumentException("Frontend stack cannot be null or empty");
        }
        if (frontendUrl == null || frontendUrl.isBlank()) {
            throw new IllegalArgumentException("Frontend URL cannot be null or empty");
        }
        if (frontendUrl.length() > 512) {
            throw new IllegalArgumentException("Frontend URL cannot exceed 512 characters");
        }
        if (backendStack == null || backendStack.isBlank()) {
            throw new IllegalArgumentException("Backend stack cannot be null or empty");
        }
        if (backendUrl == null || backendUrl.isBlank()) {
            throw new IllegalArgumentException("Backend URL cannot be null or empty");
        }
        if (backendUrl.length() > 512) {
            throw new IllegalArgumentException("Backend URL cannot exceed 512 characters");
        }
        if (cloudPlatform == null || cloudPlatform.isBlank()) {
            throw new IllegalArgumentException("Cloud platform cannot be null or empty");
        }
        if (description != null && description.length() > 360) {
            throw new IllegalArgumentException("Description cannot exceed 360 characters");
        }
        if (availabilityPercentage == null) {
            throw new IllegalArgumentException("Availability percentage cannot be null");
        }
        if (availabilityPercentage < 85 || availabilityPercentage > 99) {
            throw new IllegalArgumentException("Availability percentage must be between 85 and 99");
        }
        if (launchDate == null || launchDate.isBlank()) {
            throw new IllegalArgumentException("Launch date cannot be null or empty");
        }
    }
}
