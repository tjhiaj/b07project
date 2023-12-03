package com.example.b07project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginView extends AppCompatActivity {
    //TextInputEditText editTextEmail, editTextPassword;
    Button buttonLogin;

    ProgressBar progressBar;

    TextView textView;

    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter(this,new LoginModel());


        //editTextEmail = findViewById(R.id.email);
        //editTextPassword = findViewById(R.id.password);
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
                //String email, password;
                //email = String.valueOf(editTextEmail.getText());
                //password = String.valueOf(editTextPassword.getText());
                String email = getEmail();
                String password = getPassword();
                loginPresenter.checkUser(email,password);
                //loginPresenter.onLoginButtonClicked(email, password);

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
    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}

