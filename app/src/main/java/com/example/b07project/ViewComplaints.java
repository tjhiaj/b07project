package com.example.b07project;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class ViewComplaints extends AppCompatActivity {
    FirebaseDatabase database;
    TextView subjects;
    TextView details;
    LinearLayout complaintsLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        FirebaseDatabase.getInstance().setPersistenceEnabled(false);
        subjects = findViewById(R.id.subject1);
        details = findViewById(R.id.details3);
        complaintsLayout = findViewById(R.id.viewLayout);

        LayoutTransition layoutTransition = new LayoutTransition();

        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
        complaintsLayout.setLayoutTransition(layoutTransition);
        database = FirebaseDatabase.getInstance("https://b07project-7eb3d-default-rtdb.firebaseio.com/");
        if (database == null) return;
    }
    public void expand(View view){
        ScrollView detail = findViewById(R.id.scroll3);
        int visible = detail.getVisibility();
        if (visible == View.GONE) visible = View.VISIBLE;
        else visible = View.GONE;

        TransitionManager.beginDelayedTransition(complaintsLayout,new AutoTransition());
        detail.setVisibility(visible);
    }

}
