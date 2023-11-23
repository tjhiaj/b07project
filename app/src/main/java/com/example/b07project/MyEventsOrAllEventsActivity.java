package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MyEventsOrAllEventsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events_or_all_events);
    }
    public void OnAllEventsButtonClick(View view) {
        Intent intent = new Intent(this, EventViewActivity.class);
        startActivity(intent);
    }
}
