package com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.valueobjects;

import lombok.Getter;

@Getter
public enum CloudPlatform {
    AWS(1, "AWS"),
    AZURE(2, "AZURE"),
    GCP(3, "GCP");

    private final int id;
    private final String name;

    CloudPlatform(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CloudPlatform fromName(String name) {
        for (CloudPlatform cloudPlatform : values()) {
            if (cloudPlatform.name.equals(name)) {
                return cloudPlatform;
            }
        }
        throw new IllegalArgumentException("Invalid cloud platform  : " + name);
    }
}