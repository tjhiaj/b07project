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
        finish();
    }

    public void OnMyEventsButtonClick(View view){
        Intent intent = new Intent(this, MyEvents.class);
        startActivity(intent);
        finish();
    }

    public void OnStudentEventNavBackButtonClick(View view){
        Intent intent = new Intent(this, StudentHomeActivity.class);
        startActivity(intent);
    }
}
