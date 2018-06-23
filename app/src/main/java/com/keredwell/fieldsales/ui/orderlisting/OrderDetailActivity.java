package com.keredwell.fieldsales.ui.orderlisting;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.keredwell.fieldsales.R;
import com.keredwell.fieldsales.data.C_BPartner;
import com.keredwell.fieldsales.data.C_Order;
import com.keredwell.fieldsales.data.C_OrderLine;
import com.keredwell.fieldsales.data.C_Tax;
import com.keredwell.fieldsales.dbhelper.C_BPartnerDBAdapter;
import com.keredwell.fieldsales.dbhelper.C_OrderLineDBAdapter;
import com.keredwell.fieldsales.dbhelper.C_TaxDBAdapter;
import com.keredwell.fieldsales.ui.base.BaseActivity;
import com.keredwell.fieldsales.util.LogUtil;
import com.keredwell.fieldsales.util.PrintUtil;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class OrderDetailActivity extends BaseActivity implements OrderDetailFragment.Callback {
    private static final String TAG = makeLogTag(OrderDetailActivity.class);

    private boolean twoPaneMode;

    private OrderPrintTask mOrderPrintTask = null;
    private View mProgressView;
    private View mOrderFormView;

    private C_OrderLineDBAdapter orderLineDBAdapter;
    private C_TaxDBAdapter taxDBAdapter;
    private C_BPartnerDBAdapter bPartnerDBAdapter;

    private ArrayList<C_Order> mItem = new ArrayList<>();
    private int position = 0;
    private C_Order c_order = new C_Order();
    private ArrayList<C_OrderLine> c_orderItems = new ArrayList<>();
    private Boolean isCash = false;
    private C_Tax c_tax = new C_Tax();
    private C_BPartner c_bPartner = new C_BPartner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        orderLineDBAdapter = new C_OrderLineDBAdapter(this);
        taxDBAdapter = new C_TaxDBAdapter(this);
        bPartnerDBAdapter = new C_BPartnerDBAdapter(this);

        mItem = (ArrayList<C_Order>)getIntent().getSerializableExtra("mItem");
        position = (int)getIntent().getSerializableExtra("position");

        setContentView(R.layout.activity_orderlisting_detail);

        setupToolbar();

        updateTotalView();

        mOrderFormView = findViewById(R.id.order_main);
        mProgressView = findViewById(R.id.order_progress);
    }

    private void refreshData(){
        c_order = mItem.get(position);
        c_orderItems = orderLineDBAdapter.getAllC_OrderLinesByOrderId(mItem.get(position).getID());
        isCash = mItem.get(position).getIsCash();
        c_tax = taxDBAdapter.getC_Tax(c_orderItems.get(0).getC_Tax_ID());
        c_bPartner = bPartnerDBAdapter.getC_BPartner(mItem.get(position).getC_BPartner_ID());
    }

    private void attemptPrint() {
        if (mOrderPrintTask != null) {
            return;
        }

        boolean cancel = false;
        View focusView = null;

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            mOrderPrintTask = new OrderPrintTask();
            mOrderPrintTask.execute((Void) null);
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mOrderFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mOrderFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mOrderFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mOrderFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class OrderPrintTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                return PrintUtil.printOrder(c_order, c_orderItems, isCash, c_tax, c_bPartner);
            } catch (Exception e) {
                LogUtil.logE(TAG, e.getMessage(), e);
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mOrderPrintTask = null;
            showProgress(false);

            AlertDialog alertDialog = new AlertDialog.Builder(OrderDetailActivity.this).create();
            alertDialog.setTitle(R.string.printer_order);

            if (success) {
                alertDialog.setMessage(getApplicationContext().getString(R.string.succeeded));
            } else {
                alertDialog.setMessage(getApplicationContext().getString(R.string.failed));
            }
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getApplicationContext().getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(OrderDetailActivity.this, OrderListActivity.class);
                        startActivity(intent);
                    }
                });
            alertDialog.show();
        }

        @Override
        protected void onCancelled() {
            mOrderPrintTask = null;
            showProgress(false);
        }
    }

    private void updateTotalView()
    {
        TextView vCustomerName = (TextView) findViewById(R.id.customername);
        TextView vCustomerAddress = (TextView) findViewById(R.id.customeraddress);
        TextView vCustomerPostal = (TextView) findViewById(R.id.customerpostal);
        TextView vCashOrCredit = (TextView) findViewById(R.id.cashorcredit);

        vCustomerName.setText(c_bPartner.getName());
        vCustomerAddress.setText(c_bPartner.getAddress());
        vCustomerPostal.setText(c_bPartner.getPostal());
        if (isCash) {
            vCashOrCredit.setText(R.string.cash_or_credit_cash);
        } else
            vCashOrCredit.setText(R.string.cash_or_credit_credit);

        TextView vSubtotal = (TextView) findViewById(R.id.subtotal);
        TextView vtaxtype = (TextView) findViewById(R.id.taxtype);
        TextView vTax = (TextView) findViewById(R.id.tax);
        TextView vTotal = (TextView) findViewById(R.id.total);

        vSubtotal.setText(String.format( "%.2f", BigDecimal.valueOf(c_order.getTotalLines()).movePointLeft(2)));
        vtaxtype.setText(c_tax.getName());
        vTax.setText(String.format( "%.2f", BigDecimal.valueOf(c_order.getGrandTotal() -  c_order.getTotalLines()).movePointLeft(2)));
        vTotal.setText(String.format( "%.2f", BigDecimal.valueOf(c_order.getGrandTotal()).movePointLeft(2)));
    }

    public ArrayList<C_OrderLine> getListOrderItems()
    {
        refreshData();

        return this.c_orderItems;
    }

    /**
     * Called when an item has been selected
     *
     * @param id the selected quote ID
     */
    @Override
    public void onItemSelected(int id) {
        /*Customer customer = customers.get(id);
        if (twoPaneMode) {
            // Show the detail information by replacing the DetailFragment via transaction.

            Bundle arguments = new Bundle();
            arguments.putLong(CustomerDetailFragment.ARG_ITEM_ID, customer.getID());
            arguments.putString(CustomerDetailFragment.ARG_ITEM_NAME, customer.getName());
            arguments.putString(CustomerDetailFragment.ARG_ITEM_ADDRESS, customer.getAddress());
            arguments.putString(CustomerDetailFragment.ARG_ITEM_TEL, customer.getTel());

            CustomerDetailFragment fragment = CustomerDetailFragment.newInstance(arguments);
            getFragmentManager().beginTransaction().replace(R.id.article_detail_container, fragment).commit();
        } else {
            // Start the detail activity in single pane mode.

            Bundle arguments = new Bundle();
            arguments.putLong(CustomerDetailFragment.ARG_ITEM_ID, customer.getID());
            arguments.putString(CustomerDetailFragment.ARG_ITEM_NAME, customer.getName());
            arguments.putString(CustomerDetailFragment.ARG_ITEM_ADDRESS, customer.getAddress());
            arguments.putString(CustomerDetailFragment.ARG_ITEM_TEL, customer.getTel());

            Intent detailIntent = new Intent(this, CustomerDetailActivity.class);
            detailIntent.putExtras(arguments);
            startActivity(detailIntent);
        }*/
    }

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    /*private void setupDetailFragment() {
        Customer customer = customers.get(0);

        Bundle arguments = new Bundle();
        arguments.putLong(CustomerDetailFragment.ARG_ITEM_ID, customer.getID());
        arguments.putString(CustomerDetailFragment.ARG_ITEM_NAME, customer.getName());
        arguments.putString(CustomerDetailFragment.ARG_ITEM_ADDRESS, customer.getAddress());
        arguments.putString(CustomerDetailFragment.ARG_ITEM_TEL, customer.getTel());

        CustomerDetailFragment fragment =  CustomerDetailFragment.newInstance(arguments);
        getFragmentManager().beginTransaction().replace(R.id.article_detail_container, fragment).commit();
    }*/

    /**
     * Enables the functionality that selected items are automatically highlighted.
     */
    private void enableActiveItemState() {
        OrderDetailFragment fragmentById = (OrderDetailFragment) getFragmentManager().findFragmentById(R.id.article_list);
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
        getMenuInflater().inflate(R.menu.print_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                openDrawer();
                return true;
            case R.id.action_print:
                attemptPrint();
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
