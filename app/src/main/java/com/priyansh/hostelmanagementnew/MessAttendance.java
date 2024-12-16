package com.priyansh.hostelmanagementnew;

public class MessAttendance {
    private String enrollment_mess;
    private String status_mess;
    private String timestamp_mess;

    public MessAttendance(String enrollment_mess, String status_mess, String timestamp_mess) {
        this.enrollment_mess = enrollment_mess;
        this.status_mess = status_mess;
        this.timestamp_mess = timestamp_mess;
    }

    public String getEnrollment_mess() {
        return enrollment_mess;
    }

    public String getStatus_mess() {
        return status_mess;
    }

    public String getTimestamp_mess() {
        return timestamp_mess;
    }
}
