package com.sms.service;

import com.sms.model.Student;
import com.sms.exception.StudentNotFoundException;
import com.sms.exception.StorageFullException;
import com.sms.exception.InvalidInputException;

/**
 * Service interface demonstrating Abstraction
 * Defines contract for student management operations
 */
public interface StudentService {
    void addStudent(Student student) throws StorageFullException, InvalidInputException;

    void viewAllStudents();

    Student searchStudentById(int id) throws StudentNotFoundException;

    Student[] searchStudentsByName(String name);

    Student searchStudentByEmail(String email) throws StudentNotFoundException;

    void updateStudent(int id, Student updatedStudent) throws StudentNotFoundException, InvalidInputException;

    void updateStudentField(int id, String field, Object value) throws StudentNotFoundException, InvalidInputException;

    void deleteStudent(int id) throws StudentNotFoundException;

    int getStudentCount();
}