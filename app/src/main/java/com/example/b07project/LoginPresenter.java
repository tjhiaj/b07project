package com.example.b07project;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

interface SignInCallback extends OnCompleteListener<AuthResult>{
}
public class LoginPresenter implements SignInCallback{
    private final LoginModel loginModel;
    private final LoginView loginView;

    public LoginPresenter(LoginView loginView,LoginModel loginModel) {
        this.loginModel = loginModel;
        this.loginView = loginView;
    }

    public void onLoginButtonClicked(String email, String password) {
        if(isValidEmail(email)){
            loginView.showProgressBar();
            loginModel.signInWithEmailAndPassword(email,password,this);
        }else{
            loginView.showToast("Invalid email address.");
        }


//        loginModel.signInWithEmailAndPassword(email, password, task -> {
//            loginView.hideProgressBar();
//            if (task.isSuccessful()) {
//                // Sign in success, update UI with the signed-in user's information
//                if (UserInfo.getInstance().getRole() == UserInfo.RoleType.Student) {
//                    loginView.navigateToStudentHome();
//                } else if (UserInfo.getInstance().getRole() == UserInfo.RoleType.Admin) {
//                    loginView.navigateToAdminHome();
//                }
//            } else {
//                // If sign in fails, display a message to the user.
//                loginView.showErrorMessage();
//            }
//        });

    }
    public void checkUser(String email, String password) {
        if (email.isEmpty() || email == null) {
            loginView.showToast("Email cannot be empty.");
        } else if (password.isEmpty() || password == null) {
            loginView.showToast("Password cannot be empty.");
        } else {
            onLoginButtonClicked(email, password);
        }
    }


    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){
            loginView.hideProgressBar();
            AuthResult authResult = task.getResult();
            if(authResult != null && authResult.getUser()!=null){
                // Sign in success, update UI with the signed-in user's information
                if (UserInfo.getInstance().getRole() == UserInfo.RoleType.Student) {
                    loginView.navigateToStudentHome();
                } else if (UserInfo.getInstance().getRole() == UserInfo.RoleType.Admin) {
                    loginView.navigateToAdminHome();
                }
            }
        }else{
            // If sign in fails, display a message to the user.
            loginView.showToast("Authentication failed.");
        }
    }
    public boolean isValidEmail(String email) {
        // Add your email validation logic here
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
