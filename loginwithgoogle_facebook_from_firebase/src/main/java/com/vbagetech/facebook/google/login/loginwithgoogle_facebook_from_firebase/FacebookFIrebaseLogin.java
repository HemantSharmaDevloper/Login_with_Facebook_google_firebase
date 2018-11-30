package com.vbagetech.facebook.google.login.loginwithgoogle_facebook_from_firebase;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

import static com.facebook.GraphRequest.TAG;

public class FacebookFIrebaseLogin {
    private CallbackManager mCallbackManager;
    private FirebaseAuth mAuth;
    Activity mActivity;
    ResponceClass responceClass;

    public FacebookFIrebaseLogin(Activity activity, LoginButton fbloginButton, ResponceClass responce) {
        this.mActivity = activity;
        mAuth = FirebaseAuth.getInstance();
        this.responceClass = responce;
        mCallbackManager = CallbackManager.Factory.create();
//        LoginButton loginButton = mActivity.findViewById(R.id.signInFBbutton_ID);
        LoginButton loginButton = fbloginButton;
        loginButton.setReadPermissions("public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.d("onCompleted", object.toString());
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, name, link, email");
                request.setParameters(parameters);
                request.executeAsync();
                //Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // Log.d(TAG, "facebook:onCancel");
                // [START_EXCLUDE]

                responceClass.updateUI(null);
                // [END_EXCLUDE]
            }

            @Override
            public void onError(FacebookException error) {
                // Log.d(TAG, "facebook:onError", error);
                // [START_EXCLUDE]
                responceClass.updateUI(null);
                // [END_EXCLUDE]
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        //   Log.d(TAG, "handleFacebookAccessToken:" + token);
        // [START_EXCLUDE silent]
        loadingDialog(true);
        // [END_EXCLUDE]

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    //  Log.d(TAG, "signInWithCredential:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    responceClass.updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d(TAG, "signInWithCredential:failure", task.getException());
                    Toast.makeText(mActivity, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    responceClass.updateUI(null);
                }
                // [START_EXCLUDE]
                loadingDialog(false);
                // [END_EXCLUDE]
            }
        });
    }

    public void loadingDialog(boolean show) {
        new PrgressDialogClass(mActivity, show);
    }
}
