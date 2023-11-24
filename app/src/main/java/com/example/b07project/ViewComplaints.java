package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class ViewComplaints extends AppCompatActivity {
    FirebaseDatabase database;
    List<Complaints> complaints;
    ComplaintsAdapter complaintAdapter;
    LinearLayout viewLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
//        detailedText = findViewById(R.id.complaints1);
//        viewLayout = findViewById(R.id.viewComplaintLayout);
        //LayoutTransition layoutTransition = new LayoutTransition();
        //layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
        //database = FirebaseDatabase.getInstance("https://b07project-7eb3d-default-rtdb.firebaseio.com/");
        complaints = new ArrayList<>();
        //setupViewModel();
        //if (database == null) return;

//        // Dummy data for testing
//
        complaints.add(new Complaints("Announcement 1", "Apple"));
//        complaints.add(new Complaints("Announcement 2", new ArrayList<>(Arrays.asList("Apple", "Banana", "pear"))));
//        complaints.add(new Complaints("Announcement 3", new ArrayList<>(Arrays.asList("Apple", "Banana", "pear"))));

        // Set up RecyclerView
        RecyclerView activityView = findViewById(R.id.complaintsRecyclerView);
        complaintAdapter = new ComplaintsAdapter(this, complaints);
        activityView.setAdapter(complaintAdapter);
        activityView.setLayoutManager(new LinearLayoutManager(this));

        //setupViewModel();

    }

    public void onStudentComplaintsButtonClickBackClick(View view) {
        Intent intent = new Intent(this, AdminHomeActivity.class);
        startActivity(intent);
    }

//    public void onExpandClick(View view) {
//        Intent intent = new Intent(this, ComplaintsExpand.class);
//        startActivity(intent);
//    }

//    private void setupViewModel() {
//        DatabaseReference ref = database.getReference("complaints");
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    Log.d(TAG, complaints.toString());
//                    Map<String, ArrayList<String>> dataMap = (Map<String, ArrayList<String>>) dataSnapshot.getValue();
//                    for (String subject : dataMap.keySet()) {
//                        complaints.add(new Complaints(subject, dataMap.get(subject)));
//                        Log.d(TAG, "Data: " + complaints.toString());
//                    }
//                    if (!isDestroyed()) {
//                        RecyclerView recyclerView = findViewById(R.id.complaintsRecyclerView);
//                        Log.d(TAG, "set new adapter");
//                        ComplaintsAdapter adapter = new ComplaintsAdapter(ViewComplaints.this, complaints);
//                        recyclerView.setAdapter(adapter);
//                        recyclerView.setLayoutManager(new LinearLayoutManager(ViewComplaints.this));
//                        adapter.notifyDataSetChanged();
//                        Log.d(TAG, "setted adapter");
//                    }
//
//
//                } else {
//                    Log.d(TAG, "Data does not exist");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle cancellation
//            }
//        });
//    }

//
//    public class ComplaintsExpand extends AppCompatActivity {
//        ImageView expand;
//        LinearLayout hiddenView;
//        CardView cardView;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.card_item);
//
//            cardView = findViewById(R.id.card3);
//            this.expand = findViewById(R.id.expand);
//            hiddenView = findViewById(R.id.hidden_view);
//
//            expand.setOnClickListener(view -> {
//                if (hiddenView.getVisibility() == View.VISIBLE) {
//                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
//                    hiddenView.setVisibility(View.GONE);
//                    expand.setImageResource(R.drawable.expand_arrow);
//                } else {
//                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
//                    hiddenView.setVisibility(View.VISIBLE);
//                    expand.setImageResource(R.drawable.expand_arrow);
//                }
//            });
//        }
//    }
//

}


