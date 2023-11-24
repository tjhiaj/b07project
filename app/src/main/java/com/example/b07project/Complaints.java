package com.example.b07project;

public class Complaints {
    private String subject;
    private String complaints;

    public Complaints(String subject, String complaints) {
        this.complaints = complaints;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public String getComplaints() {
        return complaints;
    }
}
