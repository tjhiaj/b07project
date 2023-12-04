package com.example.b07project;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

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
        presenter = new LoginPresenter(view);
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
    }

    @Test
    public void onLoginButtonClicked_Success_admin() {
        String email = "jonathanzhca@gmail.com";
        String password = "123456";

        presenter.onLoginButtonClicked(email, password);

        // Assert
        verify(view).showProgressBar();
        verify(view).hideProgressBar();
        verify(view).navigateToAdminHome();
    }



    @Test
    public void onLoginButtonClicked_Failure() {
        // Arrange

        // Assert
        verify(view).showProgressBar();
        verify(view).hideProgressBar();
        verify(view).showErrorMessage();
    }
}
