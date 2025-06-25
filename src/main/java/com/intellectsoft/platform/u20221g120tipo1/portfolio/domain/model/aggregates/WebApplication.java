package com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.aggregates;

import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.commands.CreateWebApplicationCommand;
import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.valueobjects.BackendStack;
import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.valueobjects.ClientId;
import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.valueobjects.CloudPlatform;
import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.valueobjects.FrontendStack;
import com.intellectsoft.platform.u20221g120tipo1.portfolio.infrastructure.persistence.jpa.converters.BackendStackConverter;
import com.intellectsoft.platform.u20221g120tipo1.portfolio.infrastructure.persistence.jpa.converters.CloudPlatformConverter;
import com.intellectsoft.platform.u20221g120tipo1.portfolio.infrastructure.persistence.jpa.converters.FrontendStackConverter;
import com.intellectsoft.platform.u20221g120tipo1.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.intellectsoft.platform.u20221g120tipo1.shared.domain.model.valueobjects.WebAddress;
import jakarta.persistence.*;
import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Entity
public class WebApplication extends AuditableAbstractAggregateRoot<WebApplication> {

    @Column(name = "name", length = 80, nullable = false)
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "clientId", column = @Column(name = "client_id", nullable = false))
    })
    private ClientId clientId;

    @Convert(converter = FrontendStackConverter.class)
    @Column(name = "frontend_stack", nullable = false)
    private FrontendStack frontendStack;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "webAddress", column = @Column(name = "frontend_url", length = 512, nullable = false))
    })
    private WebAddress frontendUrl;

    @Convert(converter = BackendStackConverter.class)
    @Column(name = "backend_stack", nullable = false)
    private BackendStack backendStack;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "webAddress", column = @Column(name = "backend_url", length = 512, nullable = false))
    })
    private WebAddress backendUrl;

    @Convert(converter = CloudPlatformConverter.class)
    @Column(name = "cloud_platform", nullable = false)
    private CloudPlatform cloudPlatform;

    @Column(name = "description", length = 360)
    private String description;

    @Column(name = "availability_percentage", nullable = false)
    private Integer availabilityPercentage;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "launch_date", nullable = false)
    private Date launchDate;

    /**
     * Default constructor for JPA
     */
    public WebApplication() {
        // Required by JPA
    }

    /**
     * Constructor to create a WebApplication from a CreateWebApplicationCommand
     * @param command the command containing the web application data
     */
    public WebApplication(CreateWebApplicationCommand command) {
        this();
        this.name = command.name();
        this.clientId = new ClientId(command.clientId());
        this.frontendStack = FrontendStack.fromName(command.frontendStack());
        this.frontendUrl = new WebAddress(command.frontendUrl());
        this.backendStack = BackendStack.fromName(command.backendStack());
        this.backendUrl = new WebAddress(command.backendUrl());
        this.cloudPlatform = CloudPlatform.fromName(command.cloudPlatform());
        this.description = command.description();
        this.launchDate = parseLaunchDate(command.launchDate());
        this.availabilityPercentage = command.availabilityPercentage();

        // Validate business rules
        validateBusinessRules();
    }

    /**
     * Validates all business rules for the WebApplication
     */
    private void validateBusinessRules() {
        validateName();
        validateClientId();
        validateUrls();
        validateDescription();
        validateAvailabilityPercentage();
        validateLaunchDate();
    }



    /**
     * Validates the name field
     */
    private void validateName() {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (name.length() > 80) {
            throw new IllegalArgumentException("Name cannot exceed 80 characters");
        }
    }

    /**
     * Validates the client ID
     */
    private void validateClientId() {
        if (clientId == null) {
            throw new IllegalArgumentException("Client ID cannot be null");
        }
    }

    /**
     * Validates URLs are not null and not empty
     */
    private void validateUrls() {
        if (frontendUrl == null) {
            throw new IllegalArgumentException("Frontend URL cannot be null");
        }
        if (backendUrl == null) {
            throw new IllegalArgumentException("Backend URL cannot be null");
        }
    }

    /**
     * Validates the description field
     */
    private void validateDescription() {
        if (description != null && description.length() > 360) {
            throw new IllegalArgumentException("Description cannot exceed 360 characters");
        }
    }

    /**
     * Validates the availability percentage
     */
    private void validateAvailabilityPercentage() {
        if (availabilityPercentage == null) {
            throw new IllegalArgumentException("Availability percentage cannot be null");
        }
        if (availabilityPercentage < 85 || availabilityPercentage > 99) {
            throw new IllegalArgumentException("Availability percentage must be between 85 and 99");
        }
    }

    /**
     * Validates the launch date
     */
    private void validateLaunchDate() {
        if (launchDate == null) {
            throw new IllegalArgumentException("Launch date cannot be null");
        }

        // Intellectsoft founding date: July 15, 2007
        Date intellectsoftFoundingDate = new Date(2007 - 1900, 6, 15); // Month is 0-based
        if (launchDate.before(intellectsoftFoundingDate)) {
            throw new IllegalArgumentException("Launch date cannot be before Intellectsoft founding date (July 15, 2007)");
        }

        // Cannot be in the future
        Date now = new Date();
        if (launchDate.after(now)) {
            throw new IllegalArgumentException("Launch date cannot be in the future");
        }
    }

    /**
     * Parses the launch date from string format
     * @param dateString the date string in ISO format (yyyy-MM-dd)
     * @return the parsed Date object
     */
    private Date parseLaunchDate(String dateString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formatter.setLenient(false);
            return formatter.parse(dateString);
        } catch (ParseException e) {
            try {
                // Try with date-time format
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                formatter.setLenient(false);
                return formatter.parse(dateString);
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Invalid date format. Expected yyyy-MM-dd or yyyy-MM-dd'T'HH:mm:ss", ex);
            }
        }
    }
}