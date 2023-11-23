package com.example.b07project;

public class EventFeedbackStudent {
    private String comment;
    private float rating;

    // Required default constructor for Firebase
    public EventFeedbackStudent() {
    }

    public EventFeedbackStudent(String comment, float rating) {
        this.comment = comment;
        this.rating = rating;
    }

    // Getter methods
    public String getComment() {
        return comment;
    }

    public float getRating() {
        return rating;
    }

}
