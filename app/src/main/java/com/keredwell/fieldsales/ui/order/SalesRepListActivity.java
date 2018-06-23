package com.keredwell.fieldsales.ui.order;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.keredwell.fieldsales.R;
import com.keredwell.fieldsales.data.AD_User;
import com.keredwell.fieldsales.data.C_BP_Group;
import com.keredwell.fieldsales.data.C_BPartner;
import com.keredwell.fieldsales.dbhelper.AD_UserDBAdapter;
import com.keredwell.fieldsales.dbhelper.C_BP_GroupDBAdapter;
import com.keredwell.fieldsales.dbhelper.C_BPartnerDBAdapter;
import com.keredwell.fieldsales.ui.base.BaseActivity;
import com.keredwell.fieldsales.ui.customer.CustomerDetailFragment;

import java.util.ArrayList;
import java.util.List;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class SalesRepListActivity extends BaseActivity implements CustomerListFragment.Callback {
    private static final String TAG = makeLogTag(SalesRepListActivity.class);
    /**
     * Whether or not the activity is running on a device with a large screen
     */
    private ArrayList<AD_User> ad_users = new ArrayList<>();

    private AD_UserDBAdapter ad_userDBAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ad_userDBAdapter = new AD_UserDBAdapter(this);

        loadList();

        setContentView(R.layout.activity_order_salesrep_list);

        setupToolbar();
    }

    public void loadList(){
        ad_users = ad_userDBAdapter.getAllAD_Users();

        Fragment f = getFragmentManager().findFragmentById(R.id.article_list);
        if (f instanceof SalesRepListFragment)
            ((SalesRepListFragment) f).refreshData();
    }

    public ArrayList<AD_User> getListADUser()
    {
        return this.ad_users;
    }

    /**
     * Called when an item has been selected
     *
     * @param id the selected quote ID
     */
    @Override
    public void onItemSelected(int id) {
        AD_User ad_user = ad_users.get(id);
        Intent intent = new Intent();
        intent.putExtra("ad_user", ad_user);
        setResult(RESULT_OK, intent);
        finish();
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
        SalesRepListFragment fragmentById = (SalesRepListFragment) getFragmentManager().findFragmentById(R.id.article_list);
        fragmentById.getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_actions, menu);

        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) searchViewItem.getActionView();
        searchView.setQueryHint("Search for sales rep...");
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String query) {
                if (TextUtils.isEmpty(query)) {
                    ad_users = ad_userDBAdapter.getAllAD_Users();
                }
                else {
                    ad_users = ad_userDBAdapter.getAllAD_UsersBySearch(query);
                }
                Fragment f = getFragmentManager().findFragmentById(R.id.article_list);
                if (f instanceof SalesRepListFragment)
                    ((SalesRepListFragment) f).refreshData();

                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                if (TextUtils.isEmpty(query)) {
                    ad_users = ad_userDBAdapter.getAllAD_Users();
                }
                else {
                    ad_users = ad_userDBAdapter.getAllAD_UsersBySearch(query);
                }
                Fragment f = getFragmentManager().findFragmentById(R.id.article_list);
                if (f instanceof SalesRepListFragment)
                    ((SalesRepListFragment) f).refreshData();

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
        return R.id.nav_order;
    }

    @Override
    public boolean providesActivityToolbar() {
        return true;
    }
}
