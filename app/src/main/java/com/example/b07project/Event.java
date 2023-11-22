package com.example.b07project;

public class Event {
    private String eventName;
    private int participantLimit;

    // Required default constructor for Firebase
    public Event() {
    }

    public Event(String eventName, int participantLimit) {
        this.eventName = eventName;
        this.participantLimit = participantLimit;
    }

    // Getter methods
    public String getEventName() {
        return eventName;
    }

    public int getParticipantLimit() {
        return participantLimit;
    }
}
