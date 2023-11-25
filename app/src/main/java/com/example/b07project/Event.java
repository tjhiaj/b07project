package com.example.b07project;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Event implements Parcelable {
    private String eventName;

    private int participantLimit;
    private String eventDescription;
    private int imageResourceId;
    private float averageRating;
    private List<String> ratings;
    private List<String> comments;

    public List<String> getParticipantList() {
        return participantList;
    }

    public List<String> participantList;

    public void setParticipantList(List<String> participantList){
        this.participantList = participantList;
    }


    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setParticipantLimit(int participantLimit) {
        this.participantLimit = participantLimit;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public void setRatings( List<String> ratings) {
        this.ratings = ratings;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    private String eventID;



    public Event(String eventName, String eventDescription, int imageResourceId, float averageRating, List<String> comments,  List<String> ratings, String eventID, int participantLimit, List<String> participantList) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.imageResourceId = imageResourceId;
        this.averageRating = averageRating;
        this.comments = comments;
        this.ratings = ratings;
        this.eventID = eventID;
        this.participantLimit = participantLimit;
        this.participantList = participantList;


    }

    protected Event(Parcel in) {
        eventName = in.readString();
        eventDescription = in.readString();
        imageResourceId = in.readInt();
        averageRating = in.readFloat();
        comments = in.createStringArrayList();
        ratings = in.createStringArrayList();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getEventName() {
        return eventName;
    }


    public String getEventID(){return eventID;}

    public int getParticipantLimit() {
        return participantLimit;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public int getImageResourceId(){return imageResourceId;}

    public float getRating() {return averageRating;}

    public List<String> getComments() {return comments;}
    public  List<String> getRatings(){return ratings;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(eventName);
        dest.writeString(eventDescription);
        dest.writeInt(imageResourceId);
        dest.writeFloat(averageRating);
        dest.writeStringList(comments);
        dest.writeStringList(ratings);
    }
}
