package com.example.b07project;

import android.content.Intent;
import android.view.View;

public class LoginPresenter {
    private LoginModel loginModel;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginModel = new LoginModel();
        this.loginView = loginView;
    }

    public void onLoginButtonClicked(String email, String password) {
        loginView.showProgressBar();

        loginModel.signInWithEmailAndPassword(email, password, task -> {
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
                loginView.showErrorMessage();
            }
        });
    }
}
