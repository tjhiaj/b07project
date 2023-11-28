package com.example.b07project;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.Month;
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
        List<Integer> L2 = new ArrayList<>();
        L2.add(2);
        L2.add(0);
        L2.add(3);
        L2.add(4);
        L2.add(1);
        List<String> L3 = new ArrayList<>();
        L3.add("Bob");
        LocalDateTime specificDate = LocalDateTime.of(2014, 1, 1, 10, 10, 30);
        eventList.add(new Event("Event 1", "This is the description for event 1.", R.drawable.default_event, 3, L1, L2, "NjtusLW-ySqra_V71MP", 100, L3, specificDate));
        eventList.add(new Event("Event 2", "This is the description for event 2.", R.drawable.default_event, 2, L1, L2, "id2", 110, L3, specificDate));
        eventList.add(new Event("Event 3", "This is the description for event 3.", R.drawable.default_event, 4, L1, L2, "id3", 100, L3, specificDate));

        Log.i("doubt it", "sad");
        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new EventAdapter(this, eventList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
