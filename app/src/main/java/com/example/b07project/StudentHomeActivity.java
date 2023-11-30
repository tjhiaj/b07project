package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
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


public class StudentHomeActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    RecyclerView recyclerView;

    AnnouncementAdapter adapter;

    List<Announcement> announcementList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("announcements");

        // Set up RecyclerView
        recyclerView = findViewById(R.id.studentNotifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AnnouncementAdapter(this, announcementList);
        recyclerView.setAdapter(adapter);

        // Set an item click listener for the adapter
        adapter.setOnItemClickListener(position -> {
            // Handle click event for the clicked item
            Announcement clickedAnnouncement = announcementList.get(position);
//
//            // Start a new activity or navigate to the announcement details page
//            Intent intent = new Intent(StudentHomeActivity.this, StudentAnnouncementsActivity.class);
//            intent.putExtra("announcement_id", clickedAnnouncement.getId()); // Pass any necessary data to the details page
//            startActivity(intent);
        });

        getdata();
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
                Toast.makeText(StudentHomeActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });


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
}
