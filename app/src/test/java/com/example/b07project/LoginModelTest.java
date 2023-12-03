package com.example.b07project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginModelTest {

    @Mock
    FirebaseAuth mAuth;

    @Mock
    OnCompleteListener<AuthResult> onCompleteListener;

    private LoginModel loginModel;

    @Before
    public void setUp() {
        loginModel = new LoginModel();
    }

    @Test
    public void signInWithEmailAndPassword_Success() {
        // Arrange
        String email = "test@example.com";
        String password = "password";

        // Mock FirebaseAuth.getInstance() to return a mock FirebaseAuth instance
        when(FirebaseAuth.getInstance()).thenReturn(mAuth);
        when(mAuth.signInWithEmailAndPassword(email, password)).thenReturn(TaskMocker.mockTask(true));

        // Act
        loginModel.signInWithEmailAndPassword(email, password, onCompleteListener);

        // Assert
        verify(mAuth).signInWithEmailAndPassword(email, password);
        // Add more assertions as needed
    }

    @Test
    public void signInWithEmailAndPassword_Failure() {
        // Arrange
        String email = "test@example.com";
        String password = "password";

        // Mock FirebaseAuth.getInstance() to return a mock FirebaseAuth instance
        when(FirebaseAuth.getInstance()).thenReturn(mAuth);
        when(mAuth.signInWithEmailAndPassword(email, password)).thenReturn(TaskMocker.mockTask(false));

        // Act
        loginModel.signInWithEmailAndPassword(email, password, onCompleteListener);

        // Assert
        verify(mAuth).signInWithEmailAndPassword(email, password);
        // Add more assertions as needed
    }


}
