package com.example.b07project;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class SubmitComplaints extends AppCompatActivity {
    FirebaseDatabase database;
    Button submit;
    Map<String,Object>  complaintList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseDatabase.getInstance().setPersistenceEnabled(false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        database = FirebaseDatabase.getInstance("https://b07project-7eb3d-default-rtdb.firebaseio.com/");
        submit = findViewById(R.id.submitButton);
        complaintList = new HashMap<>();
        if (database == null) return;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = database.getReference("complaints");
                EditText complaintContent = findViewById(R.id.editComplaints);
                EditText subjectContent = findViewById(R.id.subjects);
                String complaints = complaintContent.getText().toString();
                String subjects = subjectContent.getText().toString();
                if (!subjects.isEmpty()) {
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (complaintList.containsKey(subjects)) {
                                LinkedList<String> current = (LinkedList<String>) (complaintList.get(subjects));
                                current.addFirst(complaints);
                                Log.d(TAG, "Data does exist");
                            } else {
                                if (dataSnapshot.hasChild(subjects)) {
                                    Map<String, LinkedList<String>> original = (Map<String, LinkedList<String>>) dataSnapshot.getValue();
                                    LinkedList<String> curList = new LinkedList<String>(original.get(subjects));
                                    curList.addFirst(complaints);
                                    ref.child(subjects).setValue(curList);
                                    Log.d(TAG, "Data does exist" + curList);

                                } else {
                                    complaintList.put(subjects, new LinkedList<String>(Arrays.asList(complaints)));
                                    Log.d(TAG, "Data does not exist");
                                }

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e(TAG, "Error reading data: " + databaseError.getMessage(), databaseError.toException());
                        }
                    });
                    ref.updateChildren(complaintList).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                complaintContent.setText("");
                                subjectContent.setText("");
                                Toast.makeText(SubmitComplaints.this, "Submitted", Toast.LENGTH_SHORT).show();
                            } else {
                                Exception exception = task.getException();
                                Log.e(TAG, "Failed to submit complaint: " + exception.getMessage(), exception);
                            }
                        }
                    });
                } else {
                    Toast.makeText(SubmitComplaints.this, "Subject cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
//            @Override
//            public void handleOnBackPressed() {
//                // Your custom back press logic here
//                // If you want to continue with the default back press behavior, call super.handleOnBackPressed();
//            }
//        };
//        void getOnBackPressedDispatcher().addCallback(this,callback);
//
//    }
    }

    public void onStudentComplaintsButtonClickBackButtonClick(View view) {
        Intent intent = new Intent(this, StudentHomeActivity.class);
        startActivity(intent);
    }
}

