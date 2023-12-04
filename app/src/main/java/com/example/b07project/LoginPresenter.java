package com.example.b07project;

import android.app.Activity;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginPresenter extends Activity {
    private LoginModel loginModel;
    private LoginView loginView;

    public LoginPresenter() {
    }

    public LoginPresenter(LoginView loginView, LoginModel loginModel) {
        this.loginModel = loginModel;
        this.loginView = loginView;
    }

    public void onLoginButtonClicked(String email, String password) {
        loginView.showProgressBar();

        Task<AuthResult> task = loginModel.signInWithEmailAndPassword(email, password);

        if (task.isSuccessful()) {
            loginView.hideProgressBar();
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
    }

    public void checkInputEmpty(String email, String password) {
        if (email == null || email.isEmpty()) {
            loginView.emailEmpty();
        } else if (password == null || password.isEmpty()) {
            loginView.passwordEmpty();
        } else {
            onLoginButtonClicked(email, password);
        }

    }
}
