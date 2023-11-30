package com.example.b07project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class FeedbackStudent extends AppCompatActivity {
    private static final String TAG = "FeedbackStudent";
    private FirebaseDatabase database;
    private Button submitFeedbackButton;
    private EditText commentEditText;
    private RatingBar ratingBar;

    private String eventID;

    private float rating;

    private List<Integer> ratings;

    Event event;

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

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("EVENT")) {
            event = intent.getParcelableExtra("EVENT");
        }

        eventID = event.getEventID();
        rating = event.getRating();
        ratings = event.getRatings();

        submitFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference feedbackRef = database.getReference("events").child(eventID);
                DatabaseReference commentsRef = feedbackRef.child("comments");
                DatabaseReference ratingRef = feedbackRef.child("rating");
                DatabaseReference ratingsRef = feedbackRef.child("ratings");
                List<Integer> ratingsList = ratings;



                String comment = commentEditText.getText().toString().trim();
                int intRating = (int) ratingBar.getRating();


                if (feedbackRef == null || comment.isEmpty()) {
                    return;
                }

                //increasing the number of people who gave the event a certain rating
                if (intRating >= 1 && intRating <= 5) {
                    int index = intRating - 1; // Adjust index to match the rating (1 to 5)
                    int currentCount = ratingsList.get(index);
                    ratingsList.set(index, currentCount + 1);

                commentsRef.push().setValue(comment);
                ratingsRef.setValue(ratingsList);

                //finds the new average rating now that the ratings list is updated to include the user's rating
                float newOverallRating = calculateNewOverallRating(rating, intRating, ratingsList.size());


                ratingRef.setValue(newOverallRating)  .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(FeedbackStudent.this, "Feedback submitted.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(FeedbackStudent.this, "Failed in submitting feedback.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });




                feedbackRef.child("comments").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Log the actual data
                        if (dataSnapshot.exists()) {
                            Log.d(TAG, "Data: " + dataSnapshot.getValue());
                        } else {
                            Log.d(TAG, "Data does not exist");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle the error
                        Toast.makeText(FeedbackStudent.this, "Error checking comments", Toast.LENGTH_SHORT).show();
                    }

                });

                feedbackRef.child("rating").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Log the actual data
                        if (dataSnapshot.exists()) {
                            Log.d(TAG, "Data: " + dataSnapshot.getValue());
                        } else {
                            Log.d(TAG, "Data does not exist");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle the error
                        Toast.makeText(FeedbackStudent.this, "Error checking rating", Toast.LENGTH_SHORT).show();
                    }

                });

            } else{
                    Toast.makeText(FeedbackStudent.this, "Invalid rating", Toast.LENGTH_SHORT).show();
                }
            }


        });


    }

    private float calculateNewOverallRating(float currentRating, float userRating, int size) {
        // Your logic to calculate the new overall rating (e.g., taking the average)
        float sumOfRatings = currentRating * size + userRating;
        return sumOfRatings / (size + 1);
    }


    }
