# [Firebase Facebook Login](https://qna.vbagetech.com/question/363/How-to-implement-Firebase-Facebook-Login-or-Authenticate-Using-Facebook-Login-in-android) and Firebase Google Login
This repository has module to implement Firebase Social Login.
import Module by clicking on `File-> New -> Import Module`

include in your App level Gradle
[`
    implementation 'com.facebook.android:facebook-android-sdk:[4,5)'
    implementation 'com.google.firebase:firebase-auth:16.0.5'
    implementation project(path: ':loginwithgoogle_facebook_from_firebase')`]


call below code in your LoginActivity
[`LoginButton loginButton=findViewById(R.id.fbLogin_Buttin_ID);
        new FacebookFIrebaseLogin(mActivity, loginButton, new ResponceClass() {
            @Override
            public void updateUI(FirebaseUser user) {
                
            }
        });`]


# Firebase Login Demo for Android

This demo Android app demonstrates authenticating with Firebase Login for Facebook, Google, Twitter,
email & password, and anonymous.

![screenshot showing authentication provider buttons](/screenshot.png)

### Setup
0. Sign up and create a new [Firebase](https://www.firebase.com).
0. Create apps for each provider on their developer consoles. See the Firebase
   [authentication provider docs](https://www.firebase.com/docs/android/guide/user-auth.html#section-providers)
   for more details.
    - [Facebook](https://developers.facebook.com/docs/android/getting-started)
    - [Google](https://developers.google.com/+/mobile/android/getting-started)
	
0. Enable Facebook, Google, Twitter, Email, and Anonymous providers on the Firebase Dashboard for
   your app. See the
   [enabling providers section](https://www.firebase.com/docs/android/guide/user-auth.html#section-enable-providers)
   of the Firebase user authentication docs for more details.
0. Populate all of the values in [`res/values/keys.xml`](/app/src/main/res/values/keys.xml).
