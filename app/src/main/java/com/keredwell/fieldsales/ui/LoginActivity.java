package com.keredwell.fieldsales.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.keredwell.fieldsales.ui.base.BaseActivity;
import com.keredwell.fieldsales.R;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {
    private static final String TAG = makeLogTag(LoginActivity.class);

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Find the view pager that will allow the user to swipe between fragments
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        LoginPageAdapter adapter = new LoginPageAdapter(getFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
    }

    @Override
    public boolean providesActivityToolbar() {
        return true;
    }
}