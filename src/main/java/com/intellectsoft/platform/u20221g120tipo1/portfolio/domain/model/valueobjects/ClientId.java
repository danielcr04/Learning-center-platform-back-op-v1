package com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.valueobjects;

public record ClientId (Long clientId) {

    public ClientId {
        if (clientId == null || clientId <= 0) {
            throw new IllegalArgumentException("ClientId must be a positive number and not null.");
        }
    }
}
