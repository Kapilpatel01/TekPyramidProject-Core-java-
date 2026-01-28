package com.student.exception;

/**
 * Custom exception thrown when a student is not found in the system
 */
public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(int studentId) {
        super("Student with ID " + studentId + " not found!");
    }
}
