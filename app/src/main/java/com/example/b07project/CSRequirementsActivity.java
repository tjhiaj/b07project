package com.example.b07project;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;

public class CSRequirementsActivity extends AppCompatActivity {
    CheckBox checkbox1;
    CheckBox checkbox2;
    CheckBox checkbox3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_science_requirements);

        checkbox1 = findViewById(R.id.CS_minimum_gpa_box);
        checkbox2 = findViewById(R.id.CS_minimum_grade_box);
        checkbox3 = findViewById(R.id.CS_minimum_2_grades_box);
    }

    public void onCSReqSubmitClick(View view){
        if (checkbox1.isChecked() && checkbox2.isChecked() && checkbox3.isChecked()) {
            String message1 = "You qualify for UTSC's Computer Science POSt requirements and can" +
                    "enroll in either a specialist or major CS program. Good job! Keep it up.";
            Snackbar snackbar = Snackbar.make(view, message1, Snackbar.LENGTH_INDEFINITE);

            // Add an action (e.g., close button)
            snackbar.setAction("Close", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss(); // Dismiss the Snackbar when the action is clicked
                }
            });

            snackbar.show();
//            int duration = Toast.LENGTH_SHORT; // Duration for how long the toast message will appear
//
//            Toast toast = Toast.makeText(getApplicationContext(), message, duration);
//            toast.show();
        } else {
            String message2 = "Sorry, you do not qualify for UTSC's Computer Science POSt " +
                    "requirements and you may not enroll in either a specialist or major CS " +
                    "program. Good luck in your future endeavors.";
            Snackbar snackbar = Snackbar.make(view, message2, Snackbar.LENGTH_INDEFINITE);

            // Add an action (e.g., close button)
            snackbar.setAction("Close", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss(); // Dismiss the Snackbar when the action is clicked
                }
            });

            snackbar.show();
//            String message2 = "Sorry, you do not qualify for UTSC's Computer Science POSt " +
//                    "requirements and you may not enroll in either a specialist or major CS program." +
//                    "Good luck in your future endeavors.";
//            int duration = Toast.LENGTH_SHORT; // Duration for how long the toast message will appear
//
//            Toast toast = Toast.makeText(getApplicationContext(), message2, duration);
//            toast.show();
        }
    }
}
