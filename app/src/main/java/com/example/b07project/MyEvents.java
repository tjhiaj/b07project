package com.example.b07project;

import static android.content.ContentValues.TAG;

import static com.example.b07project.UserInfo.RoleType.Admin;
import static com.example.b07project.UserInfo.RoleType.Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MyEvents extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    private List<Event> myEventsList;

    public void OnViewEventsBackButtonClick(View view){
        Intent intent = new Intent(this, MyEventsOrAllEventsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("pretty", "MyEvents");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);

        recyclerView = findViewById(R.id.recyclerView);
        myEventsList = new ArrayList<>();
        eventAdapter = new EventAdapter(MyEvents.this, myEventsList, MyEvents.class);
        recyclerView.setAdapter(eventAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Dummy data for testing
        List<String> L1 = new ArrayList<>();
        L1.add("ab");
        L1.add("cd");
        List<Integer> L2 = new ArrayList<>();
        L2.add(2);
        L2.add(0);
        L2.add(3);
        L2.add(4);
        L2.add(1);
        List<String> L3 = new ArrayList<>();
        L3.add("Bob");

        myEventsList.add(new Event("Event 1", "This is the description for event 1.", R.drawable.default_event, 3, L1, L2, "NjtusLW-ySqra_V71MP", 100, L3, "specificDate"));
        //loadMyEvents();
    }

    private void loadMyEvents() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String currentUserId = currentUser.getUid();

            DatabaseReference eventsRef = FirebaseDatabase.getInstance().getReference("events");

            // Query events where the user has RSVP'd
            Query query = eventsRef.orderByChild("participants").equalTo(currentUserId);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    myEventsList.clear();

                    for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                        Event event = eventSnapshot.getValue(Event.class);
                        if (event != null) {
                            myEventsList.add(event);
                        }
                    }

                    eventAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e(TAG, "Error reading data: " + databaseError.getMessage(), databaseError.toException());
                }
            });
        }
    }



}