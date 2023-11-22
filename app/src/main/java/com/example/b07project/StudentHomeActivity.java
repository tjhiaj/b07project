package com.example.b07project;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class StudentHomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
    }
    public void onStudentAnnouncementsButtonClick(View view) {
        Intent intent = new Intent(this, StudentAnnouncementsActivity.class);
        startActivity(intent);
    }

    public void onStudentEventsButtonClick(View view) {
        Intent intent = new Intent(this, EventViewActivity.class);
        startActivity(intent);
    }
}
