package com.sms.model;

/**
 * Student class demonstrating Encapsulation
 * All fields are private with public getters and setters
 */
public class Student extends Entity {
    private String name;
    private int age;
    private String course;
    private double marks;
    private String email;

    public Student(int id, String name, int age, String course, double marks, String email) {
        super(id);
        this.name = name;
        this.age = age;
        this.course = course;
        this.marks = marks;
        this.email = email;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("ID: %-5d | Name: %-20s | Age: %-3d | Email: %-25s | Course: %-15s | Marks: %.2f",
                id, name, age, email, course, marks);
    }
}