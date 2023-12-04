package com.example.b07project;


import android.app.Activity;

public class LoginPresenter extends Activity{
    private  LoginModel loginModel;
    private  LoginView loginView;

    public LoginPresenter() {
    }
    public LoginPresenter(LoginView loginView,LoginModel loginModel) {
        this.loginModel = loginModel;
        this.loginView = loginView;
    }

    public void onLoginButtonClicked(String email, String password) {
        loginView.showProgressBar();
        if(checkUser(email,password)) {
            loginModel.signInWithEmailAndPassword(this, email, password);
        }

    }
    public boolean checkUser(String email, String password) {
        if(loginView.validEmail(email)&&loginView.validPassword(password)) {
            return true;
        }
        else return false;
    }



    public void onSuccessful() {
        loginView.navigateOnSuccess();
    }

    public void onFail() {
        loginView.onLoginFailed();
    }

}
