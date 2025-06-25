package com.intellectsoft.platform.u20221g120tipo1.shared.domain.model.valueobjects;


public record WebAddress(String webAddress) {

    public WebAddress {
        if (webAddress == null || webAddress.isBlank()) {
            throw new IllegalArgumentException("Web address cannot be null or empty");
        }
        if (webAddress.length() > 512) {
            throw new IllegalArgumentException("Web address cannot exceed 512 characters");
        }
        //agregar validación de formato de URL
        if (!webAddress.matches("^(https?|http)://[a-zA-Z0-9.-]+(:[0-9]+)?(/.*)?$")) {
            throw new IllegalArgumentException("Web address must be a valid URL");
        }
    }
    public String getWebAddress() {
        return webAddress;
    }
}