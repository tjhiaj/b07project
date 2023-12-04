package com.example.b07project;

import org.junit.Test;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginJUnitTest {
    @Mock
    LoginModel model;
    @Mock
    LoginView view;
    @Mock
    String email,password;
    @Mock
    OnCompleteListener<AuthResult> onCompleteListener;

    @Test
    public void testPresenter(){
        when(view.getEmail()).thenReturn("email");
        when(view.getPassword()).thenReturn("password");
        verify(model).signInWithEmailAndPassword(email,password,onCompleteListener);
        LoginPresenter presenter = new LoginPresenter(view,model);
        presenter.checkUserEmpty("",password);
        verify(view).showToast(R.string.emptyEmail);
        presenter.checkUserEmpty(email,"");
        verify(view).showToast(R.string.emptyPassword);
        presenter.isValidEmail("email");
        verify(view).showToast(R.string.invalidEmail);
        presenter.isValidEmail("a@gmail.com");
        presenter.onLoginButtonClicked(email,password);
        presenter.onComplete(mock(Task.class));
        InOrder order= inOrder(model,view);
        order.verify(model).signInWithEmailAndPassword(email,password,onCompleteListener);
        order.verify(view).navigateToStudentHome();
        order.verify(view).navigateToAdminHome();


    }







}
