package com.example.b07project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class StudentHomeActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    RecyclerView recyclerView;

    AnnouncementAdapterDismissable adapter;

    List<DismissableAnnouncements> announcementList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("announcements");

        // Set up RecyclerView
        recyclerView = findViewById(R.id.studentNotifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AnnouncementAdapterDismissable(this, announcementList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AnnouncementAdapterDismissable.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                DismissableAnnouncements clickedAnnouncement = announcementList.get(position);
                Intent intent = new Intent(StudentHomeActivity.this, StudentAnnouncementsActivity.class);
                startActivity(intent);
            }

            @Override
            public void onDismissalClick(int position) {
                DismissableAnnouncements dismissedAnnouncement = announcementList.get(position);

                // Dismiss the announcement (store its ID as dismissed in SharedPreferences)
                dismissNotification(dismissedAnnouncement.getDismissableId());
                announcementList.remove(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyDataSetChanged();

                // don't remove from database tho
            }
        });


        getdata();
    }


    private void getdata() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                announcementList.clear();

                // Loop through the fetched announcements
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String subject = dataSnapshot.getKey();
                    String announcement = dataSnapshot.getValue(String.class);
                    String announcementId = AnnouncementIDGenerator.generateUniqueId();

                    // Check if the announcement is not dismissed before adding it
                    if (!isDismissed(announcementId)) {
                        DismissableAnnouncements newAnnouncement = new DismissableAnnouncements(subject, announcement, announcementId);
                        announcementList.add(newAnnouncement);
                    }
                }

                // Notify the adapter that the data set has changed
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled event
                Toast.makeText(StudentHomeActivity.this, "Failed to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void dismissNotification(String notificationId) {
        SharedPreferences prefs = getSharedPreferences("DismissedNotifications", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("notification_" + notificationId + "_dismissed", true);
        editor.apply();
    }

    private boolean isDismissed(String notificationId) {
        SharedPreferences prefs = getSharedPreferences("DismissedNotifications", Context.MODE_PRIVATE);
        return prefs.getBoolean("notification_" + notificationId + "_dismissed", false);
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
