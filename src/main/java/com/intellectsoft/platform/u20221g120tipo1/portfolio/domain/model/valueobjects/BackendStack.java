package com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.valueobjects;

import lombok.Getter;

@Getter
public enum BackendStack {
    SPRING_BOOT(1, "SPRING_BOOT"),
    DOTNET(2, ".NET"),
    NODEJS(3, "NODEJS");

    private final int id;
    private final String name;

    BackendStack(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static BackendStack fromName(String name) {
        for (BackendStack backendStack : values()) {
            if (backendStack.name.equals(name)) {
                return backendStack;
            }
        }
        throw new IllegalArgumentException("Invalid backend stack: " + name);
    }
}
