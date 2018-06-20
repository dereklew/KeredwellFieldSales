package com.keredwell.fieldsales.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.keredwell.fieldsales.ApplicationContext;
import com.keredwell.fieldsales.R;

public class LoginPageAdapter  extends FragmentPagerAdapter {

    public static final int FRAG_PROMPT = 0;
    public static final int FRAG_SETTING = 1;

    public LoginPageAdapter(FragmentManager fm) {
        super(fm);
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case FRAG_PROMPT:
                return new LoginPromptFragment();
            case FRAG_SETTING:
                return new LoginSettingFragment();
            default:
                return null;
        }
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case FRAG_PROMPT:
                return ApplicationContext.getAppContext().getString(R.string.action_sign_in);
            case FRAG_SETTING:
                return ApplicationContext.getAppContext().getString(R.string.action_settings);
            default:
                return null;
        }
    }
}