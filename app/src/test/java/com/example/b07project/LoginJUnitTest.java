package com.example.b07project;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginJUnitTest {
    @Mock
    LoginModel loginModel;
    @Mock
    LoginView loginView;
    @Mock
    EditText email;
    @Mock
    EditText password;
    @Mock
    Editable edit;
    @Mock
    View baseView;






}
