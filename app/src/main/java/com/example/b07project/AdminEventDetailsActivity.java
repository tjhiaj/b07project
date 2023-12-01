package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AdminEventDetailsActivity extends AppCompatActivity {
    Event event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_event_item);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("EVENT")) {
            event = intent.getParcelableExtra("EVENT");
        }
        ImageView eventImageView = findViewById(R.id.eventDetailsImageView);
        TextView titleTextView = findViewById(R.id.eventDetailsTitleTextView);
        TextView descriptionTextView = findViewById(R.id.eventDetailsDescriptionTextView);
        RatingBar ratingBar = findViewById(R.id.eventDetailsRatingBar);
        TextView ratingCountsTextView = findViewById(R.id.ratingCountsTextView);
        LinearLayout commentsLinearLayout = findViewById(R.id.eventDetailsCommentsLinearLayout);

        eventImageView.setImageResource(event.getImageResourceId());
        titleTextView.setText(event.getEventName());
        descriptionTextView.setText(event.getEventDescription());
        ratingBar.setRating(event.getRating());

        //Cheryl changed this part
        List<Integer> ratings = event.getRatings();
        String ratingCount = "| ";
        for (int i = 0; i < 5; i++){
            ratingCount = ratingCount + (i+1) + " star: " + ratings.get(i) + " | ";
        }
        ratingCountsTextView.setText(ratingCount);

        List<String> comments = event.getComments();
        for (String comment : comments) {
            TextView commentTextView = new TextView(this);
            commentTextView.setText(comment);
            commentsLinearLayout.addView(commentTextView);
        }

    }

    public void OnAdminEventsBackButtonClick(View view){
        Intent intent = new Intent(this, EventViewActivity.class);
        startActivity(intent);
    }

}
