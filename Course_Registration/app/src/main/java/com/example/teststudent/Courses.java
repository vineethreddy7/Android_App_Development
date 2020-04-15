package com.example.teststudent;

public class Courses {
    private String name;
    private double fees;
    private double hours;

    public Courses(String name, double fees, double hours) {
        this.name = name;
        this.fees = fees;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public double getFees() {
        return fees;
    }

    public double getHours() {
        return hours;
    }
}
