package com.example.b07project;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginModel extends Activity {
    private FirebaseAuth mAuth;

    public LoginModel() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void signInWithEmailAndPassword(LoginPresenter presenter, String email, String password) {
        Task<AuthResult> task = mAuth.signInWithEmailAndPassword(email, password);

        task.addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            presenter.onSuccessful();
                        } else {
                            presenter.onFail();
                        }
                    }
                }

        );

    }

}
