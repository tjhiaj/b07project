package com.example.b07project;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;

public class CSRequirementsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_science_requirements);
    }
    CheckBox checkbox1 = findViewById(R.id.CS_minimum_gpa_box);
    CheckBox checkbox2 = findViewById(R.id.CS_minimum_grade_box);
    CheckBox checkbox3 = findViewById(R.id.CS_minimum_2_grades_box);

    protected void onCSReqSubmitClick(View view){
        if (checkbox1.isChecked() && checkbox2.isChecked() && checkbox3.isChecked()) {


//            String message = "You qualify for UTSC's Computer Science POSt requirements and can" +
//                    "enroll in either a specialist or major CS program. Good job! Keep it up.";
//            int duration = Toast.LENGTH_SHORT; // Duration for how long the toast message will appear
//
//            Toast toast = Toast.makeText(getApplicationContext(), message, duration);
//            toast.show();
        } else {
            String message = "Sorry, you do not qualify for UTSC's Computer Science POSt " +
                    "requirements and you may not enroll in either a specialist or major CS program." +
                    "Good luck in your future endeavors.";
            int duration = Toast.LENGTH_SHORT; // Duration for how long the toast message will appear

            Toast toast = Toast.makeText(getApplicationContext(), message, duration);
            toast.show();
        }
    }
}
