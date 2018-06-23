package com.keredwell.fieldsales.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.keredwell.fieldsales.ApplicationContext;
import com.keredwell.fieldsales.R;
import com.keredwell.fieldsales.data.User;
import com.keredwell.fieldsales.ui.base.BaseFragment;
import com.keredwell.fieldsales.ui.order.OrderListActivity;
import com.keredwell.fieldsales.util.LogUtil;
import com.keredwell.fieldsales.util.SharedPrefUtil;
import com.keredwell.fieldsales.webservice.UserWS;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class LoginPromptFragment extends BaseFragment {

    private static final String TAG = makeLogTag(LoginPromptFragment.class);

    private UserLoginTask mAuthTask = null;

    private EditText mUserView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private View mFabView;
    private CheckBox mShowPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.include_login_view, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        if(view != null) {
            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Login", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    attemptLogin();
                }
            });
            mUserView = (EditText) view.findViewById(R.id.user);

            mPasswordView = (EditText) view.findViewById(R.id.password);
            mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                    if (id == R.id.login_main || id == EditorInfo.IME_NULL) {
                        attemptLogin();
                        return true;
                    }
                    return false;
                }
            });

            mShowPassword = (CheckBox) view.findViewById(R.id.checkbox_password);
            mShowPassword.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    boolean checked = ((CheckBox) v).isChecked();
                    if (checked)
                        mPasswordView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    else
                        mPasswordView.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            });

            mLoginFormView = view.findViewById(R.id.login_main);
            mProgressView = view.findViewById(R.id.login_progress);
            mFabView = view.findViewById(R.id.login_fab);
        }
    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }
        mUserView.setError(null);
        mPasswordView.setError(null);

        String user = mUserView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(user)) {
            mUserView.setError(getString(R.string.error_field_required));
            focusView = mUserView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            mAuthTask = new UserLoginTask(user, password);
            mAuthTask.execute((Void) null);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mFabView.setVisibility(show ? View.GONE : View.VISIBLE);
            mFabView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mFabView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mFabView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mUser;
        private final String mPassword;

        UserLoginTask(String user, String password) {
            mUser = user;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try{
                User user = UserWS.UserWSEvent(mUser, mPassword);
                if (user != null)
                {
                    SharedPrefUtil.setPersistedData(ApplicationContext.USERID, user.getID());
                    SharedPrefUtil.setPersistedData(ApplicationContext.USERNAME, mUser);
                    SharedPrefUtil.setPersistedData(ApplicationContext.USERPASS, mPassword);

                    return true;
                }
                else
                {
                    return false;
                }
            } catch (Exception e) {
                LogUtil.logE(TAG, e.getMessage(), e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                //finish();
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle(R.string.synchronization_title);
                alertDialog.setMessage(getActivity().getString(R.string.wanttosync));
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getActivity().getString(R.string.yes),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getActivity(), SynchronizationActivity.class);
                                startActivity(intent);
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getActivity().getString(R.string.no),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getActivity()
                                        , OrderListActivity.class);
                                startActivity(intent);
                            }
                        });
                alertDialog.show();
            } else {
                mPasswordView.setError(getString(R.string.error_connection));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
