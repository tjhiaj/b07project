package com.example.b07project;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentAnnouncementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements_student);

        // Dummy data for testing
        List<Announcement> announcementList = new ArrayList<>();
        announcementList.add(new Announcement("Announcement 1", "This is the message for announcement 1."));
        announcementList.add(new Announcement("Announcement 2", "This is the message for announcement 2."));
        announcementList.add(new Announcement("Announcement 3", "This is the message for announcement 3."));

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        AnnouncementAdapter adapter = new AnnouncementAdapter(this, announcementList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void OnStudentAnnouncementsBackButtonClick(View view) {
        Intent intent = new Intent(this, StudentHomeActivity.class);
        startActivity(intent);
    }

}

