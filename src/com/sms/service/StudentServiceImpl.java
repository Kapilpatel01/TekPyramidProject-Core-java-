package com.sms.service;

import com.sms.model.Student;
import com.sms.exception.StudentNotFoundException;
import com.sms.exception.StorageFullException;
import com.sms.exception.InvalidInputException;

/**
 * Implementation of StudentService interface
 * Demonstrates Polymorphism through method overriding
 */
public class StudentServiceImpl implements StudentService {
    private Student[] students;
    private int currentSize;
    private static final int MAX_CAPACITY = 100;

    public StudentServiceImpl() {
        this.students = new Student[MAX_CAPACITY];
        this.currentSize = 0;
    }

    public StudentServiceImpl(int capacity) {
        this.students = new Student[capacity];
        this.currentSize = 0;
    }

    @Override
    public void addStudent(Student student) throws StorageFullException, InvalidInputException {
        // Validate student data
        validateStudent(student);

        // Check if storage is full
        if (currentSize >= students.length) {
            throw new StorageFullException();
        }

        // Check for duplicate ID
        for (int i = 0; i < currentSize; i++) {
            if (students[i].getId() == student.getId()) {
                throw new InvalidInputException("Student with ID " + student.getId() + " already exists!");
            }
        }

        students[currentSize++] = student;
        System.out.println("\n✓ Student added successfully!");
    }

    @Override
    public void viewAllStudents() {
        if (currentSize == 0) {
            System.out.println("\n⚠ No students found in the system!");
            return;
        }

        System.out.println("\n" + "=".repeat(100));
        System.out.println("                              STUDENT RECORDS");
        System.out.println("=".repeat(100));

        for (int i = 0; i < currentSize; i++) {
            System.out.println(students[i]);
        }

        System.out.println("=".repeat(100));
        System.out.println("Total Students: " + currentSize);
    }

    @Override
    public Student searchStudentById(int id) throws StudentNotFoundException {
        for (int i = 0; i < currentSize; i++) {
            if (students[i].getId() == id) {
                return students[i];
            }
        }
        throw new StudentNotFoundException(id);
    }

    @Override
    public Student[] searchStudentsByName(String name) {
        // First, count matching students
        int count = 0;
        for (int i = 0; i < currentSize; i++) {
            if (students[i].getName().toLowerCase().contains(name.toLowerCase())) {
                count++;
            }
        }

        // Create array of matching students
        Student[] results = new Student[count];
        int index = 0;
        for (int i = 0; i < currentSize; i++) {
            if (students[i].getName().toLowerCase().contains(name.toLowerCase())) {
                results[index++] = students[i];
            }
        }

        return results;
    }

    @Override
    public Student searchStudentByEmail(String email) throws StudentNotFoundException {
        for (int i = 0; i < currentSize; i++) {
            if (students[i].getEmail().equalsIgnoreCase(email)) {
                return students[i];
            }
        }
        throw new StudentNotFoundException("Student with email " + email + " not found!");
    }

    @Override
    public void updateStudent(int id, Student updatedStudent) throws StudentNotFoundException, InvalidInputException {
        // Validate updated student data
        validateStudent(updatedStudent);

        for (int i = 0; i < currentSize; i++) {
            if (students[i].getId() == id) {
                // Keep the same ID
                updatedStudent.setId(id);
                students[i] = updatedStudent;
                System.out.println("\n✓ Student updated successfully!");
                return;
            }
        }
        throw new StudentNotFoundException(id);
    }

    @Override
    public void updateStudentField(int id, String field, Object value) throws StudentNotFoundException, InvalidInputException {
        Student student = searchStudentById(id);

        switch (field.toLowerCase()) {
            case "name":
                if (value == null || value.toString().trim().isEmpty()) {
                    throw new InvalidInputException("Name cannot be empty!");
                }
                // Validate name format - only letters and spaces allowed
                if (!value.toString().matches("^[a-zA-Z ]+$")) {
                    throw new InvalidInputException("Name must contain only letters and spaces (no numbers or special characters)!");
                }
                student.setName(value.toString());
                break;

            case "age":
                int age = (int) value;
                if (age < 18 || age > 40) {
                    throw new InvalidInputException("Age must be between 18 and 40!");
                }
                student.setAge(age);
                break;

            case "course":
                if (value == null || value.toString().trim().isEmpty()) {
                    throw new InvalidInputException("Course cannot be empty!");
                }
                student.setCourse(value.toString());
                break;

            case "email":
                if (value == null || value.toString().trim().isEmpty()) {
                    throw new InvalidInputException("Email cannot be empty!");
                }
                // Validate email format
                if (!value.toString().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                    throw new InvalidInputException("Invalid email format!");
                }
                student.setEmail(value.toString().toLowerCase());
                break;

            case "marks":
                double marks = (double) value;
                if (marks < 0 || marks > 100) {
                    throw new InvalidInputException("Marks must be between 0 and 100!");
                }
                student.setMarks(marks);
                break;

            default:
                throw new InvalidInputException("Invalid field: " + field);
        }

        System.out.println("\n✓ Student " + field + " updated successfully!");
    }

    @Override
    public void deleteStudent(int id) throws StudentNotFoundException {
        for (int i = 0; i < currentSize; i++) {
            if (students[i].getId() == id) {
                // Shift elements to fill the gap
                for (int j = i; j < currentSize - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[currentSize - 1] = null;
                currentSize--;
                System.out.println("\n✓ Student deleted successfully!");
                return;
            }
        }
        throw new StudentNotFoundException(id);
    }

    @Override
    public int getStudentCount() {
        return currentSize;
    }

    /**
     * Validates student data
     */
    private void validateStudent(Student student) throws InvalidInputException {
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            throw new InvalidInputException("Name cannot be empty!");
        }

        // Validate name format - only letters and spaces allowed
        if (!student.getName().matches("^[a-zA-Z ]+$")) {
            throw new InvalidInputException("Name must contain only letters and spaces (no numbers or special characters)!");
        }

        if (student.getAge() < 18 || student.getAge() > 40) {
            throw new InvalidInputException("Age must be between 18 and 40!");
        }

        if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
            throw new InvalidInputException("Email cannot be empty!");
        }

        // Validate email format
        if (!student.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidInputException("Invalid email format!");
        }

        if (student.getCourse() == null || student.getCourse().trim().isEmpty()) {
            throw new InvalidInputException("Course cannot be empty!");
        }

        if (student.getMarks() < 0 || student.getMarks() > 100) {
            throw new InvalidInputException("Marks must be between 0 and 100!");
        }
    }
}