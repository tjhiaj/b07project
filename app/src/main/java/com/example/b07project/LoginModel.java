package com.example.b07project;

import android.app.Activity;


import androidx.annotation.NonNull;



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
