package com.example.b07project;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



@RunWith(MockitoJUnitRunner.class)
public class UnitTest {
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
        presenter.checkUser("", "password");
        verify(view).validEmail("");

    }

    @Test
    public void checkNullUsername(){
        presenter.checkUser(null, "password");
        verify(view).validEmail(null);
    }
    @Test
    public void checkInvalidEmail(){
        presenter.checkUser("email", "122");
        verify(view).validEmail("email");
    }

    @Test
    public void checkEmptyPassword(){
        when(view.validEmail(anyString())).thenReturn(true);
        presenter.checkUser("email@gmail.com", "");
        verify(view).validPassword("");
    }

    @Test
    public void checkInvalidPassword(){
        when(view.validEmail(anyString())).thenReturn(true);
        presenter.checkUser("email@gmail.com", "122");
        verify(view).validPassword("122");
    }

    @Test
    public void checkNullPassword(){
        when(view.validEmail(anyString())).thenReturn(true);
        presenter.checkUser("email@gmail.com", null);
        verify(view).validPassword(null);
    }


    @Test
    public void checkLoginSuccess() {
        presenter.onSuccessful();
        verify(view).navigateOnSuccess();

    }


    @Test
    public void checkLoginFailure() {
        presenter.onFail();
        verify(view).onLoginFailed();

    }









}