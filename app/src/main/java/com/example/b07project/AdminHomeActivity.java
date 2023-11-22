package com.example.b07project;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class AdminHomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }
    public void onAdminAnnouncementsButtonClick(View view) {
        Intent intent = new Intent(this, AdminAnnouncementsActivity.class);
        startActivity(intent);
    }

    public void onAdminEventsButtonClick(View view) {
        Intent intent = new Intent(this, ScheduleOrViewActivity.class);
        startActivity(intent);
    }

//    public void onAdminComplaintsButtonClick(View view) {
//        Intent intent = new Intent(this, ViewComplaints.class);
//        startActivity(intent);
//    }
}
