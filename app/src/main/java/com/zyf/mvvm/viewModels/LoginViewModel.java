package com.zyf.mvvm.viewModels;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.zyf.mvvm.R;
import com.zyf.mvvm.databinding.ActivityLoginBinding;
import com.zyf.mvvm.views.LoginActivity;

/**
 * Created by zyf on 2017/3/17.
 */

public class LoginViewModel extends BaseObservable {
    /**
     * A dummy authentication store containing known mainViewModel names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    public final ObservableField<String> mEmail=new ObservableField<>();
    public final ObservableField<String> mPassword=new ObservableField<>();
    private Context mView;
    private ActivityLoginBinding mBinding;
    public LoginViewModel(Context view, ActivityLoginBinding binding) {
        this.mView = view;
        this.mBinding=binding;
    }

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;
    public void onClick(View view){
        Toast.makeText(view.getContext(), "登陆", Toast.LENGTH_LONG).show();
        attemptLogin();
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Store values at the time of the login attempt.
        String email = mEmail.get();
        String password = mPassword.get();

        boolean cancel = false;
        View focusView = null;
        // Check for a valid password, if the mainViewModel entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mBinding.password.setError(mView.getString(R.string.error_invalid_password));
            focusView = mBinding.password;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mBinding.email.setError(mView.getString(R.string.error_field_required));
            focusView = mBinding.email;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mBinding.email.setError(mView.getString(R.string.error_invalid_email));
            focusView = mBinding.email;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();

        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the mainViewModel login attempt.
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
        mPassword.set("没有密码");
    }
    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the mainViewModel.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
//            showProgress(false);
//
//            if (success) {
//                finish();
//            } else {
//                mPasswordView.setError(getString(R.string.error_incorrect_password));
//                mPasswordView.requestFocus();
//            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            //showProgress(false);
        }
    }
}
