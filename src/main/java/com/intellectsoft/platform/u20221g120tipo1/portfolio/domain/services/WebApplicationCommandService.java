package com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.services;

import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.aggregates.WebApplication;
import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.commands.CreateWebApplicationCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface WebApplicationCommandService {
    Optional<WebApplication> handle(CreateWebApplicationCommand command);
}
