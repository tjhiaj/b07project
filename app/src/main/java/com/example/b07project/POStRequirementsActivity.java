package com.example.b07project;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class POStRequirementsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_requirements);
    }

    public void onCSRequirementsClick(View view) {
        Intent intent = new Intent(this, CSRequirementsActivity.class);
        startActivity(intent);
    }

    public void onPOStGoBackClick(View view){
        Intent intent = new Intent(this, StudentHomeActivity.class);
        startActivity(intent);
    }

    public void onCSMinorReqClick(View view){
        Intent intent = new Intent(this, CSMinorRequirementsActivity.class);
        startActivity(intent);
    }
}
