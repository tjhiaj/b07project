package com.example.b07project;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Event implements Parcelable {
    private String eventName;

    private int participantLimit;

    // Required default constructor for Firebase

    public Event(String eventName, int participantLimit) {
        this.eventName = eventName;
        this.participantLimit = participantLimit;
    }

    // Getter methods
    private String eventDescription;
    private int imageResourceId;
    private float averageRating;
    private int[] ratings;
    private List<String> comments;

    public Event(String eventName, String eventDescription, int imageResourceId, float averageRating, List<String> comments, int[] ratings) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.imageResourceId = imageResourceId;
        this.averageRating = averageRating;
        this.comments = comments;
        this.ratings = ratings;
    }

    protected Event(Parcel in) {
        eventName = in.readString();
        eventDescription = in.readString();
        imageResourceId = in.readInt();
        averageRating = in.readFloat();
        comments = in.createStringArrayList();
        ratings = in.createIntArray();
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

    public int getParticipantLimit() {
        return participantLimit;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public int getImageResourceId(){return imageResourceId;}

    public float getRating() {return averageRating;}

    public List<String> getComments() {return comments;}
    public int[] getRatings(){return ratings;}

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
        dest.writeIntArray(ratings);
    }
}
