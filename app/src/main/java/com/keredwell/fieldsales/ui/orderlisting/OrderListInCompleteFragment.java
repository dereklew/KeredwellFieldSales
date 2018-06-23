package com.keredwell.fieldsales.ui.orderlisting;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.keredwell.fieldsales.ApplicationContext;
import com.keredwell.fieldsales.R;
import com.keredwell.fieldsales.data.C_Order;
import com.keredwell.fieldsales.data.C_OrderLine;
import com.keredwell.fieldsales.dbhelper.C_BPartnerDBAdapter;
import com.keredwell.fieldsales.dbhelper.C_OrderDBAdapter;
import com.keredwell.fieldsales.dbhelper.C_OrderLineDBAdapter;
import com.keredwell.fieldsales.dbhelper.C_TaxDBAdapter;
import com.keredwell.fieldsales.util.DateUtil;

import java.util.ArrayList;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class OrderListInCompleteFragment extends ListFragment {
    private static final String TAG = makeLogTag(OrderListInCompleteFragment.class);

    private ArrayList<C_Order> mItem = new ArrayList<>();

    private C_OrderLineDBAdapter orderLineDBAdapter;
    private C_TaxDBAdapter taxDBAdapter;
    private C_BPartnerDBAdapter bPartnerDBAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        orderLineDBAdapter = new C_OrderLineDBAdapter(ApplicationContext.getAppContext());
        taxDBAdapter = new C_TaxDBAdapter(ApplicationContext.getAppContext());
        bPartnerDBAdapter = new C_BPartnerDBAdapter(ApplicationContext.getAppContext());

        refreshData();

        setHasOptionsMenu(true);
    }

    public void refreshData(){
        C_OrderDBAdapter orderDBAdapter = new C_OrderDBAdapter(ApplicationContext.getAppContext());
        mItem = orderDBAdapter.getAllInCompleteOrders();

        setListAdapter(new MyListAdapter());
    }

    public void updateList(String text)
    {
        C_OrderDBAdapter orderDBAdapter = new C_OrderDBAdapter(ApplicationContext.getAppContext());

        if (TextUtils.isEmpty(text)) {
            mItem = orderDBAdapter.getAllInCompleteOrders();
        }
        else
        {
            mItem = orderDBAdapter.getAllInCompleteOrdersBySearch(text);
        }

        setListAdapter(new MyListAdapter());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ArrayList<C_OrderLine> orderItems = orderLineDBAdapter.getAllC_OrderLinesByOrderId(mItem.get(position).getID());

        Intent intent = new Intent(getActivity(), com.keredwell.fieldsales.ui.order.OrderListActivity.class);
        intent.putExtra("c_order", mItem.get(position));
        intent.putExtra("c_orderItems", orderItems);
        intent.putExtra("isCash", mItem.get(position).getIsCash());
        intent.putExtra("mTax", taxDBAdapter.getC_Tax(orderItems.get(0).getC_Tax_ID()));
        intent.putExtra("customer", bPartnerDBAdapter.getC_BPartner(mItem.get(position).getC_BPartner_ID()));
        startActivity(intent);
    }

    /**
     * onAttach(Context) is not called on pre API 23 versions of Android.
     * onAttach(Activity) is deprecated but still necessary on older devices.
     */
    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    /**
     * Deprecated on API 23 but still necessary for pre API 23 devices.
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    /**
     * Called when the fragment attaches to the context
     */
    protected void onAttachToContext(Context context) {

    }

    private class MyListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mItem.size();
        }

        @Override
        public Object getItem(int position) {
            return mItem.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_orderlisting, container, false);
            }

            final C_Order item = (C_Order) getItem(position);
            ((TextView) convertView.findViewById(R.id.itemdatae)).setText(DateUtil.ConvertToString(item.getDateOrdered()));
            ((TextView) convertView.findViewById(R.id.itemcustomername)).setText(item.getCustomerName());

            return convertView;
        }
    }

    public OrderListInCompleteFragment() {
    }
}
