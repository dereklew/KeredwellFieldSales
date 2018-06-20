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
import com.keredwell.fieldsales.dbhelper.C_OrderDBAdapter;
import com.keredwell.fieldsales.util.DateUtil;

import java.util.ArrayList;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Shows a list of all available quotes.
 * <p/>
 * Created by Andreas Schrade on 14.12.2015.
 */
public class OrderListNotSyncFragment extends ListFragment {
    private static final String TAG = makeLogTag(OrderListNotSyncFragment.class);

    private ArrayList<C_Order> mItem = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        refreshData();

        setHasOptionsMenu(true);
    }

    public void refreshData(){
        C_OrderDBAdapter orderDBAdapter = new C_OrderDBAdapter(ApplicationContext.getAppContext());
        mItem = orderDBAdapter.getAllNotSyncOrders();

        setListAdapter(new MyListAdapter());
    }

    public void updateList(String text)
    {
        C_OrderDBAdapter orderDBAdapter = new C_OrderDBAdapter(ApplicationContext.getAppContext());

        if (TextUtils.isEmpty(text)) {
            mItem = orderDBAdapter.getAllNotSyncOrders();
        }
        else
        {
            mItem = orderDBAdapter.getAllNotSyncOrdersBySearch(text);
        }

        setListAdapter(new MyListAdapter());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
        intent.putExtra("mItem", mItem);
        intent.putExtra("position", position);
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

    public OrderListNotSyncFragment() {
    }
}
