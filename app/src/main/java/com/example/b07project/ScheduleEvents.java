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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ScheduleEvents extends AppCompatActivity {

    private static final String TAG = "AdminEventsActivity";

    private FirebaseDatabase database;
    private Button scheduleEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_admin_event);

        database = FirebaseDatabase.getInstance("https://b07project-7eb3d-default-rtdb.firebaseio.com/");

        scheduleEventButton = findViewById(R.id.scheduleEventButton2);

        if (database == null) return;

        scheduleEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference eventsRef = database.getReference("events");
                EditText eventNameEditText = findViewById(R.id.eventNameTextBox);
                EditText participantLimitEditText = findViewById(R.id.participantLimitTextBox);
                EditText descriptionEditText = findViewById(R.id.eventDescriptionTextBox);

                EditText yearEditText = findViewById(R.id.yearEventTextBox);
                EditText monthEditText = findViewById(R.id.monthEventTextBox);
                EditText dayEditText = findViewById(R.id.dayEventTextBox);
                EditText hourEditText = findViewById(R.id.hourTextBox);
                EditText minuteEditText = findViewById(R.id.minuteTextBox);

                if (eventNameEditText.getText().toString().isEmpty() ||
                        participantLimitEditText.getText().toString().isEmpty() ||
                        descriptionEditText.getText().toString().isEmpty() ||
                        yearEditText.getText().toString().isEmpty() ||
                        monthEditText.getText().toString().isEmpty() ||
                        dayEditText.getText().toString().isEmpty() ||
                        hourEditText.getText().toString().isEmpty() ||
                        minuteEditText.getText().toString().isEmpty()) {

                    // Show an error message or toast indicating that all fields must be filled
                    Toast.makeText(ScheduleEvents.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }


                int year = Integer.parseInt(yearEditText.getText().toString());
                int month = Integer.parseInt(monthEditText.getText().toString());
                int day = Integer.parseInt(dayEditText.getText().toString());
                int hour = Integer.parseInt(hourEditText.getText().toString());
                int minute = Integer.parseInt(minuteEditText.getText().toString());


                String eventName = eventNameEditText.getText().toString().trim();
                String participantLimitStr = participantLimitEditText.getText().toString().trim();
                String description = descriptionEditText.getText().toString().trim();

                if(month > 12 || month < 1){
                    Toast.makeText(ScheduleEvents.this, "Invalid month", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (day >31){
                    Toast.makeText(ScheduleEvents.this, "Invalid day", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (hour >24 || hour < 0){
                    Toast.makeText(ScheduleEvents.this, "Invalid day", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (minute > 60 || minute < 0){
                    Toast.makeText(ScheduleEvents.this, "Invalid minute", Toast.LENGTH_SHORT).show();
                    return;

                }


                if (eventsRef == null || eventName.isEmpty() || participantLimitStr.isEmpty()) {
                    return;
                }
                String eventKey = eventsRef.push().getKey();
                List<String> comments = new ArrayList<>();
                comments.add("placeholder");

                List<Integer> rating = new ArrayList<>();
                for(int i = 0;i<5;i++){
                    rating.add(0);
                }

                List<String> participants = new ArrayList<>();
                participants.add("placeholder");

                LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 19, 12, 30);
                //localDateTime.withYear(year).withMonth(month).withDayOfMonth(day).withHour(hour).withMinute(minute);
                String formattedDateTime = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);



                int participantLimit = Integer.parseInt(participantLimitStr);
                Log.i("pretty", "before Event");
                Event newEvent = new Event(eventName, description, R.drawable.default_event, 0, comments, rating, eventKey, participantLimit, participants,formattedDateTime);
                Log.i("pretty", "before push");
                eventsRef.child(eventKey).setValue(newEvent, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@NonNull DatabaseError error, @NonNull DatabaseReference ref) {

                        NotificationHelper.showNotification(ScheduleEvents.this, "New Event!", eventName + " - " + description, NotificationType.generateRandomEventId());

                        if (error == null) {
                            Log.i("pretty", "after push");
//                            Log.i("pretty", newEvent.getComments().get(0));
                            // Event added successfully
                            eventNameEditText.setText("");
                            participantLimitEditText.setText("");
                            descriptionEditText.setText("");
                            yearEditText.setText("");
                            monthEditText.setText("");
                            dayEditText.setText("");
                            hourEditText.setText("");
                            minuteEditText.setText("");

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
        Intent intent = new Intent(this, ScheduleOrViewActivity.class);
        startActivity(intent);
        finish();
    }
}