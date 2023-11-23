package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ScheduleOrViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_or_schedule);
    }
    public void OnAdminViewEventsButtonClick(View view) {
        Intent intent = new Intent(this, EventViewActivity.class);
        startActivity(intent);
    }

    public void OnAdminScheduleButtonClick(View view){
        Intent intent = new Intent(this, ScheduleEvents.class);
        startActivity(intent);
    }


}
