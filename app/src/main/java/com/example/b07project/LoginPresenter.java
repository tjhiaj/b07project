package com.example.b07project;

import android.widget.Toast;

public class LoginPresenter {
    private final LoginModel loginModel;
    private final LoginView loginView;

    public LoginPresenter(LoginView loginView,LoginModel loginModel) {
        this.loginModel = loginModel;
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
    public void checkInputEmpty(String email, String password) {
        if (email.isEmpty() || email == null) {
            Toast.makeText(loginView, "Email cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty() || password == null) {
            Toast.makeText(loginView, "Password cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            onLoginButtonClicked(email, password);
        }

    }
}
