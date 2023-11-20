package com.example.b07project;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.LinkedList;

public class ViewComplaints extends AppCompatActivity {
    FirebaseDatabase database;
    TextView subjects;
    TextView details;
    LinearLayout complaintsLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        subjects = findViewById(R.id.viewSubjects);
        details = findViewById(R.id.details);
        complaintsLayout = findViewById(R.id.viewLayout);
        LayoutTransition layoutTransition = new LayoutTransition();

        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
        complaintsLayout.setLayoutTransition(layoutTransition);
        FirebaseDatabase.getInstance().setPersistenceEnabled(false);
        database = FirebaseDatabase.getInstance("https://b07project-7eb3d-default-rtdb.firebaseio.com/");
        if (database == null) return;
    }
    public void expand(View view){
        int visible = (details.getVisibility()==View.GONE)?View.VISIBLE:View.GONE;
        TransitionManager.beginDelayedTransition(complaintsLayout,new AutoTransition());
        details.setVisibility(visible);
    }

}
