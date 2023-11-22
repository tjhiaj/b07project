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

                String eventName = eventNameEditText.getText().toString().trim();
                String participantLimitStr = participantLimitEditText.getText().toString().trim();

                if (eventsRef == null || eventName.isEmpty() || participantLimitStr.isEmpty()) {
                    return;
                }

                int participantLimit = Integer.parseInt(participantLimitStr);

                Event newEvent = new Event(eventName, participantLimit);

                eventsRef.push().setValue(newEvent, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@NonNull DatabaseError error, @NonNull DatabaseReference ref) {
                        if (error == null) {
                            // Event added successfully
                            eventNameEditText.setText("");
                            participantLimitEditText.setText("");
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