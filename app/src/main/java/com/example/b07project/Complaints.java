package com.example.b07project;

import java.util.ArrayList;

public class Complaints {
    private String subject;
    private ArrayList<String> complaints;

    public Complaints(String subject, ArrayList<String> complaints) {
        this.complaints = complaints;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public ArrayList<String> getComplaints() {
        return complaints;
    }
}
