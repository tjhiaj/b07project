package com.example.b07project;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;

import com.example.b07project.LoginModel;
import com.example.b07project.LoginPresenter;
import com.example.b07project.LoginView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Mock
    LoginView view;
    @Mock
    LoginModel model;



    LoginPresenter presenter;

    @Before
    public void setUp() {
        presenter = new LoginPresenter(view, model);
    }

    @Test
    public void checkEmptyUsername(){
        presenter.checkInputEmpty("", "password");
        verify(view).emailEmpty();

    }

    @Test
    public void checkNullUsername(){
        presenter.checkInputEmpty(null, "password");
        verify(view).emailEmpty();
    }

    @Test
    public void checkEmptyPassword(){
        presenter.checkInputEmpty("email", "");
        verify(view).passwordEmpty();
    }

    @Test
    public void checkNullPassword(){
        presenter.checkInputEmpty("email", null);
        verify(view).passwordEmpty();
    }
    @Test
    public void checkLoginSuccessStudent() {
        // Arrange
        String email = "jonathanzhca@gmail.com";
        String password = "123456";
        Task<AuthResult> task =  Mockito.mock(Task.class);
        Mockito.when(task.isSuccessful()).thenReturn(true);
        when(model.signInWithEmailAndPassword(any(),any())).thenReturn(task);
        UserInfo.getInstance().setRole(UserInfo.RoleType.Student);
        presenter.onLoginButtonClicked(email, password);
        verify(view).navigateToStudentHome();

    }

    @Test
    public void checkLoginSuccessAdmin() {
        // Arrange
        String email = "jonathanzhca@gmail.com";
        String password = "123456";
        Task<AuthResult> task =  Mockito.mock(Task.class);
        Mockito.when(task.isSuccessful()).thenReturn(true);
        when(model.signInWithEmailAndPassword(any(),any())).thenReturn(task);
        UserInfo.getInstance().setRole(UserInfo.RoleType.Admin);
        presenter.onLoginButtonClicked(email, password);
        verify(view).navigateToAdminHome();

    }


    @Test
    public void checkLoginFailure() {
        // Arrange
        // Arrange
        String email = "test@example.com";
        String password = "testPassword";

        Task<AuthResult> task =  Mockito.mock(Task.class);
        Mockito.when(task.isSuccessful()).thenReturn(false);
        when(model.signInWithEmailAndPassword(any(),any())).thenReturn(task);
        UserInfo.getInstance().setRole(UserInfo.RoleType.Admin);
        presenter.onLoginButtonClicked(email, password);


        verify(view).showErrorMessage();

    }









}
