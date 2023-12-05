package com.example.b07project;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


import java.util.ArrayList;
import java.util.List;

public class Event implements Parcelable {
    private String eventName;

    private int participantLimit;
    private String eventDescription;
    private int imageResourceId;
    private float averageRating;
    private List<Integer> ratings;
    private List<String> comments;

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    private List<String> participants;

    private String eventID;

    public float getAverageRating() {
        return averageRating;
    }

    private String localDateTime;
    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }
    public String getLocalDateTime() {
        return localDateTime;
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

    public void setRatings( List<Integer> ratings) {
        this.ratings = ratings;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public Event(){}
    public Event(String eventName, String eventDescription, int imageResourceId, float averageRating, List<String> comments,  List<Integer> ratings, String eventID, int participantLimit, List<String> participants, String localDateTime) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.imageResourceId = imageResourceId;
        this.averageRating = averageRating;
        this.comments = comments;
        this.ratings = ratings;
        this.eventID = eventID;
        this.participantLimit = participantLimit;
        this.participants = participants;
        this.localDateTime = localDateTime;
    }

   protected Event(Parcel in) {
       eventName = in.readString();
       eventDescription = in.readString();
       imageResourceId = in.readInt();
       averageRating = in.readFloat();
       comments = in.createStringArrayList();
       eventID = in.readString();
       int[] intArray = in.createIntArray();
       if (intArray != null){
           ratings = new ArrayList<>();
           for (int value : intArray){
               ratings.add(value);
           }
       }
       participants = in.createStringArrayList();
       localDateTime = in.readString();
       participantLimit = in.readInt();
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
    public  List<Integer> getRatings(){return ratings;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(eventName);
        dest.writeString(eventDescription);
        dest.writeInt(imageResourceId);
        dest.writeFloat(averageRating);
        dest.writeStringList(comments);
        dest.writeString(eventID);
        dest.writeIntArray(ratings.stream().mapToInt(i->i).toArray());
        dest.writeStringList(participants);
        dest.writeString(localDateTime);
        dest.writeInt(participantLimit);

    }
    @NonNull
    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + eventName + '\'' +
                ", participantLimit=" + participantLimit +
                ", eventDescription='" + eventDescription + '\'' +
                ", imageResourceId=" + imageResourceId +
                ", averageRating=" + averageRating +
                ", ratings=" + ratings +
                ", comments=" + comments +
                ", participants=" + participants +
                ", eventID='" + eventID + '\'' +
                ", localDateTime='" + localDateTime + '\'' +
                '}';
    }

}
