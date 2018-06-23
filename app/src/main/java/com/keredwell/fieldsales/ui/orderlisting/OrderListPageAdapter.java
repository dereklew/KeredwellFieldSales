package com.keredwell.fieldsales.ui.orderlisting;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.keredwell.fieldsales.ApplicationContext;
import com.keredwell.fieldsales.R;

public class OrderListPageAdapter extends FragmentPagerAdapter {

    public static final int FRAG_INCOMPLETE = 0;
    public static final int FRAG_COMPLETED = 1;
    public static final int FRAG_NOTSYNC = 2;

    public OrderListPageAdapter(FragmentManager fm) {
        super(fm);
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case FRAG_INCOMPLETE:
                return new OrderListInCompleteFragment();
            case FRAG_COMPLETED:
                return new OrderListCompletedFragment();
            case FRAG_NOTSYNC:
                return new OrderListNotSyncFragment();
            default:
                return null;
        }
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case FRAG_INCOMPLETE:
                return ApplicationContext.getAppContext().getString(R.string.tab_incomplete);
            case FRAG_COMPLETED:
                return ApplicationContext.getAppContext().getString(R.string.tab_completed);
            case FRAG_NOTSYNC:
                return ApplicationContext.getAppContext().getString(R.string.tab_not_sync);
            default:
                return null;
        }
    }
}