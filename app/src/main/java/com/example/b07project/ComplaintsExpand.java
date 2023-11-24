package com.example.b07project;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ComplaintsExpand extends AppCompatActivity {
    ImageView expand;
    LinearLayout hiddenView;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_item);

        cardView = findViewById(R.id.card3);
        //this.expand = findViewById(R.id.expand);
        hiddenView = findViewById(R.id.hidden_view);

        expand.setOnClickListener(view -> {
            if (hiddenView.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hiddenView.setVisibility(View.GONE);
                expand.setImageResource(R.drawable.expand_arrow);
            } else {
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hiddenView.setVisibility(View.VISIBLE);
                expand.setImageResource(R.drawable.expand_arrow);
            }
        });
    }
}
