package com.keredwell.fieldsales.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.keredwell.fieldsales.util.DateUtil;
import com.keredwell.fieldsales.data.C_Order;

import java.util.ArrayList;
import java.util.Date;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 18/8/2017.
 */

public class C_OrderDBAdapter extends DBAdapter {
    private static final String TAG = makeLogTag(C_OrderDBAdapter.class);

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_C_ORDER_ID = "_c_order_id";
    public static final String COLUMN_C_DOCTYPE_ID = "_c_doctype_id";
    public static final String COLUMN_DOCTYPETARGET_ID = "_c_doctypetarget_id";
    public static final String COLUMN_SALESREP_ID = "_salesrep_id";
    public static final String COLUMN_DATEORDERED = "_dateordered";
    public static final String COLUMN_C_BPARTNER_ID = "_c_bpartner_id";
    public static final String COLUMN_CUSTOMERNAME = "_customername";
    public static final String COLUMN_C_BPARTNER_LOCATION_ID = "_c_bpartner_location_id";
    public static final String COLUMN_C_BILL_BPARTNER_ID = "_c_bill_bpartner_id";
    public static final String COLUMN_C_BILL_LOCATION_ID = "_c_bill_location_id";
    public static final String COLUMN_TOTALLINES = "_totallines";
    public static final String COLUMN_GRANDTOTAL = "_grandtotal";
    public static final String COLUMN_M_WAREHOUSE_ID = "_m_warehouse_id";
    public static final String COLUMN_M_PRICELIST_ID = "_m_pricelist_id";
    public static final String COLUMN_PAYMENTRULE = "_paymentrule";
    public static final String COLUMN_C_PAYMENTTERM_ID = "_c_paymenttern_id";
    public static final String COLUMN_ISCASH = "_iscash";
    public static final String COLUMN_PROCESSORDER_RETVAL = "_processOrder_Retval";
    public static final String COLUMN_SYNCDATE = "_syncdate";
    public static final String COLUMN_SYNCDATE_PROCESSORDER = "_syncdateProcessOrder";
    public static final String COLUMN_M_INOUT_ID = "_m_inout_id";
    public static final String COLUMN_C_INVOICE_ID = "_c_invoice_id";
    public static final String COLUMN_INTERNAL_STATUS = "_internal_status";
    public static final String COLUMN_DOCUMENTID = "_documentid";

    public static final String DATABASE_TABLE = "c_order";

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx
     *            the Context within which to work
     */
    public C_OrderDBAdapter(Context ctx) {
        super(ctx);
    }

    /**
     * Create a new order. If the order is successfully created return the
     * id for that order, otherwise return a -1 to indicate failure.
     *
     * @param order
     * @return id or -1 if failed
     */
    public long createC_Order(C_Order order){
        open();

        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_C_DOCTYPE_ID, order.getC_DocType_ID());
        initialValues.put(COLUMN_DOCTYPETARGET_ID, order.getC_DocTypeTarget_ID());
        initialValues.put(COLUMN_SALESREP_ID, order.getSalesRep_ID());
        initialValues.put(COLUMN_DATEORDERED, DateUtil.persistDate(order.getDateOrdered()));
        initialValues.put(COLUMN_C_BPARTNER_ID, order.getC_BPartner_ID());
        initialValues.put(COLUMN_CUSTOMERNAME, order.getCustomerName());
        initialValues.put(COLUMN_C_BPARTNER_LOCATION_ID, order.getC_BPartner_Location_ID());
        initialValues.put(COLUMN_C_BILL_BPARTNER_ID, order.getC_Bill_BPartner_ID());
        initialValues.put(COLUMN_C_BILL_LOCATION_ID, order.getC_Bill_Location_ID());
        initialValues.put(COLUMN_TOTALLINES, order.getTotalLines());
        initialValues.put(COLUMN_GRANDTOTAL, order.getGrandTotal());
        initialValues.put(COLUMN_M_WAREHOUSE_ID, order.getM_Warehouse_ID());
        initialValues.put(COLUMN_M_PRICELIST_ID, order.getM_Pricelist_ID());
        initialValues.put(COLUMN_PAYMENTRULE, order.getPaymentRule());
        initialValues.put(COLUMN_C_PAYMENTTERM_ID, order.getC_PaymentTerm_ID());
        initialValues.put(COLUMN_ISCASH, order.getIsCash());
        initialValues.put(COLUMN_INTERNAL_STATUS, order.getInternal_Status());

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Create a new order. If the order is successfully created return the
     * id for that order, otherwise return a -1 to indicate failure.
     *
     * @param order
     * @return id or -1 if failed
     */
    public long createC_OrderWithC_Order_ID(C_Order order){
        open();

        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_C_ORDER_ID, order.getC_Order_ID());
        initialValues.put(COLUMN_C_DOCTYPE_ID, order.getC_DocType_ID());
        initialValues.put(COLUMN_DOCTYPETARGET_ID, order.getC_DocTypeTarget_ID());
        initialValues.put(COLUMN_SALESREP_ID, order.getSalesRep_ID());
        initialValues.put(COLUMN_DATEORDERED, DateUtil.persistDate(order.getDateOrdered()));
        initialValues.put(COLUMN_C_BPARTNER_ID, order.getC_BPartner_ID());
        initialValues.put(COLUMN_CUSTOMERNAME, order.getCustomerName());
        initialValues.put(COLUMN_C_BPARTNER_LOCATION_ID, order.getC_BPartner_Location_ID());
        initialValues.put(COLUMN_C_BILL_BPARTNER_ID, order.getC_Bill_BPartner_ID());
        initialValues.put(COLUMN_C_BILL_LOCATION_ID, order.getC_Bill_Location_ID());
        initialValues.put(COLUMN_TOTALLINES, order.getTotalLines());
        initialValues.put(COLUMN_GRANDTOTAL, order.getGrandTotal());
        initialValues.put(COLUMN_M_WAREHOUSE_ID, order.getM_Warehouse_ID());
        initialValues.put(COLUMN_M_PRICELIST_ID, order.getM_Pricelist_ID());
        initialValues.put(COLUMN_PAYMENTRULE, order.getPaymentRule());
        initialValues.put(COLUMN_C_PAYMENTTERM_ID, order.getC_PaymentTerm_ID());
        initialValues.put(COLUMN_ISCASH, order.getIsCash());
        initialValues.put(COLUMN_INTERNAL_STATUS, order.getInternal_Status());
        initialValues.put(COLUMN_DOCUMENTID, order.getDocumentID());

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the car with the given id
     *
     * @param id
     * @return true if deleted, false otherwise
     */
    public boolean deleteC_Order(long id) {
        open();

        return mDb.delete(DATABASE_TABLE, COLUMN_ID + "=" + id, null) > 0; //$NON-NLS-1$
    }

    /**
     * Return a Cursor over the list of all orders in the database
     *
     * @return Cursor over all orders
     */
    public ArrayList<C_Order> getAllInCompleteOrders() {
        open();

        ArrayList<C_Order> orders = new ArrayList<>();

        Cursor mCursor =

                mDb.query(DATABASE_TABLE, new String[] { COLUMN_ID, COLUMN_C_ORDER_ID, COLUMN_C_DOCTYPE_ID, COLUMN_DOCTYPETARGET_ID,
                                COLUMN_SALESREP_ID, COLUMN_DATEORDERED, COLUMN_C_BPARTNER_ID, COLUMN_CUSTOMERNAME, COLUMN_C_BPARTNER_LOCATION_ID,
                                COLUMN_C_BILL_BPARTNER_ID, COLUMN_C_BILL_LOCATION_ID,
                                COLUMN_TOTALLINES, COLUMN_GRANDTOTAL, COLUMN_M_WAREHOUSE_ID, COLUMN_M_PRICELIST_ID,
                                COLUMN_PAYMENTRULE, COLUMN_C_PAYMENTTERM_ID, COLUMN_ISCASH, COLUMN_SYNCDATE, COLUMN_INTERNAL_STATUS, COLUMN_DOCUMENTID },
                        COLUMN_INTERNAL_STATUS +"="+ C_Order.STATUS_INCOMPLETE, null, null, null, null);

        while (mCursor.moveToNext()){
            C_Order order = new C_Order();
            order.setID(Long.parseLong(mCursor.getString(0)));
            if (mCursor.getString(1) != null)
                order.setC_Order_ID(Long.parseLong(mCursor.getString(1)));
            order.setC_DocType_ID(Integer.parseInt(mCursor.getString(2)));
            order.setC_DocTypeTarget_ID(Integer.parseInt(mCursor.getString(3)));
            order.setSalesRep_ID(Long.parseLong(mCursor.getString(4)));
            order.setDateOrdered(DateUtil.loadDate(mCursor, 5));
            order.setC_BPartner_ID(Long.parseLong(mCursor.getString(6)));
            order.setCustomerName(mCursor.getString(7));
            order.setC_BPartner_Location_ID(Long.parseLong(mCursor.getString(8)));
            order.setC_Bill_BPartner_ID(Long.parseLong(mCursor.getString(9)));
            order.setC_Bill_Location_ID(Long.parseLong(mCursor.getString(10)));
            order.setTotalLines(Integer.parseInt(mCursor.getString(11)));
            order.setGrandTotal(Integer.parseInt(mCursor.getString(12)));
            order.setM_Warehouse_ID(Long.parseLong(mCursor.getString(13)));
            order.setM_Pricelist_ID(Long.parseLong(mCursor.getString(14)));
            order.setPaymentRule(mCursor.getString(15));
            order.setC_PaymentTerm_ID(Long.parseLong(mCursor.getString(16)));
            order.setIsCash("1".equals(mCursor.getString(17)));
            order.setSyncDate(DateUtil.loadDate(mCursor,18));
            order.setInternal_Status(Integer.parseInt(mCursor.getString(19)));
            if (mCursor.getString(20) != null)
                order.setDocumentID(Long.parseLong(mCursor.getString(20)));
            orders.add(order);
        }
        close();
        return orders;
    }

    /**
     * Return a Cursor over the list of all orders in the database
     *
     * @param searchterm
     * @return Cursor over all orders
     */
    public ArrayList<C_Order> getAllInCompleteOrdersBySearch(String searchterm) {
        open();

        ArrayList<C_Order> orders = new ArrayList<>();

        Cursor mCursor =

                mDb.query(DATABASE_TABLE, new String[] { COLUMN_ID, COLUMN_C_ORDER_ID, COLUMN_C_DOCTYPE_ID, COLUMN_DOCTYPETARGET_ID,
                                COLUMN_SALESREP_ID, COLUMN_DATEORDERED, COLUMN_C_BPARTNER_ID, COLUMN_CUSTOMERNAME, COLUMN_C_BPARTNER_LOCATION_ID,
                                COLUMN_C_BILL_BPARTNER_ID, COLUMN_C_BILL_LOCATION_ID,
                                COLUMN_TOTALLINES, COLUMN_GRANDTOTAL, COLUMN_M_WAREHOUSE_ID, COLUMN_M_PRICELIST_ID,
                                COLUMN_PAYMENTRULE, COLUMN_C_PAYMENTTERM_ID, COLUMN_ISCASH, COLUMN_SYNCDATE, COLUMN_INTERNAL_STATUS, COLUMN_DOCUMENTID },
                        COLUMN_CUSTOMERNAME + " LIKE '%" + searchterm + "%' AND " +
                                COLUMN_INTERNAL_STATUS + "=" + C_Order.STATUS_INCOMPLETE, null, null, null, null);

        while (mCursor.moveToNext()){
            C_Order order = new C_Order();
            order.setID(Long.parseLong(mCursor.getString(0)));
            if (mCursor.getString(1) != null)
                order.setC_Order_ID(Long.parseLong(mCursor.getString(1)));
            order.setC_DocType_ID(Integer.parseInt(mCursor.getString(2)));
            order.setC_DocTypeTarget_ID(Integer.parseInt(mCursor.getString(3)));
            order.setSalesRep_ID(Long.parseLong(mCursor.getString(4)));
            order.setDateOrdered(DateUtil.loadDate(mCursor, 5));
            order.setC_BPartner_ID(Long.parseLong(mCursor.getString(6)));
            order.setCustomerName(mCursor.getString(7));
            order.setC_BPartner_Location_ID(Long.parseLong(mCursor.getString(8)));
            order.setC_Bill_BPartner_ID(Long.parseLong(mCursor.getString(9)));
            order.setC_Bill_Location_ID(Long.parseLong(mCursor.getString(10)));
            order.setTotalLines(Integer.parseInt(mCursor.getString(11)));
            order.setGrandTotal(Integer.parseInt(mCursor.getString(12)));
            order.setM_Warehouse_ID(Long.parseLong(mCursor.getString(13)));
            order.setM_Pricelist_ID(Long.parseLong(mCursor.getString(14)));
            order.setPaymentRule(mCursor.getString(15));
            order.setC_PaymentTerm_ID(Long.parseLong(mCursor.getString(16)));
            order.setIsCash("1".equals(mCursor.getString(17)));
            order.setSyncDate(DateUtil.loadDate(mCursor,18));
            order.setInternal_Status(Integer.parseInt(mCursor.getString(19)));
            if (mCursor.getString(20) != null)
                order.setDocumentID(Long.parseLong(mCursor.getString(20)));
            orders.add(order);
        }
        close();
        return orders;
    }

    /**
     * Return a Cursor over the list of all orders in the database
     *
     * @return Cursor over all orders
     */
    public ArrayList<C_Order> getAllCompletedOrders() {
        open();

        ArrayList<C_Order> orders = new ArrayList<>();

        Cursor mCursor =

                mDb.query(DATABASE_TABLE, new String[] { COLUMN_ID, COLUMN_C_ORDER_ID, COLUMN_C_DOCTYPE_ID, COLUMN_DOCTYPETARGET_ID,
                                COLUMN_SALESREP_ID, COLUMN_DATEORDERED, COLUMN_C_BPARTNER_ID, COLUMN_CUSTOMERNAME, COLUMN_C_BPARTNER_LOCATION_ID,
                                COLUMN_C_BILL_BPARTNER_ID, COLUMN_C_BILL_LOCATION_ID,
                                COLUMN_TOTALLINES, COLUMN_GRANDTOTAL, COLUMN_M_WAREHOUSE_ID, COLUMN_M_PRICELIST_ID,
                                COLUMN_PAYMENTRULE, COLUMN_C_PAYMENTTERM_ID, COLUMN_ISCASH, COLUMN_SYNCDATE, COLUMN_INTERNAL_STATUS, COLUMN_DOCUMENTID },
                        COLUMN_INTERNAL_STATUS +" in ("+ C_Order.STATUS_SYNCED + ", " + C_Order.STATUS_PROCESSED + ")",
                        null, null, null, null);

        while (mCursor.moveToNext()){
            C_Order order = new C_Order();
            order.setID(Long.parseLong(mCursor.getString(0)));
            if (mCursor.getString(1) != null)
                order.setC_Order_ID(Long.parseLong(mCursor.getString(1)));
            order.setC_DocType_ID(Integer.parseInt(mCursor.getString(2)));
            order.setC_DocTypeTarget_ID(Integer.parseInt(mCursor.getString(3)));
            order.setSalesRep_ID(Long.parseLong(mCursor.getString(4)));
            order.setDateOrdered(DateUtil.loadDate(mCursor, 5));
            order.setC_BPartner_ID(Long.parseLong(mCursor.getString(6)));
            order.setCustomerName(mCursor.getString(7));
            order.setC_BPartner_Location_ID(Long.parseLong(mCursor.getString(8)));
            order.setC_Bill_BPartner_ID(Long.parseLong(mCursor.getString(9)));
            order.setC_Bill_Location_ID(Long.parseLong(mCursor.getString(10)));
            order.setTotalLines(Integer.parseInt(mCursor.getString(11)));
            order.setGrandTotal(Integer.parseInt(mCursor.getString(12)));
            order.setM_Warehouse_ID(Long.parseLong(mCursor.getString(13)));
            order.setM_Pricelist_ID(Long.parseLong(mCursor.getString(14)));
            order.setPaymentRule(mCursor.getString(15));
            order.setC_PaymentTerm_ID(Long.parseLong(mCursor.getString(16)));
            order.setIsCash("1".equals(mCursor.getString(17)));
            order.setSyncDate(DateUtil.loadDate(mCursor,18));
            order.setInternal_Status(Integer.parseInt(mCursor.getString(19)));
            if (mCursor.getString(20) != null)
                order.setDocumentID(Long.parseLong(mCursor.getString(20)));
            orders.add(order);
        }
        close();
        return orders;
    }

    /**
     * Return a Cursor over the list of all orders in the database
     *
     * @param searchterm
     * @return Cursor over all orders
     */
    public ArrayList<C_Order> getAllCompletedOrdersBySearch(String searchterm) {
        open();

        ArrayList<C_Order> orders = new ArrayList<>();

        Cursor mCursor =

                mDb.query(DATABASE_TABLE, new String[] { COLUMN_ID, COLUMN_C_ORDER_ID, COLUMN_C_DOCTYPE_ID, COLUMN_DOCTYPETARGET_ID,
                                COLUMN_SALESREP_ID, COLUMN_DATEORDERED, COLUMN_C_BPARTNER_ID, COLUMN_CUSTOMERNAME, COLUMN_C_BPARTNER_LOCATION_ID,
                                COLUMN_C_BILL_BPARTNER_ID, COLUMN_C_BILL_LOCATION_ID,
                                COLUMN_TOTALLINES, COLUMN_GRANDTOTAL, COLUMN_M_WAREHOUSE_ID, COLUMN_M_PRICELIST_ID,
                                COLUMN_PAYMENTRULE, COLUMN_C_PAYMENTTERM_ID, COLUMN_ISCASH, COLUMN_SYNCDATE, COLUMN_INTERNAL_STATUS, COLUMN_DOCUMENTID },
                        COLUMN_CUSTOMERNAME + " LIKE '%" + searchterm + "%' AND " + COLUMN_INTERNAL_STATUS +
                                " IN (" + C_Order.STATUS_SYNCED + ", " + C_Order.STATUS_PROCESSED + ")",
                        null, null, null, null);

        while (mCursor.moveToNext()){
            C_Order order = new C_Order();
            order.setID(Long.parseLong(mCursor.getString(0)));
            if (mCursor.getString(1) != null)
                order.setC_Order_ID(Long.parseLong(mCursor.getString(1)));
            order.setC_DocType_ID(Integer.parseInt(mCursor.getString(2)));
            order.setC_DocTypeTarget_ID(Integer.parseInt(mCursor.getString(3)));
            order.setSalesRep_ID(Long.parseLong(mCursor.getString(4)));
            order.setDateOrdered(DateUtil.loadDate(mCursor, 5));
            order.setC_BPartner_ID(Long.parseLong(mCursor.getString(6)));
            order.setCustomerName(mCursor.getString(7));
            order.setC_BPartner_Location_ID(Long.parseLong(mCursor.getString(8)));
            order.setC_Bill_BPartner_ID(Long.parseLong(mCursor.getString(9)));
            order.setC_Bill_Location_ID(Long.parseLong(mCursor.getString(10)));
            order.setTotalLines(Integer.parseInt(mCursor.getString(11)));
            order.setGrandTotal(Integer.parseInt(mCursor.getString(12)));
            order.setM_Warehouse_ID(Long.parseLong(mCursor.getString(13)));
            order.setM_Pricelist_ID(Long.parseLong(mCursor.getString(14)));
            order.setPaymentRule(mCursor.getString(15));
            order.setC_PaymentTerm_ID(Long.parseLong(mCursor.getString(16)));
            order.setIsCash("1".equals(mCursor.getString(17)));
            order.setSyncDate(DateUtil.loadDate(mCursor,18));
            order.setInternal_Status(Integer.parseInt(mCursor.getString(19)));
            if (mCursor.getString(20) != null)
                order.setDocumentID(Long.parseLong(mCursor.getString(20)));
            orders.add(order);
        }
        close();
        return orders;
    }

    /**
     * Return a Cursor over the list of all orders in the database
     *
     * @return Cursor over all orders
     */
    public ArrayList<C_Order> getAllNotSyncOrders() {
        open();

        ArrayList<C_Order> orders = new ArrayList<>();

        Cursor mCursor =

                mDb.query(DATABASE_TABLE, new String[] { COLUMN_ID, COLUMN_C_ORDER_ID, COLUMN_C_DOCTYPE_ID, COLUMN_DOCTYPETARGET_ID,
                                COLUMN_SALESREP_ID, COLUMN_DATEORDERED, COLUMN_C_BPARTNER_ID, COLUMN_CUSTOMERNAME, COLUMN_C_BPARTNER_LOCATION_ID,
                                COLUMN_C_BILL_BPARTNER_ID, COLUMN_C_BILL_LOCATION_ID,
                                COLUMN_TOTALLINES, COLUMN_GRANDTOTAL, COLUMN_M_WAREHOUSE_ID, COLUMN_M_PRICELIST_ID,
                                COLUMN_PAYMENTRULE, COLUMN_C_PAYMENTTERM_ID, COLUMN_ISCASH, COLUMN_SYNCDATE, COLUMN_INTERNAL_STATUS, COLUMN_DOCUMENTID },
                        COLUMN_INTERNAL_STATUS +" in ("+ C_Order.STATUS_COMPLETED + ")",
                        null, null, null, null);

        while (mCursor.moveToNext()){
            C_Order order = new C_Order();
            order.setID(Long.parseLong(mCursor.getString(0)));
            if (mCursor.getString(1) != null)
                order.setC_Order_ID(Long.parseLong(mCursor.getString(1)));
            order.setC_DocType_ID(Integer.parseInt(mCursor.getString(2)));
            order.setC_DocTypeTarget_ID(Integer.parseInt(mCursor.getString(3)));
            order.setSalesRep_ID(Long.parseLong(mCursor.getString(4)));
            order.setDateOrdered(DateUtil.loadDate(mCursor, 5));
            order.setC_BPartner_ID(Long.parseLong(mCursor.getString(6)));
            order.setCustomerName(mCursor.getString(7));
            order.setC_BPartner_Location_ID(Long.parseLong(mCursor.getString(8)));
            order.setC_Bill_BPartner_ID(Long.parseLong(mCursor.getString(9)));
            order.setC_Bill_Location_ID(Long.parseLong(mCursor.getString(10)));
            order.setTotalLines(Integer.parseInt(mCursor.getString(11)));
            order.setGrandTotal(Integer.parseInt(mCursor.getString(12)));
            order.setM_Warehouse_ID(Long.parseLong(mCursor.getString(13)));
            order.setM_Pricelist_ID(Long.parseLong(mCursor.getString(14)));
            order.setPaymentRule(mCursor.getString(15));
            order.setC_PaymentTerm_ID(Long.parseLong(mCursor.getString(16)));
            order.setIsCash("1".equals(mCursor.getString(17)));
            order.setSyncDate(DateUtil.loadDate(mCursor,18));
            order.setInternal_Status(Integer.parseInt(mCursor.getString(19)));
            if (mCursor.getString(20) != null)
                order.setDocumentID(Long.parseLong(mCursor.getString(20)));
            orders.add(order);
        }
        close();
        return orders;
    }

    /**
     * Return a Cursor over the list of all orders in the database
     *
     * @param searchterm
     * @return Cursor over all orders
     */
    public ArrayList<C_Order> getAllNotSyncOrdersBySearch(String searchterm) {
        open();

        ArrayList<C_Order> orders = new ArrayList<>();

        Cursor mCursor =

                mDb.query(DATABASE_TABLE, new String[] { COLUMN_ID, COLUMN_C_ORDER_ID, COLUMN_C_DOCTYPE_ID, COLUMN_DOCTYPETARGET_ID,
                                COLUMN_SALESREP_ID, COLUMN_DATEORDERED, COLUMN_C_BPARTNER_ID, COLUMN_CUSTOMERNAME, COLUMN_C_BPARTNER_LOCATION_ID,
                                COLUMN_C_BILL_BPARTNER_ID, COLUMN_C_BILL_LOCATION_ID,
                                COLUMN_TOTALLINES, COLUMN_GRANDTOTAL, COLUMN_M_WAREHOUSE_ID, COLUMN_M_PRICELIST_ID,
                                COLUMN_PAYMENTRULE, COLUMN_C_PAYMENTTERM_ID, COLUMN_ISCASH, COLUMN_SYNCDATE, COLUMN_INTERNAL_STATUS, COLUMN_DOCUMENTID },
                        COLUMN_CUSTOMERNAME + " LIKE '%" + searchterm + "%' AND " + COLUMN_INTERNAL_STATUS +
                                " IN (" + C_Order.STATUS_COMPLETED + ")",
                        null, null, null, null);

        while (mCursor.moveToNext()){
            C_Order order = new C_Order();
            order.setID(Long.parseLong(mCursor.getString(0)));
            if (mCursor.getString(1) != null)
                order.setC_Order_ID(Long.parseLong(mCursor.getString(1)));
            order.setC_DocType_ID(Integer.parseInt(mCursor.getString(2)));
            order.setC_DocTypeTarget_ID(Integer.parseInt(mCursor.getString(3)));
            order.setSalesRep_ID(Long.parseLong(mCursor.getString(4)));
            order.setDateOrdered(DateUtil.loadDate(mCursor, 5));
            order.setC_BPartner_ID(Long.parseLong(mCursor.getString(6)));
            order.setCustomerName(mCursor.getString(7));
            order.setC_BPartner_Location_ID(Long.parseLong(mCursor.getString(8)));
            order.setC_Bill_BPartner_ID(Long.parseLong(mCursor.getString(9)));
            order.setC_Bill_Location_ID(Long.parseLong(mCursor.getString(10)));
            order.setTotalLines(Integer.parseInt(mCursor.getString(11)));
            order.setGrandTotal(Integer.parseInt(mCursor.getString(12)));
            order.setM_Warehouse_ID(Long.parseLong(mCursor.getString(13)));
            order.setM_Pricelist_ID(Long.parseLong(mCursor.getString(14)));
            order.setPaymentRule(mCursor.getString(15));
            order.setC_PaymentTerm_ID(Long.parseLong(mCursor.getString(16)));
            order.setIsCash("1".equals(mCursor.getString(17)));
            order.setSyncDate(DateUtil.loadDate(mCursor,18));
            order.setInternal_Status(Integer.parseInt(mCursor.getString(19)));
            if (mCursor.getString(20) != null)
                order.setDocumentID(Long.parseLong(mCursor.getString(20)));
            orders.add(order);
        }
        close();
        return orders;
    }

    /**
     * Return a Cursor over the list of all orders in the database
     *
     * @return Cursor over all orders
     */
    public ArrayList<C_Order> getAllNotSyncC_Orders() {
        open();

        ArrayList<C_Order> orders = new ArrayList<>();

        Cursor mCursor =

                mDb.query(DATABASE_TABLE, new String[] { COLUMN_ID, COLUMN_C_ORDER_ID, COLUMN_C_DOCTYPE_ID, COLUMN_DOCTYPETARGET_ID,
                                COLUMN_SALESREP_ID, COLUMN_DATEORDERED, COLUMN_C_BPARTNER_ID, COLUMN_CUSTOMERNAME, COLUMN_C_BPARTNER_LOCATION_ID,
                                COLUMN_C_BILL_BPARTNER_ID, COLUMN_C_BILL_LOCATION_ID,
                                COLUMN_TOTALLINES, COLUMN_GRANDTOTAL, COLUMN_M_WAREHOUSE_ID, COLUMN_M_PRICELIST_ID,
                                COLUMN_PAYMENTRULE, COLUMN_C_PAYMENTTERM_ID, COLUMN_ISCASH, COLUMN_SYNCDATE, COLUMN_INTERNAL_STATUS, COLUMN_DOCUMENTID },
                        COLUMN_SYNCDATE + " IS NULL AND " + COLUMN_INTERNAL_STATUS +"="+ C_Order.STATUS_COMPLETED,
                        null, null, null, COLUMN_ID);

        while (mCursor.moveToNext()){
            C_Order order = new C_Order();
            order.setID(Long.parseLong(mCursor.getString(0)));
            if (mCursor.getString(1) != null)
                order.setC_Order_ID(Long.parseLong(mCursor.getString(1)));
            order.setC_DocType_ID(Integer.parseInt(mCursor.getString(2)));
            order.setC_DocTypeTarget_ID(Integer.parseInt(mCursor.getString(3)));
            order.setSalesRep_ID(Long.parseLong(mCursor.getString(4)));
            order.setDateOrdered(DateUtil.loadDate(mCursor, 5));
            order.setC_BPartner_ID(Long.parseLong(mCursor.getString(6)));
            order.setCustomerName(mCursor.getString(7));
            order.setC_BPartner_Location_ID(Long.parseLong(mCursor.getString(8)));
            order.setC_Bill_BPartner_ID(Long.parseLong(mCursor.getString(9)));
            order.setC_Bill_Location_ID(Long.parseLong(mCursor.getString(10)));
            order.setTotalLines(Integer.parseInt(mCursor.getString(11)));
            order.setGrandTotal(Integer.parseInt(mCursor.getString(12)));
            order.setM_Warehouse_ID(Long.parseLong(mCursor.getString(13)));
            order.setM_Pricelist_ID(Long.parseLong(mCursor.getString(14)));
            order.setPaymentRule(mCursor.getString(15));
            order.setC_PaymentTerm_ID(Long.parseLong(mCursor.getString(16)));
            order.setIsCash("1".equals(mCursor.getString(17)));
            order.setSyncDate(DateUtil.loadDate(mCursor,18));
            order.setInternal_Status(Integer.parseInt(mCursor.getString(19)));
            if (mCursor.getString(20) != null)
                order.setDocumentID(Long.parseLong(mCursor.getString(20)));
            orders.add(order);
        }
        close();
        return orders;
    }

    /**
     * Return a Cursor over the list of all orders in the database
     *
     * @return Cursor over all orders
     */
    public ArrayList<C_Order> getAllNotProcessC_Orders() {
        open();

        ArrayList<C_Order> orders = new ArrayList<>();

        Cursor mCursor =

                mDb.query(DATABASE_TABLE, new String[] { COLUMN_ID, COLUMN_C_ORDER_ID, COLUMN_C_DOCTYPE_ID, COLUMN_DOCTYPETARGET_ID,
                                COLUMN_SALESREP_ID, COLUMN_DATEORDERED, COLUMN_C_BPARTNER_ID, COLUMN_CUSTOMERNAME, COLUMN_C_BPARTNER_LOCATION_ID,
                                COLUMN_C_BILL_BPARTNER_ID, COLUMN_C_BILL_LOCATION_ID,
                                COLUMN_TOTALLINES, COLUMN_GRANDTOTAL, COLUMN_M_WAREHOUSE_ID, COLUMN_M_PRICELIST_ID,
                                COLUMN_PAYMENTRULE, COLUMN_C_PAYMENTTERM_ID, COLUMN_ISCASH, COLUMN_SYNCDATE, COLUMN_INTERNAL_STATUS, COLUMN_DOCUMENTID },
                        COLUMN_SYNCDATE_PROCESSORDER + " IS NULL AND " + COLUMN_INTERNAL_STATUS +"="+ C_Order.STATUS_SYNCED,
                        null, null, null, COLUMN_ID);

        while (mCursor.moveToNext()){
            C_Order order = new C_Order();
            order.setID(Long.parseLong(mCursor.getString(0)));
            if (mCursor.getString(1) != null)
                order.setC_Order_ID(Long.parseLong(mCursor.getString(1)));
            order.setC_DocType_ID(Integer.parseInt(mCursor.getString(2)));
            order.setC_DocTypeTarget_ID(Integer.parseInt(mCursor.getString(3)));
            order.setSalesRep_ID(Long.parseLong(mCursor.getString(4)));
            order.setDateOrdered(DateUtil.loadDate(mCursor, 5));
            order.setC_BPartner_ID(Long.parseLong(mCursor.getString(6)));
            order.setCustomerName(mCursor.getString(7));
            order.setC_BPartner_Location_ID(Long.parseLong(mCursor.getString(8)));
            order.setC_Bill_BPartner_ID(Long.parseLong(mCursor.getString(9)));
            order.setC_Bill_Location_ID(Long.parseLong(mCursor.getString(10)));
            order.setTotalLines(Integer.parseInt(mCursor.getString(11)));
            order.setGrandTotal(Integer.parseInt(mCursor.getString(12)));
            order.setM_Warehouse_ID(Long.parseLong(mCursor.getString(13)));
            order.setM_Pricelist_ID(Long.parseLong(mCursor.getString(14)));
            order.setPaymentRule(mCursor.getString(15));
            order.setC_PaymentTerm_ID(Long.parseLong(mCursor.getString(16)));
            order.setIsCash("1".equals(mCursor.getString(17)));
            order.setSyncDate(DateUtil.loadDate(mCursor,18));
            order.setInternal_Status(Integer.parseInt(mCursor.getString(19)));
            if (mCursor.getString(20) != null)
                order.setDocumentID(Long.parseLong(mCursor.getString(20)));
            orders.add(order);
        }
        close();
        return orders;
    }

    /**
     * Return a Order positioned at the order that matches the given id
     * @param id
     * @return Order positioned to matching order, if found, else null
     */
    public C_Order getC_Order(long id) {
        open();

        C_Order order = new C_Order();

        Cursor mCursor =

                mDb.query(DATABASE_TABLE, new String[] { COLUMN_ID, COLUMN_C_ORDER_ID, COLUMN_C_DOCTYPE_ID, COLUMN_DOCTYPETARGET_ID,
                                COLUMN_SALESREP_ID, COLUMN_DATEORDERED, COLUMN_C_BPARTNER_ID, COLUMN_CUSTOMERNAME, COLUMN_C_BPARTNER_LOCATION_ID,
                                COLUMN_C_BILL_BPARTNER_ID, COLUMN_C_BILL_LOCATION_ID,
                                COLUMN_TOTALLINES, COLUMN_GRANDTOTAL, COLUMN_M_WAREHOUSE_ID, COLUMN_M_PRICELIST_ID,
                                COLUMN_PAYMENTRULE, COLUMN_C_PAYMENTTERM_ID, COLUMN_ISCASH, COLUMN_SYNCDATE, COLUMN_INTERNAL_STATUS, COLUMN_DOCUMENTID },
                        COLUMN_ID + "=" + id, null, null, null, null, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            order.setID(Long.parseLong(mCursor.getString(0)));
            if (mCursor.getString(1) != null)
                order.setC_Order_ID(Long.parseLong(mCursor.getString(1)));
            order.setC_DocType_ID(Integer.parseInt(mCursor.getString(2)));
            order.setC_DocTypeTarget_ID(Integer.parseInt(mCursor.getString(3)));
            order.setSalesRep_ID(Long.parseLong(mCursor.getString(4)));
            order.setDateOrdered(DateUtil.loadDate(mCursor, 5));
            order.setC_BPartner_ID(Long.parseLong(mCursor.getString(6)));
            order.setCustomerName(mCursor.getString(7));
            order.setC_BPartner_Location_ID(Long.parseLong(mCursor.getString(8)));
            order.setC_Bill_BPartner_ID(Long.parseLong(mCursor.getString(9)));
            order.setC_Bill_Location_ID(Long.parseLong(mCursor.getString(10)));
            order.setTotalLines(Integer.parseInt(mCursor.getString(11)));
            order.setGrandTotal(Integer.parseInt(mCursor.getString(12)));
            order.setM_Warehouse_ID(Long.parseLong(mCursor.getString(13)));
            order.setM_Pricelist_ID(Long.parseLong(mCursor.getString(14)));
            order.setPaymentRule(mCursor.getString(15));
            order.setC_PaymentTerm_ID(Long.parseLong(mCursor.getString(16)));
            order.setIsCash("1".equals(mCursor.getString(17)));
            order.setSyncDate(DateUtil.loadDate(mCursor,18));
            order.setInternal_Status(Integer.parseInt(mCursor.getString(19)));
            if (mCursor.getString(20) != null)
                order.setDocumentID(Long.parseLong(mCursor.getString(20)));
        }
        else {
            close();
            return null;
        }
        close();
        return order;
    }

    /**
     * Return a Order positioned at the order that matches the given id
     * @param c_order_id
     * @return Order positioned to matching order, if found, else null
     */
    public C_Order getC_OrderByC_Order_ID(long c_order_id) {
        open();

        C_Order order = new C_Order();

        Cursor mCursor =

                mDb.query(DATABASE_TABLE, new String[] { COLUMN_ID, COLUMN_C_ORDER_ID, COLUMN_C_DOCTYPE_ID, COLUMN_DOCTYPETARGET_ID,
                                COLUMN_SALESREP_ID, COLUMN_DATEORDERED, COLUMN_C_BPARTNER_ID, COLUMN_CUSTOMERNAME, COLUMN_C_BPARTNER_LOCATION_ID,
                                COLUMN_C_BILL_BPARTNER_ID, COLUMN_C_BILL_LOCATION_ID,
                                COLUMN_TOTALLINES, COLUMN_GRANDTOTAL, COLUMN_M_WAREHOUSE_ID, COLUMN_M_PRICELIST_ID,
                                COLUMN_PAYMENTRULE, COLUMN_C_PAYMENTTERM_ID, COLUMN_ISCASH, COLUMN_SYNCDATE, COLUMN_INTERNAL_STATUS, COLUMN_DOCUMENTID },
                        COLUMN_C_ORDER_ID + "=" + c_order_id, null, null, null, null, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            order.setID(Long.parseLong(mCursor.getString(0)));
            if (mCursor.getString(1) != null)
                order.setC_Order_ID(Long.parseLong(mCursor.getString(1)));
            order.setC_DocType_ID(Integer.parseInt(mCursor.getString(2)));
            order.setC_DocTypeTarget_ID(Integer.parseInt(mCursor.getString(3)));
            order.setSalesRep_ID(Long.parseLong(mCursor.getString(4)));
            order.setDateOrdered(DateUtil.loadDate(mCursor, 5));
            order.setC_BPartner_ID(Long.parseLong(mCursor.getString(6)));
            order.setCustomerName(mCursor.getString(7));
            order.setC_BPartner_Location_ID(Long.parseLong(mCursor.getString(8)));
            order.setC_Bill_BPartner_ID(Long.parseLong(mCursor.getString(9)));
            order.setC_Bill_Location_ID(Long.parseLong(mCursor.getString(10)));
            order.setTotalLines(Integer.parseInt(mCursor.getString(11)));
            order.setGrandTotal(Integer.parseInt(mCursor.getString(12)));
            order.setM_Warehouse_ID(Long.parseLong(mCursor.getString(13)));
            order.setM_Pricelist_ID(Long.parseLong(mCursor.getString(14)));
            order.setPaymentRule(mCursor.getString(15));
            order.setC_PaymentTerm_ID(Long.parseLong(mCursor.getString(16)));
            order.setIsCash("1".equals(mCursor.getString(17)));
            order.setSyncDate(DateUtil.loadDate(mCursor,18));
            order.setInternal_Status(Integer.parseInt(mCursor.getString(19)));
            if (mCursor.getString(20) != null)
                order.setDocumentID(Long.parseLong(mCursor.getString(20)));
        }
        else {
            close();
            return null;
        }
        close();
        return order;
    }

    /**
     * Update the order.
     *
     * @param order
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateC_Order(C_Order order){
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_C_DOCTYPE_ID, order.getC_DocType_ID());
        args.put(COLUMN_DOCTYPETARGET_ID, order.getC_DocTypeTarget_ID());
        args.put(COLUMN_SALESREP_ID, order.getSalesRep_ID());
        args.put(COLUMN_DATEORDERED, DateUtil.persistDate(order.getDateOrdered()));
        args.put(COLUMN_C_BPARTNER_ID, order.getC_BPartner_ID());
        args.put(COLUMN_CUSTOMERNAME, order.getCustomerName());
        args.put(COLUMN_C_BPARTNER_LOCATION_ID, order.getC_BPartner_Location_ID());
        args.put(COLUMN_C_BILL_BPARTNER_ID, order.getC_Bill_BPartner_ID());
        args.put(COLUMN_C_BILL_LOCATION_ID, order.getC_Bill_Location_ID());
        args.put(COLUMN_TOTALLINES, order.getTotalLines());
        args.put(COLUMN_GRANDTOTAL, order.getGrandTotal());
        args.put(COLUMN_M_WAREHOUSE_ID, order.getM_Warehouse_ID());
        args.put(COLUMN_M_PRICELIST_ID, order.getM_Pricelist_ID());
        args.put(COLUMN_PAYMENTRULE, order.getPaymentRule());
        args.put(COLUMN_C_PAYMENTTERM_ID, order.getC_PaymentTerm_ID());
        args.put(COLUMN_ISCASH, order.getIsCash());
        args.put(COLUMN_INTERNAL_STATUS, order.getInternal_Status());
        args.put(COLUMN_DOCUMENTID, order.getDocumentID());

        return mDb.update(DATABASE_TABLE, args, COLUMN_ID + "=" + order.getID(), null) >0;
    }

    /**
     * Update the order.
     *
     * @param order
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateC_OrderWithC_Order_ID(C_Order order){
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_C_DOCTYPE_ID, order.getC_DocType_ID());
        args.put(COLUMN_DOCTYPETARGET_ID, order.getC_DocTypeTarget_ID());
        args.put(COLUMN_SALESREP_ID, order.getSalesRep_ID());
        args.put(COLUMN_DATEORDERED, DateUtil.persistDate(order.getDateOrdered()));
        args.put(COLUMN_C_BPARTNER_ID, order.getC_BPartner_ID());
        args.put(COLUMN_CUSTOMERNAME, order.getCustomerName());
        args.put(COLUMN_C_BPARTNER_LOCATION_ID, order.getC_BPartner_Location_ID());
        args.put(COLUMN_C_BILL_BPARTNER_ID, order.getC_Bill_BPartner_ID());
        args.put(COLUMN_C_BILL_LOCATION_ID, order.getC_Bill_Location_ID());
        args.put(COLUMN_TOTALLINES, order.getTotalLines());
        args.put(COLUMN_GRANDTOTAL, order.getGrandTotal());
        args.put(COLUMN_M_WAREHOUSE_ID, order.getM_Warehouse_ID());
        args.put(COLUMN_M_PRICELIST_ID, order.getM_Pricelist_ID());
        args.put(COLUMN_PAYMENTRULE, order.getPaymentRule());
        args.put(COLUMN_C_PAYMENTTERM_ID, order.getC_PaymentTerm_ID());
        args.put(COLUMN_ISCASH, order.getIsCash());
        args.put(COLUMN_INTERNAL_STATUS, order.getInternal_Status());
        args.put(COLUMN_DOCUMENTID, order.getDocumentID());

        return mDb.update(DATABASE_TABLE, args, COLUMN_C_ORDER_ID + "=" + order.getC_Order_ID(), null) >0;
    }

    /**
     * Update the synchronization date of the order.
     *
     * @param id
     * @param c_order_id
     * @param syncdate
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateC_OrderSyncDate(long id, long c_order_id, Date syncdate){
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_C_ORDER_ID, c_order_id);
        args.put(COLUMN_SYNCDATE, DateUtil.persistDate(syncdate));
        args.put(COLUMN_INTERNAL_STATUS, C_Order.STATUS_SYNCED);

        return mDb.update(DATABASE_TABLE, args, COLUMN_ID + "=" + id, null) >0;
    }

    /**
     * Update the synchronization date of the order.
     *
     * @param id
     * @param processOrder_RetVal
     * @param syncdate
     * @param isCash
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateProcessC_OrderRetVal(long id, String processOrder_RetVal, Date syncdate, Boolean isCash) {
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_PROCESSORDER_RETVAL, processOrder_RetVal);
        args.put(COLUMN_SYNCDATE_PROCESSORDER, DateUtil.persistDate(syncdate));
        args.put(COLUMN_INTERNAL_STATUS, C_Order.STATUS_PROCESSED);

        if (isCash) {
            String[] retval = processOrder_RetVal.split(" - ");

            if (retval != null) {
                String[] m_inout_id = retval[0].split(":");
                if (m_inout_id != null) {
                    args.put(COLUMN_M_INOUT_ID, m_inout_id[1].trim());
                }
                String[] c_invoice_id = retval[1].split(":");
                if (c_invoice_id != null) {
                    args.put(COLUMN_C_INVOICE_ID, c_invoice_id[1].trim());
                }
            }
        }
        return mDb.update(DATABASE_TABLE, args, COLUMN_ID + "=" + id, null) >0;
    }
}
