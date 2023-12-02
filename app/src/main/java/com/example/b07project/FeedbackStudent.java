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

import java.util.ArrayList;
import java.util.List;

public class FeedbackStudent extends AppCompatActivity {
    private static final String TAG = "FeedbackStudent";
    private FirebaseDatabase database;
    private Button submitFeedbackButton;
    private EditText commentEditText;
    private RatingBar ratingBar;

    private String eventID;

    private double rating;

    private List<Integer> ratings;
    private List<String> comments;

    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_student);
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
        Log.i("Cheryl", eventID);

        DatabaseReference feedbackRef = database.getReference("events").child(eventID);
        DatabaseReference commentsRef = feedbackRef.child("comments");
        DatabaseReference ratingRef = feedbackRef.child("rating");
        DatabaseReference ratingsRef = feedbackRef.child("ratings");


//
//
//        rating = event.getRating();
//
//        ratings = event.getRatings();
//
//        comments = event.getComments();



        ratingRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Check if dataSnapshot exists and has a value
                if (dataSnapshot.exists() && dataSnapshot.getValue() != null) {
                    // Check if the value is a number
                    Object value = dataSnapshot.getValue();
                    if (value instanceof Number) {
                        Number ratingValue = (Number) value;
                        // Assign ratingValue to the rating variable
                        rating = ratingValue.doubleValue();
                    } else {
                        // Handle the case where the value is not a Number
                        // You can log a warning or handle it based on your requirements
                        Log.w(TAG, "Unexpected data type for rating: " + value.getClass().getSimpleName());
                        rating = 0.0; // Default to 0.0
                    }
                } else {
                    // Handle the case where the data is missing or not in the expected format
                    rating = 0.0; // Default to 0.0
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle cancellation
            }
        });

        ratingsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Assuming ratings is a list of integers
                List<Integer> ratingsList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Long rating = snapshot.getValue(Long.class);
                    if (rating != null) {
                        ratingsList.add(Math.toIntExact(rating));
                    }
                }
                // Assign ratingsList to the ratings variable
                ratings = ratingsList;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

        commentsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Assuming comments is a list of strings
                List<String> commentsList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String comment = snapshot.getValue(String.class);
                    if (comment != null) {
                        commentsList.add(comment);
                    }
                }
                // Assign commentsList to the comments variable
                comments = commentsList;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });




        submitFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Integer> ratingsList = ratings;
                List<String> commentsList = comments;
                Log.i("Cheryl", ratingsList.get(0).toString());
                Log.i("Cheryl", commentsList.get(0).toString());



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

                    commentsList.add(comment);
                    commentsRef.setValue(commentsList);

                    ratingsRef.setValue(ratingsList);

                //finds the new average rating now that the ratings list is updated to include the user's rating
                double newOverallRating = calculateNewOverallRating(rating, intRating, ratingsList.size());


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


    private double calculateNewOverallRating(double currentRating, float userRating, int size) {
        // Your logic to calculate the new overall rating (e.g., taking the average)
        double sumOfRatings = currentRating * size + userRating;
        return sumOfRatings / (size + 1);
    }


}
