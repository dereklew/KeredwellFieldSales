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

import com.keredwell.fieldsales.data.C_BP_Group;
import com.keredwell.fieldsales.dbhelper.C_BP_GroupDBAdapter;
import com.keredwell.fieldsales.dbhelper.C_BPartnerDBAdapter;
import com.keredwell.fieldsales.R;
import com.keredwell.fieldsales.data.C_BPartner;
import com.keredwell.fieldsales.ui.base.BaseActivity;
import com.keredwell.fieldsales.ui.customer.CustomerDetailFragment;

import java.util.ArrayList;
import java.util.List;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class CustomerListActivity extends BaseActivity implements CustomerListFragment.Callback {
    private static final String TAG = makeLogTag(CustomerListActivity.class);

    private List<String> customerGroups = new ArrayList<>();
    private ArrayList<C_BPartner> customers = new ArrayList<>();

    private C_BPartnerDBAdapter c_bPartnerDBAdapter;
    private C_BP_GroupDBAdapter c_bp_groupDBAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        c_bPartnerDBAdapter = new C_BPartnerDBAdapter(this);
        c_bp_groupDBAdapter = new C_BP_GroupDBAdapter(this);

        loadCustomerGroup();
        loadList("", 0);

        setContentView(R.layout.activity_order_customers_list);

        setupToolbar();
    }

    public void loadList(String cg, int mode){
        C_BP_Group customerGroup;
        if (mode == 0)
            customerGroup = c_bp_groupDBAdapter.getC_BP_Group(customerGroups.get(0));
        else
            customerGroup = c_bp_groupDBAdapter.getC_BP_Group(cg);

        customers = c_bPartnerDBAdapter.getAllC_BPartnersByGroupID(customerGroup.getC_BP_Group_ID());

        if (mode == 1) {
            Fragment f = getFragmentManager().findFragmentById(R.id.article_list);
            if (f instanceof CustomerListFragment)
                ((CustomerListFragment) f).refreshData();
        }
    }

    public void loadCustomerGroup()
    {
        customerGroups = c_bp_groupDBAdapter.getAllC_BP_Groups();
    }

    public List<String> getListCustomerGroups()
    {
        return this.customerGroups;
    }

    public ArrayList<C_BPartner> getListCustomers()
    {
        return this.customers;
    }

    /**
     * Called when an item has been selected
     *
     * @param id the selected quote ID
     */
    @Override
    public void onItemSelected(int id) {
        C_BPartner customer = customers.get(id);
        Intent intent = new Intent();
        intent.putExtra("customer", customer);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setupDetailFragment() {
        C_BPartner customer = customers.get(0);

        Bundle arguments = new Bundle();
        arguments.putLong(CustomerDetailFragment.ARG_ITEM_ID, customer.getC_BPartner_ID());
        arguments.putString(CustomerDetailFragment.ARG_ITEM_NAME, customer.getName());
        arguments.putString(CustomerDetailFragment.ARG_ITEM_ADDRESS, customer.getAddress());
        arguments.putString(CustomerDetailFragment.ARG_ITEM_POSTAL, customer.getPostal());
        arguments.putString(CustomerDetailFragment.ARG_ITEM_TEL, customer.getPhone());

        CustomerDetailFragment fragment =  CustomerDetailFragment.newInstance(arguments);
        getFragmentManager().beginTransaction().replace(R.id.article_detail_container, fragment).commit();
    }

    /**
     * Enables the functionality that selected items are automatically highlighted.
     */
    private void enableActiveItemState() {
        CustomerListFragment fragmentById = (CustomerListFragment) getFragmentManager().findFragmentById(R.id.article_list);
        fragmentById.getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_actions, menu);

        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) searchViewItem.getActionView();
        searchView.setQueryHint("Search for customers...");
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String query) {
                if (TextUtils.isEmpty(query)) {
                    C_BP_Group c_bp_group;
                    c_bp_group = c_bp_groupDBAdapter.getC_BP_Group(customerGroups.get(0));
                    customers = c_bPartnerDBAdapter.getAllC_BPartnersByGroupID(c_bp_group.getC_BP_Group_ID());
                }
                else {
                    customers = c_bPartnerDBAdapter.getAllC_BPartnersBySearch(query);
                }
                Fragment f = getFragmentManager().findFragmentById(R.id.article_list);
                if (f instanceof CustomerListFragment)
                    ((CustomerListFragment) f).refreshData();

                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                if (TextUtils.isEmpty(query)) {
                    C_BP_Group c_bp_group;
                    c_bp_group = c_bp_groupDBAdapter.getC_BP_Group(customerGroups.get(0));
                    customers = c_bPartnerDBAdapter.getAllC_BPartnersByGroupID(c_bp_group.getC_BP_Group_ID());
                }
                else {
                    customers = c_bPartnerDBAdapter.getAllC_BPartnersBySearch(query);
                }
                Fragment f = getFragmentManager().findFragmentById(R.id.article_list);
                if (f instanceof CustomerListFragment)
                    ((CustomerListFragment) f).refreshData();

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
        return R.id.nav_customer;
    }

    @Override
    public boolean providesActivityToolbar() {
        return true;
    }
}
