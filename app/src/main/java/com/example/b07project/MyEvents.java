package com.example.b07project;

import static android.content.ContentValues.TAG;

import static com.example.b07project.UserInfo.RoleType.Admin;
import static com.example.b07project.UserInfo.RoleType.Student;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MyEvents extends AppCompatActivity {
    EventAdapter adapter;
    List<Event> eventList = new ArrayList<>();
    FirebaseDatabase database;

    public void OnViewEventsBackButtonClick(View view){
        Intent intent = new Intent(this, MyEventsOrAllEventsActivity.class);
        startActivity(intent);
    }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            Log.i("pretty", "All Events");
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_events);
            database = FirebaseDatabase.getInstance("https://b07project-7eb3d-default-rtdb.firebaseio.com/");

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String currentUserId = currentUser != null ? currentUser.getUid() : null;


            DatabaseReference ref = database.getReference("events");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {
                        for(DataSnapshot eventSnapshot:dataSnapshot.getChildren()){

                            Event event = eventSnapshot.getValue(Event.class);
                            if(event!= null){
                                Log.i("event",event.toString());
                                //participants
                                if (eventSnapshot.hasChild("participants")) {
                                    Object rawParticipants = eventSnapshot.child("participants").getValue();
                                    if (rawParticipants instanceof List<?>) {
                                        List<?> rawParticipantsList = (List<?>) rawParticipants;
                                        if (!rawParticipantsList.isEmpty() && allElementsAreString(rawParticipantsList)) {
                                            List<String> participantsList = convertList(rawParticipantsList, String.class);
                                            participantsList.remove(0);
                                            event.setParticipants(participantsList);
                                            Log.w("participants", participantsList.toString());
                                        } else {
                                            Log.w("participants", "List elements are not of type String");
                                        }
                                    } else {
                                        Log.w("participants", "comments is not a List");
                                    }
                                } else {
                                    Log.i("participants", "no child");
                                }
                                if (currentUserId != null && event.getParticipants() != null && event.getParticipants().contains(currentUserId)) {
                                    //eventName
                                    if (eventSnapshot.hasChild("eventName")) {
                                        String eventName = eventSnapshot.child("eventName").getValue(String.class);
                                        event.setEventName(eventName);
                                        assert eventName != null;
                                        Log.i("eventName", eventName);
                                    } else {
                                        Log.i("eventName", "no child");
                                    }
                                    //eventDescription
                                    if (eventSnapshot.hasChild("eventDescription")) {
                                        String eventDescription = eventSnapshot.child("eventDescription").getValue(String.class);
                                        event.setEventDescription(eventDescription);
                                        assert eventDescription != null;
                                        Log.i("eventDescription", eventDescription);
                                    } else {
                                        Log.i("eventDescription", "no child");
                                    }
                                    //LocalDateTime
                                    if (eventSnapshot.hasChild("localDateTime")) {
                                        String localDateTime = eventSnapshot.child("localDateTime").getValue().toString();
                                        event.setLocalDateTime(localDateTime);
                                        Log.i("localDateTime", localDateTime);
                                    } else {
                                        Log.i("localDateTime", "no child");
                                    }
                                    //eventID
                                    if (eventSnapshot.hasChild("eventID")) {
                                        String eventID = eventSnapshot.child("eventID").getValue(String.class);
                                        event.setEventID(eventID);
                                        assert eventID != null;
                                        Log.i("eventID", eventID);
                                    } else {
                                        Log.i("eventID", "no child");
                                    }
                                    ;
                                    //imageSourceId
                                    if (eventSnapshot.hasChild("imageResourceId")) {
                                        Integer imageSourceId = eventSnapshot.child("imageResourceId").getValue(Integer.class);
                                        if (imageSourceId != null) {
                                            event.setImageResourceId(imageSourceId);
                                            Log.i("imageResourceId", imageSourceId.toString());
                                        } else {
                                            Log.i("imageResourceId", "null");
                                        }
                                    } else {
                                        Log.i("imageResourceId", "no child");
                                    }
                                    //averageRating
                                    if (eventSnapshot.hasChild("averageRating")) {
                                        Float averageRating = eventSnapshot.child("averageRating").getValue(Float.class);
                                        if (averageRating != null) {
                                            event.setAverageRating(averageRating);
                                            Log.i("averageRating", averageRating.toString());
                                        } else {
                                            Log.i("averageRating", "null");
                                        }
                                    } else {
                                        Log.i("averageRating", "no child");
                                    }
                                    //Comments
                                    if (eventSnapshot.hasChild("comments")) {
                                        Object rawComments = eventSnapshot.child("comments").getValue();
                                        if (rawComments instanceof List<?>) {
                                            List<?> rawCommentsList = (List<?>) rawComments;
                                            if (!rawCommentsList.isEmpty() && allElementsAreString(rawCommentsList)) {
                                                List<String> commentsList = convertList(rawCommentsList, String.class);
                                                commentsList.remove(0);
                                                event.setComments(commentsList);
                                                Log.w("comments", commentsList.toString());
                                            } else {
                                                Log.w("comments", "List elements are not of type String");
                                            }
                                        } else {
                                            Log.w("comments", "comments is not a List");
                                        }
                                    } else {
                                        Log.i("comments", "no child");
                                    }
                                    //Ratings
                                    if (eventSnapshot.hasChild("ratings")) {
                                        Object rawRatings = eventSnapshot.child("ratings").getValue();
                                        if (rawRatings instanceof List<?>) {
                                            List<?> rawRatingsList = (List<?>) rawRatings;
                                            if (!rawRatingsList.isEmpty() && allElementsAreInteger(rawRatingsList)) {
                                                List<Integer> ratingsList = convertList(rawRatingsList, Integer.class);
                                                event.setRatings(ratingsList);
                                                Log.w("ratings", ratingsList.toString());
                                            } else {
                                                if (rawRatingsList.isEmpty()) {
                                                    Log.w("ratings", "List is empty");
                                                } else {
                                                    Log.w("ratings", "List elements are not of type Integer");
                                                }
                                            }
                                        } else {
                                            Log.w("ratings", "ratings is not a List");
                                        }
                                    } else {
                                        Log.i("ratings", "no child");
                                    }

                                    //participantLimit
                                    if (eventSnapshot.hasChild("participantLimit")) {
                                        Integer participantLimit = eventSnapshot.child("participantLimit").getValue(Integer.class);
                                        if (participantLimit != null) {
                                            event.setParticipantLimit(participantLimit);
                                            Log.i("participantLimit", participantLimit.toString());
                                        } else {
                                            Log.i("participantLimit", "null");
                                        }
                                    } else {
                                        Log.i("participantLimit", "no child");
                                    }

                                    eventList.add(event);
                                }
                                }
                        }

                        if (!eventList.isEmpty()) {
                            if (!isDestroyed()) {
                                // Set up RecyclerView
                                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                                adapter = new EventAdapter(MyEvents.this, eventList, MyEvents.class);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(MyEvents.this));
                            }
                        } else {
                            Log.e(TAG, "List is empty");
                        }
                    }
                    else {
                        Log.d(TAG, "Data does not exist");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle cancellation
                }
            });
        }


    private static boolean allElementsAreInteger(List<?> integerList) {
        for (Object element : integerList) {
            if (!(element instanceof Integer)) {
                return false;
            }
        }
        return true;
    }
    private static boolean allElementsAreString(List<?> list) {
        for (Object element : list) {
            if (!(element instanceof String)) {
                return false;
            }
        }
        return true;
    }
    private static <T> List<T> convertList(List<?> list, Class<T> targetType) {
        List<T> result = new ArrayList<>();
        for (Object element : list) {
            result.add(targetType.cast(element));
        }
        return result;
    }


}