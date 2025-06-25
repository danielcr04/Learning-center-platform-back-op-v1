package com.intellectsoft.platform.u20221g120tipo1.portfolio.interfaces.rest.transform;

import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.commands.CreateWebApplicationCommand;
import com.intellectsoft.platform.u20221g120tipo1.portfolio.interfaces.rest.resources.CreateWebApplicationResource;

public class CreateWebApplicationCommandFromResourceAssembler {
    public static CreateWebApplicationCommand toCommandFromResource(Long clientId, CreateWebApplicationResource resource){
        return new CreateWebApplicationCommand
                (
                    resource.name(),
                    clientId,
                    resource.frontendStack(),
                    resource.frontendUrl(),
                    resource.backendStack(),
                    resource.backendUrl(),
                    resource.cloudPlatform(),
                    resource.description(),
                    resource.availabilityPercentage(),
                    resource.launchDate());
    }
}
