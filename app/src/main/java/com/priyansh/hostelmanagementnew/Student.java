package com.priyansh.hostelmanagementnew;

// Used in staff module
public class Student {
    private String name;
    private String enrollment;
    private String status;

    // Constructor
    public Student(String name, String status) {
        this.name = name;
        this.status = status;
    }

    // Getter and Setter methods
    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
