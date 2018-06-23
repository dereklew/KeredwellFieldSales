package com.keredwell.fieldsales.ui.order;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.keredwell.fieldsales.ApplicationContext;
import com.keredwell.fieldsales.R;
import com.keredwell.fieldsales.data.AD_User;
import com.keredwell.fieldsales.data.C_BPartner;
import com.keredwell.fieldsales.data.C_Tax;
import com.keredwell.fieldsales.data.M_Product;
import com.keredwell.fieldsales.data.C_Order;
import com.keredwell.fieldsales.data.C_OrderLine;
import com.keredwell.fieldsales.dbhelper.AD_UserDBAdapter;
import com.keredwell.fieldsales.dbhelper.AD_User_RolesDBAdapter;
import com.keredwell.fieldsales.dbhelper.M_LocatorDBAdapter;
import com.keredwell.fieldsales.ui.base.BaseActivity;
import com.keredwell.fieldsales.util.PropUtil;
import com.keredwell.fieldsales.util.SharedPrefUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class OrderListActivity extends BaseActivity implements OrderListFragment.Callback {
    private static final String TAG = makeLogTag(OrderListActivity.class);

    private C_Order c_order = new C_Order();
    private ArrayList<C_OrderLine> c_orderLines = new ArrayList<>();
    private C_Tax c_tax = new C_Tax();
    private C_BPartner c_bPartner = new C_BPartner();
    private AD_User ad_user = new AD_User();
    private boolean isCash;

    private M_LocatorDBAdapter m_locatorDBAdapter;
    private AD_User_RolesDBAdapter ad_user_rolesDBAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        m_locatorDBAdapter = new M_LocatorDBAdapter(this);
        ad_user_rolesDBAdapter = new AD_User_RolesDBAdapter(this);

        if (getIntent().getSerializableExtra("c_order") != null) {
            c_order = (C_Order) getIntent().getSerializableExtra("c_order");
            c_orderLines = (ArrayList<C_OrderLine>) getIntent().getSerializableExtra("c_orderItems");
            isCash = (Boolean) getIntent().getSerializableExtra("isCash");
            c_tax = (C_Tax) getIntent().getSerializableExtra("mTax");
            c_bPartner = (C_BPartner) getIntent().getSerializableExtra("customer");
        }

        setContentView(R.layout.activity_orders_list);
        setupToolbar();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderListActivity.this, ProductListActivity.class);
                intent.putExtra("orderitem", c_orderLines);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                if (data != null) {
                    M_Product product = (M_Product) data.getSerializableExtra("product");
                    C_OrderLine orderItem = new C_OrderLine();
                    orderItem.setM_Product_ID(product.getM_Product_ID());

                    if (product.getM_Locator_ID()  == 0) {
                        orderItem.setM_Warehouse_ID(m_locatorDBAdapter.getM_Locator().getM_Warehouse_ID());
                        orderItem.setM_Locator_ID(m_locatorDBAdapter.getM_Locator().getM_Locator_ID());
                    }
                    else {
                        orderItem.setM_Warehouse_ID(m_locatorDBAdapter.getM_Locator(product.getM_Locator_ID()).getM_Warehouse_ID());
                        orderItem.setM_Locator_ID(m_locatorDBAdapter.getM_Locator(product.getM_Locator_ID()).getM_Locator_ID());
                    }

                    orderItem.setC_Uom_ID(product.getC_Uom_ID());
                    orderItem.setProductName(product.getName());
                    orderItem.setQtyOrdered(1);
                    orderItem.setM_Pricelist_ID(product.getM_ProductList_ID());
                    orderItem.setPriceList(product.getPriceList());
                    orderItem.setPriceActual(product.getPriceStd());
                    orderItem.setPriceLimit(product.getPriceLimit());
                    orderItem.setLineNetAmt(product.getPriceStd());

                    c_orderLines.add(orderItem);

                    updateTotalView();

                    Fragment f = getFragmentManager().findFragmentById(R.id.article_list);
                    if (f instanceof OrderListFragment) {
                        ((OrderListFragment) f).refreshData();
                    }
                }
            }
        }
        else if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                if (data != null) {
                    c_bPartner = (C_BPartner) data.getSerializableExtra("customer");

                    CashOrCreditDialogFragment dialog = new CashOrCreditDialogFragment();
                    dialog.show(getFragmentManager(), "CashOrCreditDialogFragment");

                    String mUserID = SharedPrefUtil.getPersistedData(ApplicationContext.USERID, null);
                    if (ad_user_rolesDBAdapter.isAdmin(Long.parseLong(mUserID)))
                    {
                        c_order.setSalesRep_ID(Long.parseLong(mUserID));
                        Intent intent = new Intent(OrderListActivity.this, SalesRepListActivity.class);
                         startActivityForResult(intent, 3);
                    }
                }
            }
        }
        else if (requestCode == 3) {
            if(resultCode == Activity.RESULT_OK){
                if (data != null) {
                    ad_user = (AD_User) data.getSerializableExtra("ad_user");

                    c_order.setSalesRep_ID(ad_user.getAD_User_ID());
                }
            }
        }
    }

    public void onCashOrCreditSelection(boolean isCash) {
        this.isCash = isCash;

        updateOrder();
        //resetData();
    }

    private void updateOrder()
    {
        c_order.setC_BPartner_ID(c_bPartner.getC_BPartner_ID());
        c_order.setCustomerName(c_bPartner.getName());
        c_order.setC_BPartner_Location_ID(c_bPartner.getC_BPartner_Location_ID());
        c_order.setC_Bill_BPartner_ID(c_bPartner.getC_BPartner_ID());
        c_order.setC_Bill_Location_ID(c_bPartner.getC_BPartner_Location_ID());

        c_order.setDateOrdered(new Date());
        c_order.setIsCash(isCash);

        if (isCash)
        {
            c_order.setPaymentRule("B");
            c_order.setC_PaymentTerm_ID(Long.parseLong(PropUtil.getProperty("c_paymentterm_id_cash")));
            c_order.setC_DocTypeTarget_ID(Long.parseLong(PropUtil.getProperty("c_doctypetarget_id_cash")));
        }
        else {
            c_order.setPaymentRule("P");
            c_order.setC_PaymentTerm_ID(Long.parseLong(PropUtil.getProperty("c_paymentterm_id_credit")));
            c_order.setC_DocTypeTarget_ID(Long.parseLong(PropUtil.getProperty("c_doctypetarget_id_credit")));
        }

        ArrayList<C_OrderLine> c_orderItems = new ArrayList<>();

        BigDecimal subtotal = new BigDecimal(0);
        int lineNo = 0;
        Iterator<C_OrderLine> orderItemIterator = c_orderLines.iterator();
        while(orderItemIterator.hasNext()) {
            C_OrderLine nextOrderItem = orderItemIterator.next();
            C_OrderLine c_orderItem = new C_OrderLine();
            lineNo = lineNo + 10;
            c_orderItem.setLineNo(lineNo);
            if (nextOrderItem.getID() != 0)
                c_orderItem.setID(nextOrderItem.getID());
            if (nextOrderItem.getOrderID() != 0)
                c_orderItem.setOrderID(nextOrderItem.getOrderID());
            if (nextOrderItem.getC_Order_ID() != 0)
                c_orderItem.setC_Order_ID(nextOrderItem.getC_Order_ID());
            if (nextOrderItem.getC_OrderLine_ID() != 0)
                c_orderItem.setC_OrderLine_ID(nextOrderItem.getC_OrderLine_ID());
            c_orderItem.setC_BPartner_ID(c_bPartner.getC_BPartner_ID());
            c_orderItem.setC_BPartner_Location_ID(c_bPartner.getC_BPartner_Location_ID());
            c_orderItem.setDateOrdered(c_order.getDateOrdered());
            c_orderItem.setM_Product_ID(nextOrderItem.getM_Product_ID());
            c_orderItem.setProductName(nextOrderItem.getProductName());
            c_orderItem.setM_Warehouse_ID(nextOrderItem.getM_Warehouse_ID());
            c_orderItem.setM_Locator_ID(nextOrderItem.getM_Locator_ID());
            c_orderItem.setC_Uom_ID(nextOrderItem.getC_Uom_ID());
            c_orderItem.setQtyOrdered(nextOrderItem.getQtyOrdered());
            c_orderItem.setM_Pricelist_ID(nextOrderItem.getM_Pricelist_ID());
            c_orderItem.setPriceList(nextOrderItem.getPriceList());
            c_orderItem.setPriceActual(nextOrderItem.getPriceActual());
            c_orderItem.setPriceLimit(nextOrderItem.getPriceLimit());
            c_orderItem.setLineNetAmt(nextOrderItem.getLineNetAmt());
            c_orderItem.setDiscount((((nextOrderItem.getPriceList() - nextOrderItem.getPriceActual())/nextOrderItem.getPriceList())*100));
            c_orderItem.setC_Tax_ID(c_tax.getC_Tax_ID());

            subtotal = subtotal.add(BigDecimal.valueOf(nextOrderItem.getLineNetAmt()).movePointLeft(2));
            c_orderItems.add(c_orderItem);
        }

        BigDecimal multiplier = BigDecimal.valueOf(c_tax.getRate()).divide(new BigDecimal(10000), 12, BigDecimal.ROUND_HALF_UP);

        BigDecimal tax = subtotal.multiply(multiplier).setScale(2, BigDecimal.ROUND_HALF_UP);

        BigDecimal total = subtotal.add(tax);

        c_order.setM_Warehouse_ID(c_orderItems.get(0).getM_Warehouse_ID());
        c_order.setM_Pricelist_ID(c_orderItems.get(0).getM_Pricelist_ID());
        c_order.setTotalLines(subtotal.multiply(new BigDecimal(100).setScale(2, BigDecimal.ROUND_HALF_UP)).intValueExact());
        c_order.setGrandTotal(total.multiply(new BigDecimal(100).setScale(2, BigDecimal.ROUND_HALF_UP)).intValueExact());

        Intent intent = new Intent(this, OrderCheckoutActivity.class);
        intent.putExtra("c_order", c_order);
        intent.putExtra("c_orderItems", c_orderItems);
        intent.putExtra("isCash", isCash);
        intent.putExtra("mTax", c_tax);
        intent.putExtra("customer", c_bPartner);
        startActivity(intent);
    }

    private void resetData(){
        c_orderLines.clear();
        c_tax = new C_Tax();
        c_bPartner = null;

        TextView vSubtotal = (TextView) findViewById(R.id.subtotal);
        TextView vTax = (TextView) findViewById(R.id.tax);
        TextView vTotal = (TextView) findViewById(R.id.total);

        vSubtotal.setText("");
        vTax.setText("");
        vTotal.setText("");

        Fragment f = getFragmentManager().findFragmentById(R.id.article_list);
        if (f instanceof OrderListFragment) {
            ((OrderListFragment) f).refreshData();
        }
    }

    private void updateTotalView()
    {
        BigDecimal subtotal = new BigDecimal(0);
        Iterator<C_OrderLine> orderItemIterator = c_orderLines.iterator();
        while(orderItemIterator.hasNext()) {
            C_OrderLine nextOrderItem = orderItemIterator.next();
            subtotal = subtotal.add(BigDecimal.valueOf(nextOrderItem.getLineNetAmt()).movePointLeft(2));
        }

        BigDecimal multiplier = BigDecimal.valueOf(c_tax.getRate()).divide(new BigDecimal(10000), 12, BigDecimal.ROUND_HALF_UP);

        BigDecimal tax = subtotal.multiply(multiplier).setScale(2, BigDecimal.ROUND_HALF_UP);

        BigDecimal total = subtotal.add(tax);

        TextView vSubtotal = (TextView) findViewById(R.id.subtotal);
        TextView vTax = (TextView) findViewById(R.id.tax);
        TextView vTotal = (TextView) findViewById(R.id.total);

        vSubtotal.setText(String.format( "%.2f", subtotal));
        vTax.setText(String.format( "%.2f", tax));
        vTotal.setText(String.format( "%.2f", total));
    }

    public ArrayList<C_OrderLine> getListOrderItems()
    {
        return this.c_orderLines;
    }

    public void setTax(C_Tax tax)
    {
        c_tax = tax;
        updateTotalView();
    }

    public void updateListOrderItems(ArrayList<C_OrderLine> newOrderItems)
    {
        this.c_orderLines = newOrderItems;

        updateTotalView();

        Fragment f = getFragmentManager().findFragmentById(R.id.article_list);
        if (f instanceof OrderListFragment) {
            // do something with f
            ((OrderListFragment) f).refreshData();
        }
    }

    /**
     * Called when an item has been selected
     *
     * @param id the selected quote ID
     */
    @Override
    public void onItemSelected(int id) {

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
        OrderListFragment fragmentById = (OrderListFragment) getFragmentManager().findFragmentById(R.id.article_list);
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
        getMenuInflater().inflate(R.menu.order_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                openDrawer();
                return true;
            case R.id.action_checkout:
                if (c_orderLines.size() > 0) {
                    if (c_bPartner.getC_BPartner_ID() == 0) {
                        Intent intent = new Intent(this, CustomerListActivity.class);
                        startActivityForResult(intent, 2);
                    }
                    else
                    {
                        updateOrder();
                    }
                }
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
