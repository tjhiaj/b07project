package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.annotation.NonNull;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class ScheduleEvents extends AppCompatActivity {

    private static final String TAG = "AdminEventsActivity";

    private FirebaseDatabase database;
    private Button scheduleEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_events);

        FirebaseDatabase.getInstance().setPersistenceEnabled(false);
        database = FirebaseDatabase.getInstance("https://b07project-7eb3d-default-rtdb.firebaseio.com/");

        scheduleEventButton = findViewById(R.id.scheduleEventButton);

        if (database == null) return;

        scheduleEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference eventsRef = database.getReference("events");
                EditText eventNameEditText = findViewById(R.id.eventNameEditText);
                EditText participantLimitEditText = findViewById(R.id.participantLimitEditText);
                EditText descriptionEditText = findViewById(R.id.description);

                String eventName = eventNameEditText.getText().toString().trim();
                String participantLimitStr = participantLimitEditText.getText().toString().trim();
                String description = descriptionEditText.getText().toString().trim();

                if (eventsRef == null || eventName.isEmpty() || participantLimitStr.isEmpty()) {
                    return;
                }
                String eventKey = eventsRef.push().getKey();
                List<String> comments = new ArrayList<>();
                comments.add("placeholder");

                List<String> rating = new ArrayList<>();
                rating.add("placeholder");

                List<String> participants = new ArrayList<>();
                participants.add("placeholder");


                int participantLimit = Integer.parseInt(participantLimitStr);
                Log.i("pretty", "before Event");
                Event newEvent = new Event(eventName, description, 0, 0, comments, rating, eventKey, participantLimit, participants);
                Log.i("pretty", "before push");
                eventsRef.child(eventKey).setValue(newEvent, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@NonNull DatabaseError error, @NonNull DatabaseReference ref) {
                        if (error == null) {
                            Log.i("pretty", "after push");
                            Log.i("pretty", newEvent.getComments().get(0));
                            // Event added successfully
                            eventNameEditText.setText("");
                            participantLimitEditText.setText("");
                            descriptionEditText.setText("");

                            Toast.makeText(ScheduleEvents.this, "Event scheduled", Toast.LENGTH_SHORT).show();
                        } else {
                            // Error adding event
                            Log.e(TAG, "Failed to schedule event: " + error.getMessage(), error.toException());
                        }
                    }
                });
            }
        });
    }

    public void onAdminEventsBackButtonClick(View view) {
        Intent intent = new Intent(this, AdminHomeActivity.class);
        startActivity(intent);
        finish();
    }
}