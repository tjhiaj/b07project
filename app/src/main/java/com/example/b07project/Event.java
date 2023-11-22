package com.example.b07project;

public class Event {
    private String eventName;
    private String eventDescription;
    private int imageResourceId;

    public Event(String eventName, String eventDescription, int imageResourceId) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.imageResourceId = imageResourceId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public int getImageResourceId(){return imageResourceId;}
}
