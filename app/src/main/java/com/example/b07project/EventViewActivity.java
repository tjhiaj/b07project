package com.example.b07project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventViewActivity extends AppCompatActivity {
    EventAdapter adapter;
    List<Event> eventList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);

        // Dummy data for testing
        List<String> L1 = new ArrayList<>();
        L1.add("ab");
        L1.add("cd");
        int [] L2 = new int[]{2,0,0,3, 4};
        eventList.add(new Event("Event 1", "This is the description for event 1.", R.drawable.default_event, 3, L1, L2));
        eventList.add(new Event("Event 2", "This is the description for event 2.", R.drawable.default_event, 2, L1, L2));
        eventList.add(new Event("Event 3", "This is the description for event 3.", R.drawable.default_event, 4, L1, L2));

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new EventAdapter(this, eventList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
