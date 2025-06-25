package com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.valueobjects;


import lombok.Getter;

@Getter
public enum FrontendStack {
    ANGULAR(1, "ANGULAR"),
    VUE(2, "VUE"),
    REACT(3, "REACT");

    private final int id;
    private final String name;

    FrontendStack(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static FrontendStack fromName(String name) {
        for (FrontendStack frontendStack : values()) {
            if (frontendStack.name.equals(name)) {
                return frontendStack;
            }
        }
        throw new IllegalArgumentException("Invalid frontend stack: " + name);
    }
}