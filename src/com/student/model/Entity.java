package com.student.model;


/**
 * Base Entity class demonstrating Inheritance
 * All domain objects extend this class
 */
public abstract class Entity {
    protected int id;

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public abstract String toString();
}
