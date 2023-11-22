package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AdminOrStudentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_or_student);
    }
    public void OnAdminButtonClick(View view) {
        UserInfo.getInstance().setRole(UserInfo.RoleType.Admin);
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void OnStudentButtonClick(View view) {
        UserInfo.getInstance().setRole(UserInfo.RoleType.Student);
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
