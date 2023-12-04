package com.example.b07project;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginPresenter implements OnCompleteListener<AuthResult> {
    private final LoginModel loginModel;
    private final LoginView loginView;

    public LoginPresenter(LoginView loginView,LoginModel loginModel) {
        this.loginModel = loginModel;
        this.loginView = loginView;
    }

    public void onLoginButtonClicked(String email, String password) {
        loginView.showProgressBar();
        if(isValidEmail(email)){
            loginModel.signInWithEmailAndPassword(email, password,this);
        }else{
            loginView.showToast(R.string.invalidEmail);
        }




    }
    public void checkUserEmpty(String email, String password) {
        if (email.isEmpty() || email == null) {
            loginView.showToast(R.string.emptyEmail);
        } else if (password.isEmpty() || password == null) {
            loginView.showToast(R.string.emptyPassword);
        } else {
            onLoginButtonClicked(email, password);
        }
    }


    public boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        loginView.hideProgressBar();
        if (task.isSuccessful()) {
            // Sign in success, update UI with the signed-in user's information
            if (UserInfo.getInstance().getRole() == UserInfo.RoleType.Student) {
                loginView.navigateToStudentHome();
            } else if (UserInfo.getInstance().getRole() == UserInfo.RoleType.Admin) {
                loginView.navigateToAdminHome();
            }
        } else {
            // If sign in fails, display a message to the user.
            loginView.showToast(R.string.auth);
        }
    }
}
