package com.priyansh.hostelmanagementnew;

public class Attendance {
    private String enrollment_in_mess;
    private String status_in_mess;
    private String timestamp_in_mess;

    public Attendance(String enrollment_in_mess, String status_in_mess, String timestamp_in_mess) {
        this.enrollment_in_mess = enrollment_in_mess;
        this.status_in_mess = status_in_mess;
        this.timestamp_in_mess = timestamp_in_mess;
    }

    public String getEnrollment_in_mess() {
        return enrollment_in_mess;
    }

    public String getStatus_in_mess() {
        return status_in_mess;
    }

    public String getTimestamp_in_mess() {
        return timestamp_in_mess;
    }
}
