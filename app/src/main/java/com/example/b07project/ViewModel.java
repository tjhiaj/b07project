package com.example.b07project;

import java.util.ArrayList;
import java.util.LinkedList;

public class ViewModel {
    private String subject;
    private ArrayList<String> complaints;
    public ViewModel(String subject,ArrayList<String> complaints) {
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
