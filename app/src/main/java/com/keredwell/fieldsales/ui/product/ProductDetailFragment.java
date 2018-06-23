package com.keredwell.fieldsales.ui.product;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;

import com.keredwell.fieldsales.ApplicationContext;
import com.keredwell.fieldsales.R;
import com.keredwell.fieldsales.data.M_Product;
import com.keredwell.fieldsales.ui.base.BaseActivity;
import com.keredwell.fieldsales.ui.base.BaseFragment;

import java.math.BigDecimal;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class ProductDetailFragment extends BaseFragment {
    private static final String TAG = makeLogTag(ProductDetailFragment.class);

    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_ITEM_NAME = "item_name";
    public static final String ARG_ITEM_UNIT_PRICE = "item_unit_price";

    private M_Product mItem;

    @Bind(R.id.productname)
    TextView productname;

    @Bind(R.id.unitprice)
    TextView unitprice;

    @Bind(R.id.backdrop)
    ImageView backdropImg;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();

        mItem = new M_Product();

        if (arguments != null) {
            mItem.setM_Product_ID(arguments.getInt(ARG_ITEM_ID));
            mItem.setName(arguments.getString(ARG_ITEM_NAME));
            mItem.setPriceStd(arguments.getInt(ARG_ITEM_UNIT_PRICE));
        }

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflateAndBind(inflater, container, R.layout.fragment_product_detail);

        if (!((BaseActivity) getActivity()).providesActivityToolbar()) {
            ((BaseActivity) getActivity()).setToolbar((Toolbar) rootView.findViewById(R.id.toolbar));
        }

        if (mItem != null) {
            loadBackdrop();
            collapsingToolbar.setTitle(ApplicationContext.getAppContext().getString(R.string.title_products_detail));
            productname.setText(mItem.getName());
            unitprice.setText(String.format( "%.2f", BigDecimal.valueOf(mItem.getPriceStd()).movePointLeft(2)));
        }

        return rootView;
    }

    private void loadBackdrop() {
        Glide.with(this).load(R.drawable.background).centerCrop().into(backdropImg);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_actions, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static ProductDetailFragment newInstance(Bundle arguments) {
        ProductDetailFragment fragment = new ProductDetailFragment();

        fragment.setArguments(arguments);
        return fragment;
    }

    public ProductDetailFragment() {}
}
