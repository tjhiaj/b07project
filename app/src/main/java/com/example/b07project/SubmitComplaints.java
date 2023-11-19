package com.example.b07project;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;



public class SubmitComplaints extends AppCompatActivity {
    FirebaseDatabase database;
    TextView textView ;
    Button submit;


    LinkedList<String> complaintList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        FirebaseDatabase.getInstance().setPersistenceEnabled(false);
        database = FirebaseDatabase.getInstance("https://b07project-7eb3d-default-rtdb.firebaseio.com/");

        textView = findViewById(R.id.textView);
        submit = findViewById(R.id.submitButton);
        complaintList = new LinkedList<>();
        if(database == null) return;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = database.getReference("complaints");
                EditText complaintContent = findViewById(R.id.editComplaints);
                EditText subjectContent = findViewById(R.id.subjects);
                String complaints = complaintContent.getText().toString();
                String subjects = subjectContent.getText().toString();

                if(ref == null) return;
                if (!subjects.isEmpty()) {
                    DatabaseReference subjectRef = ref.child(subjects);
                    complaintList.addFirst(complaints);
                    subjectRef.setValue(complaints).addOnCompleteListener(new OnCompleteListener<Void>() {

                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                // Update UI or show a toast indicating successful update
                                complaintContent.setText("");
                                subjectContent.setText("");
                                textView.setText("Submitted");
                                Toast.makeText(SubmitComplaints.this, "Complaint submitted", Toast.LENGTH_SHORT).show();
                            } else {
                                Exception exception = task.getException();
                                Log.e(TAG, "Failed to submit complaint: " + exception.getMessage(), exception);
                            }
                        }
                    });
                    ref.child(subjects).addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            // Log the actual data
                            if (dataSnapshot.exists()) {
                                //Log.d(TAG, "Data: " + dataSnapshot.getValue());
                            } else {
                                //Log.d(TAG, "Data does not exist");
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e(TAG, "Error reading data: " + databaseError.getMessage(), databaseError.toException());
                        }
                    });



                } else {
                    Toast.makeText(SubmitComplaints.this, "Subject cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


}

