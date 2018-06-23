package com.keredwell.fieldsales.ui.orderlisting;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.keredwell.fieldsales.R;
import com.keredwell.fieldsales.ui.base.BaseActivity;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class OrderListActivity extends BaseActivity {
    private static final String TAG = makeLogTag(OrderListActivity.class);

    private boolean twoPaneMode;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_orderlisting_list);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        OrderListPageAdapter adapter = new OrderListPageAdapter(getFragmentManager());

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        setupToolbar();
    }

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void enableActiveItemState() {
        Fragment page = getFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + viewPager.getCurrentItem());
        if (viewPager.getCurrentItem() == OrderListPageAdapter.FRAG_INCOMPLETE && page != null) {
            ((OrderListInCompleteFragment)page).getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
        else if (viewPager.getCurrentItem() == OrderListPageAdapter.FRAG_COMPLETED && page != null) {
            ((OrderListCompletedFragment)page).getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
        else if (viewPager.getCurrentItem() == OrderListPageAdapter.FRAG_NOTSYNC && page != null) {
            ((OrderListNotSyncFragment)page).getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_actions, menu);

        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) searchViewItem.getActionView();
        searchView.setQueryHint("Search for customers...");
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);// Do not iconify the widget; expand it by default

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String query) {
                Fragment page = getFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + viewPager.getCurrentItem());
                if (viewPager.getCurrentItem() == OrderListPageAdapter.FRAG_INCOMPLETE && page != null) {
                    ((OrderListInCompleteFragment)page).updateList(query);
                }
                else if (viewPager.getCurrentItem() == OrderListPageAdapter.FRAG_COMPLETED && page != null) {
                    ((OrderListCompletedFragment)page).updateList(query);
                }
                else if (viewPager.getCurrentItem() == OrderListPageAdapter.FRAG_NOTSYNC && page != null) {
                    ((OrderListNotSyncFragment)page).updateList(query);
                }

                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                Fragment page = getFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + viewPager.getCurrentItem());
                if (viewPager.getCurrentItem() == OrderListPageAdapter.FRAG_INCOMPLETE && page != null) {
                    ((OrderListInCompleteFragment)page).updateList(query);
                }
                else if (viewPager.getCurrentItem() == OrderListPageAdapter.FRAG_COMPLETED && page != null) {
                    ((OrderListCompletedFragment)page).updateList(query);
                }
                else if (viewPager.getCurrentItem() == OrderListPageAdapter.FRAG_NOTSYNC && page != null) {
                    ((OrderListNotSyncFragment)page).updateList(query);
                }

                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                openDrawer();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getSelfNavDrawerItem() {
        return R.id.nav_history;
    }

    @Override
    public boolean providesActivityToolbar() {
        return true;
    }
}
