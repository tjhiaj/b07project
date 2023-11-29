package com.example.b07project;

import static android.content.ContentValues.TAG;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventViewActivity extends AppCompatActivity {
    EventAdapter adapter;
    List<Event> eventList = new ArrayList<>();
    FirebaseDatabase database;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);
        database = FirebaseDatabase.getInstance("https://b07project-7eb3d-default-rtdb.firebaseio.com/");

        // Dummy data for testing
        List<String> L1 = new ArrayList<>();
        L1.add("ab");
        L1.add("cd");
        List<Integer> L2 = new ArrayList<>();
        L2.add(2);
        L2.add(0);
        L2.add(3);
        L2.add(4);
        L2.add(1);
        List<String> L3 = new ArrayList<>();
        L3.add("Bob");
        LocalDateTime specificDate = LocalDateTime.of(2014, 1, 1, 10, 10, 30);
        /*eventList.add(new Event("Event 1", "This is the description for event 1.", R.drawable.default_event, 3, L1, L2, "NjtusLW-ySqra_V71MP", 100, L3, specificDate));
        eventList.add(new Event("Event 2", "This is the description for event 2.", R.drawable.default_event, 2, L1, L2, "id2", 110, L3, specificDate));
        eventList.add(new Event("Event 3", "This is the description for event 3.", R.drawable.default_event, 4, L1, L2, "id3", 100, L3, specificDate));*/

        Log.i("doubt it", "sad");
        DatabaseReference ref = database.getReference("events");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for(DataSnapshot eventSnapshot:dataSnapshot.getChildren()){
                        Event event = eventSnapshot.getValue(Event.class);
                        if(event!= null){
                        event.setEventName(dataSnapshot.child("eventName").getValue().toString());
                        event.setEventDescription(dataSnapshot.child("eventDescription").getValue().toString());
                        event.setImageResourceId((int) dataSnapshot.child("imageSourceId ").getValue());
                        event.setAverageRating((float) dataSnapshot.child("averageRating").getValue());
                        event.setComments((List<String>)dataSnapshot.child("comments").getValue());
                        event.setRatings((List<Integer>) dataSnapshot.child("ratings").getValue());
                        event.setEventID(dataSnapshot.child("eventID").getValue().toString());
                        event.setParticipantLimit((int) dataSnapshot.child("participantLimit").getValue());
                        event.setParticipants((List<String>) dataSnapshot.child("participants").getValue());
                        event.setLocalDateTime((LocalDateTime) dataSnapshot.child("localDateTime").getValue());
                        eventList.add(event);
                        }
                    }

                    if (!eventList.isEmpty()) {
                        if (!isDestroyed()) {
                            // Set up RecyclerView
                            RecyclerView recyclerView = findViewById(R.id.recyclerView);
                            adapter = new EventAdapter(EventViewActivity.this, eventList);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(EventViewActivity.this));
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

}

