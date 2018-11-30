package com.vbagetech.facebook.google.login.loginwithgoogle_facebook_from_firebase;

import android.app.Activity;
import android.app.ProgressDialog;

import com.google.android.gms.common.util.VisibleForTesting;

public class PrgressDialogClass {
    @VisibleForTesting
    public ProgressDialog mProgressDialog;

    PrgressDialogClass(Activity activity, boolean show) {
        if (show) {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(activity);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.setIndeterminate(true);
            }
            mProgressDialog.show();
        } else {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }
    }
}
