package com.priyansh.hostelmanagementnew;

// Used in mess module
public class Student_mess {
    private String messName;
    private String messEnrollment;
    private String messStatus;

    // Constructor
    public Student_mess(String messName, String messStatus) {
        this.messName = messName;
        this.messStatus = messStatus;
    }

    // Getter and Setter methods
    public String getMessEnrollment() {
        return messEnrollment;
    }

    public void setMessEnrollment(String messEnrollment) {
        this.messEnrollment = messEnrollment;
    }

    public String getMessName() {
        return messName;
    }

    public void setMessName(String messName) {
        this.messName = messName;
    }

    public String getMessStatus() {
        return messStatus;
    }

    public void setMessStatus(String messStatus) {
        this.messStatus = messStatus;
    }
}
