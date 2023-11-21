package com.example.b07project;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentAnnouncementsActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    AnnouncementAdapter adapter;

    List<Announcement> announcementList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements_student);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("announcements");




        // Dummy data for testing

//        announcementList.add(new Announcement("Announcement 1", "This is the message for announcement 1."));
//        announcementList.add(new Announcement("Announcement 2", "This is the message for announcement 2."));
//        announcementList.add(new Announcement("Announcement 3", "This is the message for announcement 3."));

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new AnnouncementAdapter(this, announcementList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getdata();

    }

    public void OnStudentAnnouncementsBackButtonClick(View view) {
        Intent intent = new Intent(this, StudentHomeActivity.class);
        startActivity(intent);
    }

    private void getdata() {

        // calling add value event listener method
        // for getting the values from database.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is

                announcementList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    // Loop through the children of the "announcements" node
                    String subject = dataSnapshot.getKey();
                    String announcement = dataSnapshot.getValue(String.class);

                    // Create an Announcement object and add it to the list
                    Announcement newAnnouncement = new Announcement(subject, announcement);
                    announcementList.add(newAnnouncement);
                }

                // Notify the adapter that the data set has changed
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(StudentAnnouncementsActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
      
      
    }}

