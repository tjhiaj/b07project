package com.example.b07project;

public class Announcement {
    private String subject;
    private String message;

    public Announcement(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}

