package com.keredwell.fieldsales.webservice;

import com.keredwell.fieldsales.ApplicationContext;
import com.keredwell.fieldsales.data.C_Order;
import com.keredwell.fieldsales.data.C_OrderLine;
import com.keredwell.fieldsales.dbhelper.C_OrderDBAdapter;
import com.keredwell.fieldsales.dbhelper.C_OrderLineDBAdapter;
import com.keredwell.fieldsales.util.LogUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 3/12/2017.
 */

public class SendOrders {
    private static final String TAG = makeLogTag(SendOrders.class);

    public static Boolean sendXml(String mUser, String mPassword)
    {
        try {
            C_OrderDBAdapter odb = new C_OrderDBAdapter(ApplicationContext.getAppContext());
            C_OrderLineDBAdapter oidb = new C_OrderLineDBAdapter(ApplicationContext.getAppContext());

            ArrayList<C_Order> c_orders = odb.getAllNotSyncC_Orders();
            for (Iterator<C_Order> i = c_orders.iterator(); i.hasNext();) {
                C_Order c_order = i.next();
                long c_order_id = C_OrderSendWS.WSEvent(mUser, mPassword, c_order);

                if (c_order_id > 0) {
                    odb.updateC_OrderSyncDate(c_order.getID(), c_order_id, new Date());
                    if (c_order.getDocumentID() == 0)
                    {
                        C_Order_DocumentIDWS.WSEvent(mUser, mPassword, c_order_id);
                    }
                    ArrayList<C_OrderLine> c_orderItems = oidb.getAllC_OrderLinesByOrderId(c_order.getID());
                    for (Iterator<C_OrderLine> j = c_orderItems.iterator(); j.hasNext(); ) {
                        C_OrderLine c_orderItem = j.next();
                        oidb.updateC_OrderLineC_Order_ID(c_orderItem.getID(), c_order_id);
                    }
                }
            }
            ArrayList<C_OrderLine> c_orderItems = oidb.getAllNotSyncC_OrderLines();
            for (Iterator<C_OrderLine> j = c_orderItems.iterator(); j.hasNext(); ) {
                C_OrderLine c_orderItem = j.next();

                long c_orderline_id = C_OrderLineSendWS.WSEvent(mUser, mPassword, c_orderItem);
                if (c_orderline_id > 0)
                {
                    oidb.updateC_OrderLineSyncDate(c_orderItem.getID(), c_orderline_id, new Date());
                }
            }
            c_orders = odb.getAllNotProcessC_Orders();
            for (Iterator<C_Order> i = c_orders.iterator(); i.hasNext();) {
                C_Order c_order = i.next();
                if (c_order.getC_Order_ID() > 0) {
                    String retval = ProcessOrderWS.WSEvent(mUser, mPassword, c_order.getC_Order_ID());
                    if (retval.equals("") == false) {
                        odb.updateProcessC_OrderRetVal(c_order.getID(), retval, new Date(), c_order.getIsCash());
                    }
                }
            }

            return true;
        } catch (Exception e) {
            LogUtil.logE(TAG, e.getMessage(), e);
            return false;
        }
    }
}
