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

/**
 * Lists all available quotes. This Activity supports a single pane (= smartphones) and a two pane mode (= large screens with >= 600dp width).
 *
 * Created by Andreas Schrade on 14.12.2015.
 */
public class OrderListActivity extends BaseActivity {
    private static final String TAG = makeLogTag(OrderListActivity.class);

    /**
     * Whether or not the activity is running on a device with a large screen
     */
    private boolean twoPaneMode;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_orderlisting_list);

        // Find the view pager that will allow the user to swipe between fragments
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        OrderListPageAdapter adapter = new OrderListPageAdapter(getFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        setupToolbar();

        if (isTwoPaneLayoutUsed()) {
            twoPaneMode = true;
            enableActiveItemState();
        }

        if (savedInstanceState == null && twoPaneMode) {
            //setupDetailFragment();
        }
    }

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Enables the functionality that selected items are automatically highlighted.
     */
    private void enableActiveItemState() {
        Fragment page = getFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + viewPager.getCurrentItem());
        // based on the current position you can then cast the page to the correct
        // class and call the method:
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

    /**
     * Is the container present? If so, we are using the two-pane layout.
     *
     * @return true if the two pane layout is used.
     */
    private boolean isTwoPaneLayoutUsed() {
        return findViewById(R.id.article_detail_container) != null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_actions, menu);

        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) searchViewItem.getActionView();
        searchView.setQueryHint("Search for customers...");
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);// Do not iconify the widget; expand it by default

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String query) {
                // This is your adapter that will be filtered
                //Toast.makeText(getApplicationContext(),"textChanged :"+newText,Toast.LENGTH_LONG).show();

                Fragment page = getFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + viewPager.getCurrentItem());
                // based on the current position you can then cast the page to the correct
                // class and call the method:
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
                // **Here you can get the value "query" which is entered in the search box.**

                Fragment page = getFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + viewPager.getCurrentItem());
                // based on the current position you can then cast the page to the correct
                // class and call the method:
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
