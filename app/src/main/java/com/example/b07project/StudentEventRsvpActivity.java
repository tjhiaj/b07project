package com.example.b07project;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentEventRsvpActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private Button rsvpButton;
    private TextView eventDetailsTitleTextView;

    private TextView top_header_events;

    private String eventId;


    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = auth.getCurrentUser();

    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.i("pretty", "whats going on");
        setContentView(R.layout.student_event_item);

        eventDetailsTitleTextView = findViewById(R.id.eventDetailsTitleTextView);
        top_header_events = findViewById(R.id.top_header_events);

 //       FirebaseDatabase.getInstance().setPersistenceEnabled(false);
        database = FirebaseDatabase.getInstance("https://b07project-7eb3d-default-rtdb.firebaseio.com/");

        rsvpButton = findViewById(R.id.RsvpButton);

        if (database == null) return;

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("EVENT")) {
            event = intent.getParcelableExtra("EVENT");
        }
        assert event != null;
        eventId = event.getEventID();

//

        if(eventId == null)
            Log.i("pretty", "AAAAANULL");
        Log.i("pretty2", event.toString());

        String eventName = event.getEventName();

        eventDetailsTitleTextView.setText(eventName);

        top_header_events.setText(eventName);


        rsvpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("pretty", "OnClick");
                rsvpForEvent();
            }
        });

    }

    private void rsvpForEvent() {
        Log.i("pretty", "RSVP");
        DatabaseReference eventsRef = database.getReference("events").child(eventId);
        Log.i("pretty", "error found");
        DatabaseReference participantLimitRef = eventsRef.child("participantLimit");
        DatabaseReference participantsRef = eventsRef.child("participants");
        // Add the current user's UID to the "participants" node under the specific event
        Log.i("pretty", currentUser.getUid());

        participantLimitRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Participant limit is the value stored in the database
                    long participantLimit = dataSnapshot.getValue(Long.class);

                    // Check if the participant limit has been reached
                    participantsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getChildrenCount() >= participantLimit) {
                                // Participant limit reached, prevent user from signing up
                                Toast.makeText(StudentEventRsvpActivity.this, "Participant limit reached. Cannot RSVP.", Toast.LENGTH_SHORT).show();
                            } else {
                                // Participant limit not reached, add the user to the participants list
                                participantsRef.child(currentUser.getUid()).setValue(true)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(StudentEventRsvpActivity.this, "RSVP successful", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(StudentEventRsvpActivity.this, EventViewActivity.class);
                                                    startActivity(intent);
                                                } else {
                                                    Toast.makeText(StudentEventRsvpActivity.this, "RSVP failed", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Handle the error
                            Toast.makeText(StudentEventRsvpActivity.this, "Error checking participant limit", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    // Handle the case where participantLimitRef does not exist
                    Toast.makeText(StudentEventRsvpActivity.this, "Participant limit not available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
                Toast.makeText(StudentEventRsvpActivity.this, "Error retrieving participant limit", Toast.LENGTH_SHORT).show();
            }
        });

    }





}
