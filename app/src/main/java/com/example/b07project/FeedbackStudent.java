package com.example.b07project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FeedbackStudent extends AppCompatActivity {
    private static final String TAG = "FeedbackStudent";
    private FirebaseDatabase database;
    private Button submitFeedbackButton;
    private EditText commentEditText;
    private RatingBar ratingBar;

    private String eventName; //what we are trying to find

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_student);
        FirebaseDatabase.getInstance().setPersistenceEnabled(false);
        database = FirebaseDatabase.getInstance("https://b07project-7eb3d-default-rtdb.firebaseio.com/");

        submitFeedbackButton = findViewById(R.id.submitFeedbackButton);
        commentEditText = findViewById(R.id.commentEditText);
        ratingBar = findViewById(R.id.ratingBar);

        if (database == null) return;

        eventName = getIntent().getStringExtra("eventName");

        submitFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference eventsRef = database.getReference("events");

                Query eventQuery = eventsRef.orderByChild("eventName").equalTo(eventName);

                eventQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                            // Found the matching event
                            String eventId = eventSnapshot.getKey();
                            submitFeedbackToEvent(eventId);
                            return; // Exit the loop since we found the match
                        }

                        // If you reach here, no matching event was found
                        Toast.makeText(FeedbackStudent.this, "Event not found", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e(TAG, "Error searching for event: " + databaseError.getMessage(), databaseError.toException());
                    }

                });

            }

        });

    }
        private void submitFeedbackToEvent(String eventId) {
            DatabaseReference feedbackRef = database.getReference("events").child(eventId).child("feedback");

            String comment = commentEditText.getText().toString().trim();
            float rating = ratingBar.getRating();

            if (feedbackRef == null || comment.isEmpty()) {
                return;
            }

            EventFeedbackStudent eventFeedback = new EventFeedbackStudent(comment, rating);

            feedbackRef.push().setValue(eventFeedback, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    if (error == null) {
                        // Feedback submitted successfully
                        commentEditText.setText("");
                        ratingBar.setRating(0);
                        Toast.makeText(FeedbackStudent.this, "Feedback submitted", Toast.LENGTH_SHORT).show();

                    } else {
                        // Error submitting feedback
                        Log.e(TAG, "Failed to provide feedback: " + error.getMessage(), error.toException());
                    }
                }
            });














    }
    }
