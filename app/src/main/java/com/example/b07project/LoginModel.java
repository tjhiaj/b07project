package com.example.b07project;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginModel {

    private FirebaseAuth mAuth;

    public LoginModel() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void signInWithEmailAndPassword(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        mAuth.signInWithEmailAndPassword(email, password).
                addOnCompleteListener(onCompleteListener);
    }




}
