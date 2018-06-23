package com.keredwell.fieldsales.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.keredwell.fieldsales.ui.base.BaseActivity;
import com.keredwell.fieldsales.R;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class LoginActivity extends BaseActivity {
    private static final String TAG = makeLogTag(LoginActivity.class);

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        LoginPageAdapter adapter = new LoginPageAdapter(getFragmentManager());

        viewPager.setAdapter(adapter);

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