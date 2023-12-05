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

    private String eventId;
    private int participantLimit;

    String eventName;

    String description;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = auth.getCurrentUser();

    Event event;

    public void OnRsvpEventsBackButtonClick(View view){
        Intent intent = new Intent(this, EventViewActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.student_event_item);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("EVENT")) {
            event = intent.getParcelableExtra("EVENT");
        }

        assert event != null;
        eventId = event.getEventID();

        eventDetailsTitleTextView = findViewById(R.id.eventDetailsTitleTextView);
        eventDetailsTitleTextView.setText(event.getEventName());
        String participants = "Participant limit:" + event.getParticipantLimit();
        TextView eventDetailsParticipantLimitTextView = findViewById(R.id.eventDetailsParticipantLimitTextView);
        eventDetailsParticipantLimitTextView.setText(participants);
        TextView eventDetailsDescriptionTextView = findViewById(R.id.eventDetailsDescriptionTextView);
        eventDetailsDescriptionTextView.setText(event.getEventDescription());
        TextView eventDetailsDateTimeTextView = findViewById(R.id.eventDetailsDateTimeTextView);
        eventDetailsDateTimeTextView.setText(event.getLocalDateTime());



        database = FirebaseDatabase.getInstance("https://b07project-7eb3d-default-rtdb.firebaseio.com/");
        DatabaseReference eventsIDRef = database.getReference("events").child(eventId);
        DatabaseReference participantLimitRef = eventsIDRef.child("participantLimit");
        DatabaseReference eventNameRef = eventsIDRef.child("eventName");
        DatabaseReference descriptionRef = eventsIDRef.child("description");




        rsvpButton = findViewById(R.id.RsvpButton);

        if (database == null) return;


        eventNameRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String eventNameValue = dataSnapshot.getValue(String.class);
                // Assign eventNameValue to the eventName variable
                eventName = eventNameValue;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

        descriptionRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String descriptionValue = dataSnapshot.getValue(String.class);
                // Assign descriptionValue to the description variable
                description = descriptionValue;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });


        participantLimitRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Integer participantLimitValue = dataSnapshot.getValue(Integer.class);
                // Assign participantLimitValue to the participantLimit variable
                participantLimit = participantLimitValue != null ? participantLimitValue : 0; // Default to 0 if null
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

        rsvpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rsvpForEvent();
            }
        });

    }

    private void rsvpForEvent() {
        DatabaseReference eventsRef = database.getReference("events").child(eventId);
        DatabaseReference participantsRef = eventsRef.child("participants");


        participantsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Get the current list of participants
                    List<String> participantsList = new ArrayList<>();
                    for (DataSnapshot participantSnapshot : dataSnapshot.getChildren()) {
                        String participantUID = participantSnapshot.getValue(String.class);
                        participantsList.add(participantUID);
                    }
                    Log.i("CONRAD", "DONE FILLING IT UP");


                    // Add the current user's UID to the list
                    participantsList.add(currentUser.getUid());
                    Log.i("CONRAD", participantsList.toString() + " new ARRAY HERE");

                    if (participantsList.size() - 1 <= participantLimit){
                        // Update the participantsRef with the modified list
                        Log.i("CONRAD", participantsList.toString() + "adding to database");
                        participantsRef.setValue(participantsList)
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

                    }else{
                        Toast.makeText(StudentEventRsvpActivity.this, "Participant limit reached. Can not rsvp.", Toast.LENGTH_SHORT).show();

                    }


                } else {
                    // Handle the case where participantsRef does not exist
                    Toast.makeText(StudentEventRsvpActivity.this, "Participants not available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
                Toast.makeText(StudentEventRsvpActivity.this, "Error retrieving participants", Toast.LENGTH_SHORT).show();
            }
        });
    }






}
