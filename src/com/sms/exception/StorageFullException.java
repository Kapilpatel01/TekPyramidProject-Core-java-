package com.sms.exception;

/**
 * Custom exception thrown when trying to add more students than array capacity
 */
public class StorageFullException extends Exception {
    public StorageFullException(String message) {
        super(message);
    }

    public StorageFullException() {
        super("Student storage is full! Cannot add more students.");
    }
}