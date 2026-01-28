package com.student.service;

import com.student.model.Student;
import com.student.exception.StudentNotFoundException;
import com.student.exception.StorageFullException;
import com.student.exception.InvalidInputException;

/**
 * Service interface demonstrating Abstraction
 * Defines contract for student management operations
 */
public interface StudentService {
    void addStudent(Student student) throws StorageFullException, InvalidInputException;

    void viewAllStudents();

    Student searchStudentById(int id) throws StudentNotFoundException;

    void updateStudent(int id, Student updatedStudent) throws StudentNotFoundException, InvalidInputException;

    void updateStudentField(int id, String field, Object value) throws StudentNotFoundException, InvalidInputException;

    void deleteStudent(int id) throws StudentNotFoundException;

    int getStudentCount();
}