package com.sms.util;

import java.util.Scanner;

/**
 * Utility class for common operations
 */
public class InputValidator {

    /**
     * Validates and returns an integer input
     */
    public static int getValidInteger(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a valid number.");
            }
        }
    }

    /**
     * Validates and returns a double input
     */
    public static double getValidDouble(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a valid number.");
            }
        }
    }

    /**
     * Validates and returns a valid age (18-40 years)
     */
    public static int getValidAge(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                int age = Integer.parseInt(input);

                if (age < 18 || age > 40) {
                    System.out.println("✗ Invalid age! Age must be between 18 and 40 years.");
                    System.out.println("  This is a student management system for higher education.");
                    continue;
                }

                return age;
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a valid number.");
            }
        }
    }

    /**
     * Validates and returns a non-empty string
     */
    public static String getValidString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("✗ Input cannot be empty! Please try again.");
        }
    }

    /**
     * Validates and returns a valid name (alphabets and spaces only)
     */
    public static String getValidName(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            // Check if empty
            if (input.isEmpty()) {
                System.out.println("✗ Name cannot be empty! Please try again.");
                continue;
            }

            // Check if contains only alphabets and spaces
            if (input.matches("[a-zA-Z ]+")) {
                return input;
            } else {
                System.out.println("✗ Invalid name! Name should contain only letters and spaces (no numbers or special characters).");
                System.out.println("  Valid examples: 'John Doe', 'Alice Johnson', 'Bob Smith'");
                System.out.println("  Invalid examples: 'John123', 'Alice@', 'Bob_Smith'");
            }
        }
    }

    /**
     * Validates and returns a valid email address
     */
    public static String getValidEmail(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            // Check if empty
            if (input.isEmpty()) {
                System.out.println("✗ Email cannot be empty! Please try again.");
                continue;
            }

            // Simple email validation pattern
            // Format: text@text.text
            if (input.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                return input.toLowerCase(); // Store emails in lowercase for consistency
            } else {
                System.out.println("✗ Invalid email format!");
                System.out.println("  Valid examples: 'john@example.com', 'alice.smith@university.edu'");
                System.out.println("  Invalid examples: 'john@', '@example.com', 'alice.example.com'");
            }
        }
    }

    /**
     * Pauses execution until user presses Enter
     */
    public static void pressEnterToContinue(Scanner scanner) {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    /**
     * Clears console (simulated with line breaks)
     */
    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}