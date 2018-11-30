package com.vbagetech.facebook.google.login.loginwithgooglefacebook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseUser;
import com.vbagetech.facebook.google.login.loginwithgoogle_facebook_from_firebase.FacebookFIrebaseLogin;
import com.vbagetech.facebook.google.login.loginwithgoogle_facebook_from_firebase.ResponceClass;

public class LoginWithGoogleFacebookActivity extends AppCompatActivity {
    Activity mActivity;
    FacebookFIrebaseLogin facebookFIrebaseLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_google_facebook);
        mActivity = LoginWithGoogleFacebookActivity.this;
        LoginButton loginButton = findViewById(R.id.fbLogin_Buttin_ID);
        facebookFIrebaseLogin = new FacebookFIrebaseLogin(mActivity, loginButton, new ResponceClass() {
            @Override
            public void updateUI(FirebaseUser user) {
                facebookFIrebaseLogin.loadingDialog(false);
                if (user != null) {
                    startActivity(new Intent(mActivity, MainActivity.class));
                    finish();
                }
            }
        });
    }
}
