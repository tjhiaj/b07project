package com.example.b07project;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;


public class LoginView extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword;
    Button buttonLogin;

    ProgressBar progressBar;

    TextView textView;

    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter(this,new LoginModel());


        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.registerNow);

        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email = getEmail();
                String password = getPassword();
                loginPresenter.onLoginButtonClicked(email, password);

            }
        });


    }
    public String getEmail(){
        TextInputEditText editTextEmail  = findViewById(R.id.email);
        return String.valueOf(editTextEmail.getText());
    }
    public String getPassword(){
        TextInputEditText editTextPassword = findViewById(R.id.password);
        return String.valueOf(editTextPassword.getText());
    }


    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }


    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    public void navigateOnSuccess(){
       hideProgressBar();
        // Sign in success, update UI with the signed-in user's information
        if (UserInfo.getInstance().getRole() == UserInfo.RoleType.Student) {
            navigateToStudentHome();
        } else if (UserInfo.getInstance().getRole() == UserInfo.RoleType.Admin) {
            navigateToAdminHome();
        }
    }
    public void onLoginFailed(){
        showToast(R.string.auth);
    }

    public boolean validEmail(String email){
        if (email == null|| email.isEmpty() || PatternsCompat.EMAIL_ADDRESS == null) {
            showToast(R.string.emptyEmail);
            return false;
        }
        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            showToast(R.string.invalidEmail);
            return false;
        }
        else return true;

    }
    public boolean validPassword(String password){
        if (password == null || password.isEmpty()) {
            showToast(R.string.emptyPassword);
            return false;
        }else if(password.length()<6){
            showToast(R.string.invalidPassword);
            return false;
        }
        else return true;
    }

    public void navigateToStudentHome() {
        Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
        startActivity(intent);
        finish();
    }


    public void navigateToAdminHome() {
        Intent intent = new Intent(getApplicationContext(), AdminHomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void OnLoginBackButtonClick(View view) {
        Intent intent = new Intent(this, AdminOrStudentActivity.class);
        startActivity(intent);
    }
    public void showToast(int msgId){
        Toast.makeText(this,msgId,Toast.LENGTH_SHORT).show();
    }

}

