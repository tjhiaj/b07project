package com.example.b07project;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.google.android.material.snackbar.Snackbar;

public class CSMinorRequirementsActivity extends AppCompatActivity {
    CheckBox checkbox1;
    CheckBox checkbox2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_science_minor_requirements);
        checkbox1 = findViewById(R.id.CS_minor_4_credits_box);
        checkbox2 = findViewById(R.id.CS_minor_req_courses_box);
    }

    public void onCSMinorSubmitClick(View view){
        if (checkbox1.isChecked() && checkbox2.isChecked()){
            String message1 = "You qualify for UTSC's Computer Science Minor POSt! Good job.";
            Snackbar snackbar = Snackbar.make(view, message1, Snackbar.LENGTH_INDEFINITE);

            snackbar.setAction("Close", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            });

            snackbar.show();
        } else {
            String message2 = "Sorry, you do not qualify for UTSC's Computer Science Minor POSt.";
            Snackbar snackbar = Snackbar.make(view, message2, Snackbar.LENGTH_INDEFINITE);

            snackbar.setAction("Close", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            });

            snackbar.show();
        }
    }

    public void onCSMinorGoBackClick(View view) {
        Intent intent = new Intent(this, POStRequirementsActivity.class);
        startActivity(intent);
    }
}
