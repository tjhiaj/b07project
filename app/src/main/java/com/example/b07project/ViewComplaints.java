package com.example.b07project;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
import java.util.Map;


public class ViewComplaints extends AppCompatActivity{
    FirebaseDatabase database;
    List<Complaints> complaints;
    ComplaintsAdapter complaintAdapter;
    ArrayList<Map<String,String>> viewModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        complaints = new ArrayList<>();

//        // Dummy data for testing
//
        complaints.add(new Complaints("Announcement 1", "Apple"));
//        complaints.add(new Complaints("Announcement 2", new ArrayList<>(Arrays.asList("Apple", "Banana", "pear"))));
//        complaints.add(new Complaints("Announcement 3", new ArrayList<>(Arrays.asList("Apple", "Banana", "pear"))));

        // Set up RecyclerView
        RecyclerView activityView = findViewById(R.id.complaintsRecyclerView);
        complaintAdapter = new ComplaintsAdapter(this, complaints);
        activityView.setLayoutManager(new LinearLayoutManager(this));
        activityView.setAdapter(complaintAdapter);


        //setupViewModel();

        database = FirebaseDatabase.getInstance("https://b07project-7eb3d-default-rtdb.firebaseio.com/");
        viewModels = new ArrayList<>();
        setupViewModel();

    }

    public void onStudentComplaintsButtonClickBackClick(View view) {
        Intent intent = new Intent(this, AdminHomeActivity.class);
        startActivity(intent);
    }

    private void setupViewModel() {
        DatabaseReference ref = database.getReference("complaints");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Log.d(TAG, viewModels.toString());
                    Object dataValue = dataSnapshot.getValue();
                    if(dataValue instanceof ArrayList){
                        ArrayList<Map<String,String>> rawList = (ArrayList<Map<String,String>>) dataValue;
                        if (!rawList.isEmpty()) {

                            viewModels = rawList;

                            Log.d(TAG, "Data: " + viewModels);

                            if (!isDestroyed()) {
                                RecyclerView recyclerView = findViewById(R.id.complaintsRecyclerView);
                                Log.d(TAG, "new adapter");
                                ViewReclyerAdapter adapter = new ViewReclyerAdapter(ViewComplaints.this, viewModels);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(ViewComplaints.this));
                                adapter.notifyDataSetChanged();
                                Log.d(TAG, "adapter set up ");
                            }
                        } else {
                            Log.e(TAG, "Invalid data format: Elements are not maps with string keys and string values");
                        }
                    } else {
                        Log.e(TAG, "Invalid data format: Not an ArrayList");
                    }}
                else {
                    Log.d(TAG, "Data does not exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                String errorMessage = databaseError.getMessage();
                int errorCode = databaseError.getCode();
                Log.e("Firebase Database Error", "Error Code: " + errorCode + ", Message: " + errorMessage);
            }
        });
    }
}