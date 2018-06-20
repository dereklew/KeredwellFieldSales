package com.keredwell.fieldsales.ui.product;

import android.os.Bundle;

import com.keredwell.fieldsales.ui.base.BaseActivity;
import com.keredwell.fieldsales.R;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Simple wrapper for {@link ProductDetailFragment}
 * This wrapper is only used in single pan mode (= on smartphones)
 * Created by Andreas Schrade on 14.12.2015.
 */
public class ProductDetailActivity extends BaseActivity {
    private static final String TAG = makeLogTag(ProductDetailActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Show the Up button in the action bar.
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Bundle arguments = new Bundle();
        arguments = getIntent().getExtras();

        ProductDetailFragment fragment =  ProductDetailFragment.newInstance(arguments);
        getFragmentManager().beginTransaction().replace(R.id.article_detail_container, fragment).commit();
    }

    @Override
    public boolean providesActivityToolbar() {
        return false;
    }
}
