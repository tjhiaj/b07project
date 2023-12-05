package com.example.b07project;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

public class AdminAnnouncementsActivity extends AppCompatActivity {
    FirebaseDatabase database;
    Button post;


    LinkedList<String> announcementsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements_admin);


        database = FirebaseDatabase.getInstance("https://b07project-7eb3d-default-rtdb.firebaseio.com/");

        post = findViewById(R.id.postButton);
        announcementsList = new LinkedList<>();

        if(database == null) return;

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DO I need to add announcements thing in firebase
                DatabaseReference ref = database.getReference("announcements");
                EditText complaintContent = findViewById(R.id.messageEditText);
                EditText subjectContent = findViewById(R.id.subjectEditText);
                String announcements = complaintContent.getText().toString();
                String subjects = subjectContent.getText().toString();

                if(ref == null) return;
                if (!subjects.isEmpty()) {
                    DatabaseReference subjectRef = ref.child(subjects);
                    announcementsList.addFirst(announcements);
                    subjectRef.setValue(announcements).addOnCompleteListener(new OnCompleteListener<Void>() {

                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                NotificationHelper.showNotification(AdminAnnouncementsActivity.this, "New Announcement!", subjects + " - " + announcements, NotificationType.generateRandomAnnouncementId());
                                // Update UI or show a toast indicating successful update
                                complaintContent.setText("");
                                subjectContent.setText("");
                                Toast.makeText(AdminAnnouncementsActivity.this, "Announcement submitted", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), AdminHomeActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Exception exception = task.getException();
                                Log.e(TAG, "Failed to submit announcement: " + exception.getMessage(), exception);
                            }
                        }
                    });
                    ref.child(subjects).addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            // Log the actual data
                            if (dataSnapshot.exists()) {
                                Log.d(TAG, "Data: " + dataSnapshot.getValue());
                            } else {
                                Log.d(TAG, "Data does not exist");
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e(TAG, "Error reading data: " + databaseError.getMessage(), databaseError.toException());
                        }
                    });



                } else {
                    Toast.makeText(AdminAnnouncementsActivity.this, "Subject cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


        }

        public void OnAdminAnnouncementsBackButtonClick(View view) {
                Intent intent = new Intent(this, AdminHomeActivity.class);
                startActivity(intent);
            }
    }
