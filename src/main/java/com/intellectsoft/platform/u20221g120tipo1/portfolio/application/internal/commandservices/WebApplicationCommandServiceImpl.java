package com.intellectsoft.platform.u20221g120tipo1.portfolio.application.internal.commandservices;

import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.exceptions.DuplicateBackendUrlException;
import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.exceptions.DuplicateFrontendUrlException;
import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.exceptions.InvalidLaunchDateException;
import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.aggregates.WebApplication;
import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.commands.CreateWebApplicationCommand;
import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.services.WebApplicationCommandService;
import com.intellectsoft.platform.u20221g120tipo1.portfolio.infrastructure.persistence.jpa.repositories.WebApplicationRepository;
import com.intellectsoft.platform.u20221g120tipo1.shared.domain.model.valueobjects.WebAddress;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class WebApplicationCommandServiceImpl implements WebApplicationCommandService {
    private final WebApplicationRepository webApplicationRepository;

    public WebApplicationCommandServiceImpl(WebApplicationRepository webApplicationRepository) {
        this.webApplicationRepository = webApplicationRepository;
    }

    @Override
    public Optional<WebApplication> handle(CreateWebApplicationCommand command) {


        // 2. Validar launchDate (regla de negocio específica de Intellectsoft)
        if (command.launchDate() == null || command.launchDate().trim().isEmpty()) {
            throw new IllegalArgumentException("Launch date cannot be null or empty");
        }

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formatter.setLenient(false);
            Date launchDate = formatter.parse(command.launchDate());

            // Validar rango de fechas
            Date intellectsoftFoundingDate = new Date(2007 - 1900, 6, 15); // July 15, 2007
            if (launchDate.before(intellectsoftFoundingDate)) {
                throw new IllegalArgumentException("Launch date must be after Intellectsoft foundation date (July 15, 2007)");
            }
            if (launchDate.after(new Date())) {
                throw new IllegalArgumentException("Launch date cannot be in the future");
            }
        } catch (ParseException e) {
            throw new InvalidLaunchDateException(command.launchDate());
        }

        // 4. Validar URLs únicas (regla de negocio que requiere repositorio)
        var frontendUrl = new WebAddress(command.frontendUrl());
        if(webApplicationRepository.existsByFrontendUrl(frontendUrl)) {
             throw new DuplicateFrontendUrlException(command.frontendUrl());
        }

        var backendUrl = new WebAddress(command.backendUrl());
        if(webApplicationRepository.existsByBackendUrl(backendUrl)) {
            throw new DuplicateBackendUrlException(command.backendUrl());
        }

        var webApplication = new WebApplication(command);
        webApplicationRepository.save(webApplication);

        return Optional.of(webApplication);
    }

}
