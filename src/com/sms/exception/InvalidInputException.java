package com.sms.exception;

/**
 * Custom exception thrown when invalid input is provided
 */
public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}