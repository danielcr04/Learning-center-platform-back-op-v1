package com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.exceptions;

/**
 * Thrown when a web application with the same backend URL already exists.
 */
public class DuplicateBackendUrlException extends RuntimeException {
    public DuplicateBackendUrlException(String url) {
        super("A web application with the backend URL already exists: " + url);
    }
}