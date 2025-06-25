package com.intellectsoft.platform.u20221g120tipo1.portfolio.interfaces.rest.transform;

import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.aggregates.WebApplication;
import com.intellectsoft.platform.u20221g120tipo1.portfolio.interfaces.rest.resources.WebApplicationResource;

public class WebApplicationResourceFromEntityAssembler {
    public static WebApplicationResource toResourceFromEntity(WebApplication entity) {
        return new WebApplicationResource(
                entity.getId(),
                entity.getName(),
                entity.getClientId().clientId(),
                entity.getFrontendStack().getId(),
                entity.getFrontendUrl().getWebAddress(),
                entity.getBackendStack().getId(),
                entity.getBackendUrl().getWebAddress(),
                entity.getCloudPlatform().getId(),
                entity.getDescription(),
                entity.getAvailabilityPercentage(),
                entity.getLaunchDate().toString()
        );
    }
}
