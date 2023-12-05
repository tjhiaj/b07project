package com.example.b07project;

public class Complaints {
    String subject;
    String complaints;

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
