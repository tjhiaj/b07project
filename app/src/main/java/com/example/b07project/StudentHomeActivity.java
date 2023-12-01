package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

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
        Intent intent = new Intent(this, MyEventsOrAllEventsActivity.class);
        startActivity(intent);
    } 

    public void programButtonClick(View view){
        Intent intent = new Intent(this, POStRequirementsActivity.class);
        startActivity(intent);
    }
    public void onStudentComplaintsButtonClick(View view) {
        Intent intent = new Intent(this, SubmitComplaints.class);
        startActivity(intent);
    }

    public void OnStudentLogoutButtonClick(View view) {
        Intent intent = new Intent(this, LoginView.class);
        startActivity(intent);
    }
}
