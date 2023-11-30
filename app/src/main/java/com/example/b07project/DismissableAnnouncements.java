package com.example.b07project;

public class DismissableAnnouncements {
    private String subject;
    private String message;
    private String id;

    public DismissableAnnouncements(String subject, String message, String id) {
        this.subject = subject;
        this.message = message;
        this.id = id;
    }

    public String getDismissableSubject() {
        return subject;
    }

    public String getDismissableMessage() {
        return message;
    }

    public String getDismissableId(){return id;}
}
