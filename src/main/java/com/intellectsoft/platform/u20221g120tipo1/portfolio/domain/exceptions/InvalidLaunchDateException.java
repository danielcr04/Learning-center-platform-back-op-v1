package com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.exceptions;

public class InvalidLaunchDateException extends RuntimeException {
    public InvalidLaunchDateException(String invalidDate) {
        super("Invalid launch date format: '" + invalidDate + "'. Expected format: yyyy-MM-dd");
    }
}