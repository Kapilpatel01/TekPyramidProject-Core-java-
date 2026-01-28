package com.sms.main;

import com.sms.model.Student;
import com.sms.service.StudentService;
import com.sms.service.StudentServiceImpl;
import com.sms.exception.StudentNotFoundException;
import com.sms.exception.StorageFullException;
import com.sms.exception.InvalidInputException;
import com.sms.util.InputValidator;

import java.util.Scanner;

/**
 * Main Application Class
 * Menu-driven console application for Student Management System
 */
public class StudentManagementApp {
    private static StudentService studentService;
    private static Scanner scanner;

    public static void main(String[] args) {
        studentService = new StudentServiceImpl(100);
        scanner = new Scanner(System.in);

        boolean exit = false;

        displayWelcomeBanner();

        while (!exit) {
            try {
                displayMenu();
                int choice = InputValidator.getValidInteger(scanner, "Enter your choice: ");

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        searchStudent();
                        break;
                    case 4:
                        updateStudent();
                        break;
                    case 5:
                        deleteStudent();
                        break;
                    case 6:
                        exit = true;
                        displayExitMessage();
                        break;
                    default:
                        System.out.println("\n✗ Invalid choice! Please select a valid option (1-6).");
                }

                if (!exit) {
                    InputValidator.pressEnterToContinue(scanner);
                }

            } catch (Exception e) {
                System.out.println("\n✗ An unexpected error occurred: " + e.getMessage());
                InputValidator.pressEnterToContinue(scanner);
            }
        }

        scanner.close();
    }

    private static void displayWelcomeBanner() {
        System.out.println("\n" + "=".repeat(120));
        System.out.println("                            WELCOME TO STUDENT MANAGEMENT SYSTEM");
        System.out.println("=".repeat(120));
    }

    private static void displayMenu() {
        System.out.println("\n" + "=".repeat(120));
        System.out.println("                                        MAIN MENU");
        System.out.println("=".repeat(120));
        System.out.println("  1. Add Student");
        System.out.println("  2. View All Students");
        System.out.println("  3. Search Students");
        System.out.println("  4. Update Student Details");
        System.out.println("  5. Delete Student");
        System.out.println("  6. Exit");
        System.out.println("=".repeat(120));
    }

    private static void addStudent() {
        System.out.println("\n" + "-".repeat(120));
        System.out.println("                                    ADD NEW STUDENT");
        System.out.println("-".repeat(120));

        try {
            int id = InputValidator.getValidInteger(scanner, "Enter Student ID: ");
            String name = InputValidator.getValidName(scanner, "Enter Student Name: ");
            int age = InputValidator.getValidAge(scanner, "Enter Student Age (18-40): ");
            String email = InputValidator.getValidEmail(scanner, "Enter Student Email: ");
            String course = InputValidator.getValidString(scanner, "Enter Course Name: ");
            double marks = InputValidator.getValidDouble(scanner, "Enter Marks (0-100): ");

            Student student = new Student(id, name, age, course, marks, email);
            studentService.addStudent(student);

        } catch (StorageFullException e) {
            System.out.println("\n✗ Error: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("\n✗ Validation Error: " + e.getMessage());
        }
    }

    private static void viewAllStudents() {
        System.out.println("\n" + "-".repeat(120));
        System.out.println("                                    ALL STUDENTS");
        System.out.println("-".repeat(120));

        studentService.viewAllStudents();
    }

    private static void searchStudent() {
        System.out.println("\n" + "-".repeat(120));
        System.out.println("                                    SEARCH STUDENT");
        System.out.println("-".repeat(120));

        System.out.println("Search by:");
        System.out.println("  1. Student ID");
        System.out.println("  2. Student Name");
        System.out.println("  3. Student Email");
        System.out.println("  4. Cancel");
        System.out.println("-".repeat(120));

        int searchChoice = InputValidator.getValidInteger(scanner, "Enter your choice (1-4): ");

        try {
            switch (searchChoice) {
                case 1:
                    // Search by ID
                    int id = InputValidator.getValidInteger(scanner, "Enter Student ID to search: ");
                    Student student = studentService.searchStudentById(id);

                    System.out.println("\n✓ Student Found:");
                    System.out.println("=".repeat(120));
                    System.out.println(student);
                    System.out.println("=".repeat(120));
                    break;

                case 2:
                    // Search by Name
                    String name = InputValidator.getValidString(scanner, "Enter Student Name to search: ");
                    Student[] nameResults = studentService.searchStudentsByName(name);

                    if (nameResults.length == 0) {
                        System.out.println("\n⚠ No students found with name containing: " + name);
                    } else {
                        System.out.println("\n✓ Found " + nameResults.length + " student(s):");
                        System.out.println("=".repeat(120));
                        for (Student s : nameResults) {
                            System.out.println(s);
                        }
                        System.out.println("=".repeat(120));
                    }
                    break;

                case 3:
                    // Search by Email
                    String email = InputValidator.getValidEmail(scanner, "Enter Student Email to search: ");
                    Student emailStudent = studentService.searchStudentByEmail(email);

                    System.out.println("\n✓ Student Found:");
                    System.out.println("=".repeat(120));
                    System.out.println(emailStudent);
                    System.out.println("=".repeat(120));
                    break;

                case 4:
                    // Cancel
                    System.out.println("\n⚠ Search cancelled.");
                    return;

                default:
                    System.out.println("\n✗ Invalid choice!");
            }

        } catch (StudentNotFoundException e) {
            System.out.println("\n✗ " + e.getMessage());
        }
    }

    private static void updateStudent() {
        System.out.println("\n" + "-".repeat(120));
        System.out.println("                                    UPDATE STUDENT");
        System.out.println("-".repeat(120));

        try {
            int id = InputValidator.getValidInteger(scanner, "Enter Student ID to update: ");

            // First, check if student exists
            Student existingStudent = studentService.searchStudentById(id);

            System.out.println("\nCurrent Details:");
            System.out.println(existingStudent);

            // Display update options
            System.out.println("\n" + "-".repeat(120));
            System.out.println("Select what you want to update:");
            System.out.println("-".repeat(120));
            System.out.println("  1. Update Name Only");
            System.out.println("  2. Update Age Only");
            System.out.println("  3. Update Email Only");
            System.out.println("  4. Update Course Only");
            System.out.println("  5. Update Marks Only");
            System.out.println("  6. Update All Details");
            System.out.println("  7. Cancel Update");
            System.out.println("-".repeat(120));

            int updateChoice = InputValidator.getValidInteger(scanner, "Enter your choice (1-7): ");

            switch (updateChoice) {
                case 1:
                    // Update Name Only
                    String newName = InputValidator.getValidName(scanner, "Enter New Name: ");
                    studentService.updateStudentField(id, "name", newName);
                    break;

                case 2:
                    // Update Age Only
                    int newAge = InputValidator.getValidAge(scanner, "Enter New Age (18-40): ");
                    studentService.updateStudentField(id, "age", newAge);
                    break;

                case 3:
                    // Update Email Only
                    String newEmail = InputValidator.getValidEmail(scanner, "Enter New Email: ");
                    studentService.updateStudentField(id, "email", newEmail);
                    break;

                case 4:
                    // Update Course Only
                    String newCourse = InputValidator.getValidString(scanner, "Enter New Course: ");
                    studentService.updateStudentField(id, "course", newCourse);
                    break;

                case 5:
                    // Update Marks Only
                    double newMarks = InputValidator.getValidDouble(scanner, "Enter New Marks (0-100): ");
                    studentService.updateStudentField(id, "marks", newMarks);
                    break;

                case 6:
                    // Update All Details
                    System.out.println("\nEnter All New Details:");
                    String name = InputValidator.getValidName(scanner, "Enter New Name: ");
                    int age = InputValidator.getValidAge(scanner, "Enter New Age (18-40): ");
                    String email = InputValidator.getValidEmail(scanner, "Enter New Email: ");
                    String course = InputValidator.getValidString(scanner, "Enter New Course: ");
                    double marks = InputValidator.getValidDouble(scanner, "Enter New Marks (0-100): ");

                    Student updatedStudent = new Student(id, name, age, course, marks, email);
                    studentService.updateStudent(id, updatedStudent);
                    break;

                case 7:
                    // Cancel
                    System.out.println("\n⚠ Update cancelled.");
                    return;

                default:
                    System.out.println("\n✗ Invalid choice! Update cancelled.");
                    return;
            }

            // Show updated details
            System.out.println("\nUpdated Details:");
            Student updated = studentService.searchStudentById(id);
            System.out.println(updated);

        } catch (StudentNotFoundException e) {
            System.out.println("\n✗ " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("\n✗ Validation Error: " + e.getMessage());
        }
    }

    private static void deleteStudent() {
        System.out.println("\n" + "-".repeat(120));
        System.out.println("                                    DELETE STUDENT");
        System.out.println("-".repeat(120));

        try {
            int id = InputValidator.getValidInteger(scanner, "Enter Student ID to delete: ");

            // First, show the student to be deleted
            Student student = studentService.searchStudentById(id);
            System.out.println("\nStudent to be deleted:");
            System.out.println(student);

            System.out.print("\nAre you sure you want to delete this student? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("yes") || confirmation.equals("y")) {
                studentService.deleteStudent(id);
            } else {
                System.out.println("\n⚠ Delete operation cancelled.");
            }

        } catch (StudentNotFoundException e) {
            System.out.println("\n✗ " + e.getMessage());
        }
    }

    private static void displayExitMessage() {
        System.out.println("\n" + "=".repeat(120));
        System.out.println("                        Thank you for using Student Management System!");
        System.out.println("                                    Goodbye!");
        System.out.println("=".repeat(120));
    }
}