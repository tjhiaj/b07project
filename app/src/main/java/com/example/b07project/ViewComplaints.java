package com.example.b07project;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import java.util.Map;


public class ViewComplaints extends AppCompatActivity{
    FirebaseDatabase database;
    ArrayList<Complaints> complaints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        //LayoutTransition layoutTransition = new LayoutTransition();
        //layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
        database = FirebaseDatabase.getInstance("https://b07project-7eb3d-default-rtdb.firebaseio.com/");
        complaints = new ArrayList<com.example.b07project.Complaints>();
        setupViewModel();
        if (database == null) return;

    }
    private void setupViewModel() {
        DatabaseReference ref = database.getReference("complaints");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Log.d(TAG, complaints.toString());
                    Map<String, ArrayList<String>> dataMap = (Map<String,ArrayList<String>>)dataSnapshot.getValue();
                    for(String subject: dataMap.keySet()) {
                        complaints.add(new Complaints(subject, dataMap.get(subject)));
                        Log.d(TAG, "Data: " + complaints.toString());
                    }
                    if(!isDestroyed()){
                        RecyclerView recyclerView = findViewById(R.id.complaintsRecyclerView);
                        Log.d(TAG,"set new adapter");
                        ComplaintsAdapter adapter = new ComplaintsAdapter(ViewComplaints.this, complaints);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ViewComplaints.this));
                        adapter.notifyDataSetChanged();
                        Log.d(TAG,"setted adapter");
                    }


                } else {
                    Log.d(TAG, "Data does not exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle cancellation
            }
        });
    }


    public void expand(View view){
        TextView complaintDisplay = findViewById(R.id.text1);
        LinearLayout verticalLayout = findViewById(R.id.verticalLayout);
        int visible = complaintDisplay.getVisibility();
        if (visible == View.GONE) visible = View.VISIBLE;
        else visible = View.GONE;
        complaintDisplay.setVisibility(visible);
    }
}


