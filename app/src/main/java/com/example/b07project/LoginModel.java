package com.example.b07project;

import android.app.Activity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginModel extends Activity {
    private FirebaseAuth mAuth;

    public LoginModel() {
        mAuth = FirebaseAuth.getInstance();
    }

    public Task<AuthResult> signInWithEmailAndPassword(String email, String password)
             {
             return   mAuth.signInWithEmailAndPassword(email, password);

    }
}
