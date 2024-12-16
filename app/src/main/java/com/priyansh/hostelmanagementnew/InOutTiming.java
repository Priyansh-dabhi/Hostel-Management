package com.priyansh.hostelmanagementnew;

public class InOutTiming {
    private String enrollment;
    private String status;
    private String timestamp;

    // Constructor accepting enrollment, status, and timestamp
    public InOutTiming(String enrollment, String status, String timestamp) {
        this.enrollment = enrollment;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public String getStatus() {
        return status;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
