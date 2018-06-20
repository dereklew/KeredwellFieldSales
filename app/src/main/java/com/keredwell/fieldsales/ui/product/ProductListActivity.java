package com.keredwell.fieldsales.ui.product;

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
import com.keredwell.fieldsales.data.M_Product;
import com.keredwell.fieldsales.data.M_Product_Category;
import com.keredwell.fieldsales.dbhelper.M_ProductDBAdapter;
import com.keredwell.fieldsales.dbhelper.M_Product_CategoryDBAdapter;
import com.keredwell.fieldsales.ui.base.BaseActivity;
import com.keredwell.fieldsales.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Lists all available quotes. This Activity supports a single pane (= smartphones) and a two pane mode (= large screens with >= 600dp width).
 *
 * Created by Andreas Schrade on 14.12.2015.
 */
public class ProductListActivity extends BaseActivity implements ProductListFragment.Callback {
    private static final String TAG = makeLogTag(ProductListActivity.class);

    /**
     * Whether or not the activity is running on a device with a large screen
     */
    private boolean twoPaneMode;

    private List<String> productCategories = new ArrayList<>();
    private ArrayList<M_Product> products = new ArrayList<>();;

    private M_Product_CategoryDBAdapter m_product_categoryDBAdapter;
    private M_ProductDBAdapter m_productDBAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        m_product_categoryDBAdapter = new M_Product_CategoryDBAdapter(this);
        m_productDBAdapter = new M_ProductDBAdapter(this);

        loadProductCategory();
        loadList("", 0);

        setContentView(R.layout.activity_products_list);

        setupToolbar();

        if (isTwoPaneLayoutUsed()) {
            twoPaneMode = true;
            LogUtil.logD("TEST","Two panes mode");
            enableActiveItemState();
        }

        if (savedInstanceState == null && twoPaneMode) {
            setupDetailFragment();
        }
    }

    public void loadList(String cg, int mode){
        try {
            M_Product_Category productCategory;
            if (mode == 0)
                productCategory = m_product_categoryDBAdapter.getM_Product_Category(productCategories.get(0));
            else
                productCategory = m_product_categoryDBAdapter.getM_Product_Category(cg);

            // Spinner Drop down elements
            products = m_productDBAdapter.getAllProductsByCategoryId(productCategory.getM_Product_Category_ID());

            if (mode == 1) {
                Fragment f = getFragmentManager().findFragmentById(R.id.article_list);
                if (f instanceof ProductListFragment)
                    // do something with f
                    ((ProductListFragment) f).refreshData();
            }
        } catch (Exception e) {
            LogUtil.logE(TAG, e.getStackTrace().toString());
        }
    }

    public void loadProductCategory()
    {
        try{
            productCategories = m_product_categoryDBAdapter.getAllProductCategories();
        } catch (Exception e) {
            LogUtil.logE(TAG, e.getStackTrace().toString());
        }
    }

    public List<String> getListProductCategories()
    {
        return this.productCategories;
    }

    public ArrayList<M_Product> getListProducts()
    {
        return this.products;
    }

    /**
     * Called when an item has been selected
     *
     * @param id the selected quote ID
     */
    @Override
    public void onItemSelected(int id) {
        M_Product product = products.get(id);
        if (twoPaneMode) {
            // Show the detail information by replacing the DetailFragment via transaction.

            Bundle arguments = new Bundle();
            arguments.putLong(ProductDetailFragment.ARG_ITEM_ID, product.getM_Product_ID());
            arguments.putString(ProductDetailFragment.ARG_ITEM_NAME, product.getName());
            arguments.putInt(ProductDetailFragment.ARG_ITEM_UNIT_PRICE, product.getPriceStd());

            ProductDetailFragment fragment = ProductDetailFragment.newInstance(arguments);
            getFragmentManager().beginTransaction().replace(R.id.article_detail_container, fragment).commit();
        } else {
            // Start the detail activity in single pane mode.

            Bundle arguments = new Bundle();
            arguments.putLong(ProductDetailFragment.ARG_ITEM_ID, product.getM_Product_ID());
            arguments.putString(ProductDetailFragment.ARG_ITEM_NAME, product.getName());
            arguments.putInt(ProductDetailFragment.ARG_ITEM_UNIT_PRICE, product.getPriceStd());

            Intent detailIntent = new Intent(this, ProductDetailActivity.class);
            detailIntent.putExtras(arguments);
            startActivity(detailIntent);
        }
    }

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setupDetailFragment() {
        try {
            if (products.size()>0) {
                M_Product product = products.get(0);

                Bundle arguments = new Bundle();
                arguments.putLong(ProductDetailFragment.ARG_ITEM_ID, product.getM_Product_ID());
                arguments.putString(ProductDetailFragment.ARG_ITEM_NAME, product.getName());
                arguments.putInt(ProductDetailFragment.ARG_ITEM_UNIT_PRICE, product.getPriceStd());

                ProductDetailFragment fragment = ProductDetailFragment.newInstance(arguments);
                getFragmentManager().beginTransaction().replace(R.id.article_detail_container, fragment).commit();
            }
        } catch (Exception e) {
            LogUtil.logE(TAG, e.getStackTrace().toString());
        }
    }

    /**
     * Enables the functionality that selected items are automatically highlighted.
     */
    private void enableActiveItemState() {
        ProductListFragment fragmentById = (ProductListFragment) getFragmentManager().findFragmentById(R.id.article_list);
        fragmentById.getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
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
        searchView.setQueryHint("Search for products...");
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);// Do not iconify the widget; expand it by default

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String query) {
                // This is your adapter that will be filtered
                //Toast.makeText(getApplicationContext(),"textChanged :"+newText,Toast.LENGTH_LONG).show();
                if (TextUtils.isEmpty(query)) {
                    M_Product_Category productCategory;
                    productCategory = m_product_categoryDBAdapter.getM_Product_Category(productCategories.get(0));

                    products = m_productDBAdapter.getAllProductsByCategoryId(productCategory.getM_Product_Category_ID());
                }
                else {
                    products = m_productDBAdapter.getAllProductsBySearch(query);
                }

                Fragment f = getFragmentManager().findFragmentById(R.id.article_list);
                if (f instanceof ProductListFragment)
                    // do something with f
                    ((ProductListFragment) f).refreshData();

                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                // **Here you can get the value "query" which is entered in the search box.**

                if (TextUtils.isEmpty(query)) {
                    M_Product_Category productCategory;
                    productCategory = m_product_categoryDBAdapter.getM_Product_Category(productCategories.get(0));

                    products = m_productDBAdapter.getAllProductsByCategoryId(productCategory.getM_Product_Category_ID());
                }
                else {
                    products = m_productDBAdapter.getAllProductsBySearch(query);
                }

                Fragment f = getFragmentManager().findFragmentById(R.id.article_list);
                if (f instanceof ProductListFragment)
                    // do something with f
                    ((ProductListFragment) f).refreshData();

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
        return R.id.nav_product;
    }

    @Override
    public boolean providesActivityToolbar() {
        return true;
    }
}
