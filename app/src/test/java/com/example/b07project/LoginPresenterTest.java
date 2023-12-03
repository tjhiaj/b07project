package com.example.b07project;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    LoginModel model;

    @Mock
    LoginView view;

    @Mock
    LoginPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new LoginPresenter(view,model);
    }

    @Test
    public void onLoginButtonClicked_Success_student() {
        String email = "jonathanzhca@gmail.com";
        String password = "123456";

        presenter.onLoginButtonClicked(email, password);

        // Assert
        verify(view).showProgressBar();
        verify(view).hideProgressBar();
        verify(view).navigateToStudentHome();
        verify(view).getEmail();
        verify(view).getPassword();
    }


    @Test
    public void onLoginButtonClicked_Success_admin() {
        String email = "jonathanzhca@gmail.com";
        String password = "password";

        presenter.onLoginButtonClicked(email, password);

        // Assert
        verify(view).showProgressBar();
        verify(view).hideProgressBar();
        verify(view).navigateToAdminHome();
        verify(view).getEmail();
        verify(view).getPassword();
    }


    @Test
    public void onLoginButtonClicked_invalid_email() {
        // Arrange
        String email = "invalidEmail";
        String password = "123456";

        presenter.onLoginButtonClicked(email, password);
        // Assert
        verify(view,never()).showProgressBar();
        verify(view).showToast(eq("Invalid email address."));
    }

    @Test
    public void onCompleteTask_Failure() {
        // Arrange
        Task<AuthResult> task = mock(Task.class);
        when(task.isSuccessful()).thenReturn(false);
        presenter.onComplete(task);
        // Assert
        verify(view).hideProgressBar();
        verify(view).showToast(eq("Authentication Failed."));
        verify(view,never()).navigateToAdminHome();
        verify(view,never()).navigateToStudentHome();
    }

    @Test
    public void ValidEmail(){
        String email = "test@example.com";
        boolean isValid = presenter.isValidEmail(email);
        assert isValid;
    }

    @Test
    public void inValidEmail(){
        String email = "invalidEmail";
        boolean isValid = presenter.isValidEmail(email);
        assert !isValid;
    }

    @Test
    public void EmptyEmail(){
        String email = "";
        String password = "123";
        presenter.checkUser(email,password);
        verify(view).showToast(eq("Email cannot be empty."));
    }
    @Test
    public void EmptyPassword(){
        String email = "test@example.com";
        String password = "";
        presenter.checkUser(email,password);
        verify(view).showToast(eq("Password cannot be empty."));
    }


}
