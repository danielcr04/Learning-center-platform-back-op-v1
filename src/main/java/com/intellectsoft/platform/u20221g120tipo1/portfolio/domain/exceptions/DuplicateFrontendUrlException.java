package com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.exceptions;


/**
 * Thrown when a web application with the same frontend URL already exists.
 */
public class DuplicateFrontendUrlException extends RuntimeException {
    public DuplicateFrontendUrlException(String url) {
        super("A web application with the frontend URL already exists: " + url);
    }
}