package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentEventRsvpActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private Button rsvpButton;
    private TextView eventDetailsTitleTextView;

    private TextView top_header_events;

    private String eventId; // Assume you receive the eventId from the previous activity

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = auth.getCurrentUser();

    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_event_item);

        eventDetailsTitleTextView = findViewById(R.id.eventDetailsTitleTextView);
        top_header_events = findViewById(R.id.top_header_events);

        FirebaseDatabase.getInstance().setPersistenceEnabled(false);
        database = FirebaseDatabase.getInstance("https://b07project-7eb3d-default-rtdb.firebaseio.com/");

        rsvpButton = findViewById(R.id.RsvpButton);

        if (database == null) return;

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("EVENT")) {
            event = intent.getParcelableExtra("EVENT");
        }



        eventId = getIntent().getStringExtra("eventId");
        String eventName = event.getEventID();

        eventDetailsTitleTextView.setText(eventName);

        top_header_events.setText(eventName);

        rsvpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rsvpForEvent();
            }
        });

    }

    private void rsvpForEvent() {
        DatabaseReference eventsRef = database.getReference("events").child(eventId).child("participants");

        // Check if the student has already RSVP'd
        // This is a basic example and might not be the most efficient way to check
        // In a real-world scenario, you might want to improve this logic
        eventsRef.child(currentUser.getUid()).setValue(true).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(StudentEventRsvpActivity.this, "RSVP successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StudentEventRsvpActivity.this, "RSVP failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
