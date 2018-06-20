package com.keredwell.fieldsales.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.keredwell.fieldsales.data.C_OrderLine;
import com.keredwell.fieldsales.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 18/8/2017.
 */

public class C_OrderLineDBAdapter extends DBAdapter {
    private static final String TAG = makeLogTag(C_OrderLineDBAdapter.class);

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ORDERID = "_orderid";
    public static final String COLUMN_C_ORDER_ID = "_c_order_id";
    public static final String COLUMN_C_ORDERLINE_ID = "_c_orderline_id";
    public static final String COLUMN_LINENO = "_lineno";
    public static final String COLUMN_C_BPARTNER_ID = "_c_bpartner_id";
    public static final String COLUMN_C_BPARTNER_LOCATION_ID = "_c_bpartner_location_id";
    public static final String COLUMN_DATEORDERED = "_dateordered";
    public static final String COLUMN_M_PRODUCT_ID = "_m_product_id";
    public static final String COLUMN_PRODUCTNAME = "_productname";
    public static final String COLUMN_M_WAREHOUSE_ID = "_m_warehouse_id";
    public static final String COLUMN_M_LOCATOR_ID = "_m_locator_id";
    public static final String COLUMN_C_UOM_ID = "_c_uom_id";
    public static final String COLUMN_QTYORDERED = "_qtyordered";
    public static final String COLUMN_M_PRICELIST_ID = "_m_pricelist_id";
    public static final String COLUMN_PRICELIST = "_pricelist";
    public static final String COLUMN_PRICEACTUAL = "_priceactual";
    public static final String COLUMN_PRICELIMIT = "_pricelimit";
    public static final String COLUMN_LINENETAMT = "_linenetamt";
    public static final String COLUMN_DISCOUNT = "_discount";
    public static final String COLUMN_C_TAX_ID = "_c_tax_id";
    public static final String COLUMN_SYNCDATE = "_syncdate";

    public static final String DATABASE_TABLE = "c_orderline";

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx
     *            the Context within which to work
     */
    public C_OrderLineDBAdapter(Context ctx) {
        super(ctx);
    }

    /**
     * Create a new order item. If the order item is successfully created return the new
     * id for that order item, otherwise return a -1 to indicate failure.
     *
     * @param orderitem
     * @return rowId or -1 if failed
     */
    public long createC_OrderLine(C_OrderLine orderitem){
        open();

        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_ORDERID, orderitem.getOrderID());
        initialValues.put(COLUMN_LINENO, orderitem.getLineNo());
        initialValues.put(COLUMN_C_BPARTNER_ID, orderitem.getC_BPartner_ID());
        initialValues.put(COLUMN_C_BPARTNER_LOCATION_ID, orderitem.getC_BPartner_Location_ID());
        initialValues.put(COLUMN_DATEORDERED, DateUtil.persistDate(orderitem.getDateOrdered()));
        initialValues.put(COLUMN_M_PRODUCT_ID, orderitem.getM_Product_ID());
        initialValues.put(COLUMN_PRODUCTNAME, orderitem.getProductName());
        initialValues.put(COLUMN_M_WAREHOUSE_ID, orderitem.getM_Warehouse_ID());
        initialValues.put(COLUMN_M_LOCATOR_ID, orderitem.getM_Locator_ID());
        initialValues.put(COLUMN_C_UOM_ID, orderitem.getC_Uom_ID());
        initialValues.put(COLUMN_QTYORDERED, orderitem.getQtyOrdered());
        initialValues.put(COLUMN_M_PRICELIST_ID, orderitem.getM_Pricelist_ID());
        initialValues.put(COLUMN_PRICELIST, orderitem.getPriceList());
        initialValues.put(COLUMN_PRICEACTUAL, orderitem.getPriceActual());
        initialValues.put(COLUMN_PRICELIMIT, orderitem.getPriceLimit());
        initialValues.put(COLUMN_LINENETAMT, orderitem.getLineNetAmt());
        initialValues.put(COLUMN_DISCOUNT, orderitem.getDiscount());
        initialValues.put(COLUMN_C_TAX_ID, orderitem.getC_Tax_ID());

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Create a new order item. If the order item is successfully created return the new
     * id for that order item, otherwise return a -1 to indicate failure.
     *
     * @param orderitem
     * @return rowId or -1 if failed
     */
    public long createC_OrderLineWithC_OrderLine_ID(C_OrderLine orderitem){
        open();

        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_ORDERID, orderitem.getOrderID());
        initialValues.put(COLUMN_C_ORDER_ID, orderitem.getC_Order_ID());
        initialValues.put(COLUMN_C_ORDERLINE_ID, orderitem.getC_OrderLine_ID());
        initialValues.put(COLUMN_LINENO, orderitem.getLineNo());
        initialValues.put(COLUMN_C_BPARTNER_ID, orderitem.getC_BPartner_ID());
        initialValues.put(COLUMN_C_BPARTNER_LOCATION_ID, orderitem.getC_BPartner_Location_ID());
        initialValues.put(COLUMN_DATEORDERED, DateUtil.persistDate(orderitem.getDateOrdered()));
        initialValues.put(COLUMN_M_PRODUCT_ID, orderitem.getM_Product_ID());
        initialValues.put(COLUMN_PRODUCTNAME, orderitem.getProductName());
        initialValues.put(COLUMN_M_WAREHOUSE_ID, orderitem.getM_Warehouse_ID());
        initialValues.put(COLUMN_M_LOCATOR_ID, orderitem.getM_Locator_ID());
        initialValues.put(COLUMN_C_UOM_ID, orderitem.getC_Uom_ID());
        initialValues.put(COLUMN_QTYORDERED, orderitem.getQtyOrdered());
        initialValues.put(COLUMN_M_PRICELIST_ID, orderitem.getM_Pricelist_ID());
        initialValues.put(COLUMN_PRICELIST, orderitem.getPriceList());
        initialValues.put(COLUMN_PRICEACTUAL, orderitem.getPriceActual());
        initialValues.put(COLUMN_PRICELIMIT, orderitem.getPriceLimit());
        initialValues.put(COLUMN_LINENETAMT, orderitem.getLineNetAmt());
        initialValues.put(COLUMN_DISCOUNT, orderitem.getDiscount());
        initialValues.put(COLUMN_C_TAX_ID, orderitem.getC_Tax_ID());

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the order item with the given id
     *
     * @param id
     * @return true if deleted, false otherwise
     */
    public boolean deleteC_OrderLine(long id) {
        open();

        return mDb.delete(DATABASE_TABLE, COLUMN_ID + "= ?", new String[]{Long.toString(id)}) > 0; //$NON-NLS-1$
    }

    /**
     * Return a Cursor over the list of all order items in the database
     *
     * @return Cursor over all order items
     */
    public ArrayList<C_OrderLine> getAllC_OrderLinesByOrderId(long orderid) {

        open();

        ArrayList<C_OrderLine> orderitems = new ArrayList<>();

        Cursor mCursor =

                mDb.query(true, DATABASE_TABLE, new String[] { COLUMN_ID, COLUMN_ORDERID, COLUMN_C_ORDER_ID, COLUMN_C_ORDERLINE_ID, COLUMN_LINENO,
                                COLUMN_C_BPARTNER_ID, COLUMN_C_BPARTNER_LOCATION_ID, COLUMN_DATEORDERED, COLUMN_M_PRODUCT_ID, COLUMN_PRODUCTNAME,
                                COLUMN_M_WAREHOUSE_ID, COLUMN_M_LOCATOR_ID, COLUMN_C_UOM_ID, COLUMN_QTYORDERED, COLUMN_M_PRICELIST_ID, COLUMN_PRICELIST,
                                COLUMN_PRICEACTUAL, COLUMN_PRICELIMIT, COLUMN_LINENETAMT, COLUMN_DISCOUNT, COLUMN_C_TAX_ID, COLUMN_SYNCDATE },
                COLUMN_ORDERID + "= ?",
                        new String[]{Long.toString(orderid)}, null, null, null, null);

        while (mCursor.moveToNext()){
            C_OrderLine orderitem = new C_OrderLine();
            orderitem.setID(Long.parseLong(mCursor.getString(0)));
            orderitem.setOrderID(Long.parseLong(mCursor.getString(1)));
            if (mCursor.getString(2) != null)
                orderitem.setC_Order_ID(Long.parseLong(mCursor.getString(2)));
            if (mCursor.getString(3) != null)
                orderitem.setC_OrderLine_ID(Long.parseLong(mCursor.getString(3)));
            orderitem.setLineNo(Integer.parseInt(mCursor.getString(4)));
            orderitem.setC_BPartner_ID(Long.parseLong(mCursor.getString(5)));
            orderitem.setC_BPartner_Location_ID(Long.parseLong((mCursor.getString(6))));
            orderitem.setDateOrdered(DateUtil.loadDate(mCursor, 7));
            orderitem.setM_Product_ID(Long.parseLong((mCursor.getString(8))));
            orderitem.setProductName(mCursor.getString(9));
            orderitem.setM_Warehouse_ID(Long.parseLong(mCursor.getString(10)));
            orderitem.setM_Locator_ID(Long.parseLong(mCursor.getString(11)));
            orderitem.setC_Uom_ID(Long.parseLong(mCursor.getString(12)));
            orderitem.setQtyOrdered(Integer.parseInt(mCursor.getString(13)));
            orderitem.setM_Pricelist_ID(Long.parseLong(mCursor.getString(14)));
            orderitem.setPriceList(Integer.parseInt(mCursor.getString(15)));
            orderitem.setPriceActual(Integer.parseInt(mCursor.getString(16)));
            orderitem.setPriceLimit(Integer.parseInt(mCursor.getString(17)));
            orderitem.setLineNetAmt(Integer.parseInt(mCursor.getString(18)));
            orderitem.setDiscount(Integer.parseInt(mCursor.getString(19)));
            orderitem.setC_Tax_ID(Long.parseLong(mCursor.getString(20)));
            orderitem.setSyncDate(DateUtil.loadDate(mCursor,21));
            orderitems.add(orderitem);
        }
        close();
        return orderitems;
    }

    /**
     * Return a Cursor over the list of all order items in the database
     *
     * @return Cursor over all order items
     */
    public ArrayList<C_OrderLine> getAllNotSyncC_OrderLines() {

        open();

        ArrayList<C_OrderLine> orderitems = new ArrayList<>();

        Cursor mCursor =

                mDb.query(true, DATABASE_TABLE, new String[] { COLUMN_ID, COLUMN_ORDERID, COLUMN_C_ORDER_ID, COLUMN_C_ORDERLINE_ID, COLUMN_LINENO,
                                COLUMN_C_BPARTNER_ID, COLUMN_C_BPARTNER_LOCATION_ID, COLUMN_DATEORDERED, COLUMN_M_PRODUCT_ID, COLUMN_PRODUCTNAME,
                                COLUMN_M_WAREHOUSE_ID, COLUMN_M_LOCATOR_ID, COLUMN_C_UOM_ID, COLUMN_QTYORDERED, COLUMN_M_PRICELIST_ID, COLUMN_PRICELIST,
                                COLUMN_PRICEACTUAL, COLUMN_PRICELIMIT, COLUMN_LINENETAMT, COLUMN_DISCOUNT, COLUMN_C_TAX_ID, COLUMN_SYNCDATE },
                        COLUMN_SYNCDATE + " IS NULL AND " + COLUMN_C_ORDER_ID + " IS NOT NULL",
                        null, null, null, null, null);

        while (mCursor.moveToNext()){
            C_OrderLine orderitem = new C_OrderLine();
            orderitem.setID(Long.parseLong(mCursor.getString(0)));
            orderitem.setOrderID(Long.parseLong(mCursor.getString(1)));
            if (mCursor.getString(2) != null)
                orderitem.setC_Order_ID(Long.parseLong(mCursor.getString(2)));
            if (mCursor.getString(3) != null)
                orderitem.setC_OrderLine_ID(Long.parseLong(mCursor.getString(3)));
            orderitem.setLineNo(Integer.parseInt(mCursor.getString(4)));
            orderitem.setC_BPartner_ID(Long.parseLong(mCursor.getString(5)));
            orderitem.setC_BPartner_Location_ID(Long.parseLong((mCursor.getString(6))));
            orderitem.setDateOrdered(DateUtil.loadDate(mCursor, 7));
            orderitem.setM_Product_ID(Long.parseLong((mCursor.getString(8))));
            orderitem.setProductName(mCursor.getString(9));
            orderitem.setM_Warehouse_ID(Long.parseLong(mCursor.getString(10)));
            orderitem.setM_Locator_ID(Long.parseLong(mCursor.getString(11)));
            orderitem.setC_Uom_ID(Long.parseLong(mCursor.getString(12)));
            orderitem.setQtyOrdered(Integer.parseInt(mCursor.getString(13)));
            orderitem.setM_Pricelist_ID(Long.parseLong(mCursor.getString(14)));
            orderitem.setPriceList(Integer.parseInt(mCursor.getString(15)));
            orderitem.setPriceActual(Integer.parseInt(mCursor.getString(16)));
            orderitem.setPriceLimit(Integer.parseInt(mCursor.getString(17)));
            orderitem.setLineNetAmt(Integer.parseInt(mCursor.getString(18)));
            orderitem.setDiscount(Integer.parseInt(mCursor.getString(19)));
            orderitem.setC_Tax_ID(Long.parseLong(mCursor.getString(20)));
            orderitem.setSyncDate(DateUtil.loadDate(mCursor,21));
            orderitems.add(orderitem);
        }
        close();
        return orderitems;
    }

    /**
     * Return a Cursor positioned at the order item that matches the given id
     * @param id
     * @return Cursor positioned to matching order item, if found, else null
     */
    public C_OrderLine getC_OrderLine(long id) {
        open();

        C_OrderLine orderitem = new C_OrderLine();

        Cursor mCursor =

                mDb.query(true, DATABASE_TABLE, new String[] { COLUMN_ID, COLUMN_ORDERID, COLUMN_C_ORDER_ID, COLUMN_C_ORDERLINE_ID, COLUMN_LINENO,
                                COLUMN_C_BPARTNER_ID, COLUMN_C_BPARTNER_LOCATION_ID, COLUMN_DATEORDERED, COLUMN_M_PRODUCT_ID, COLUMN_PRODUCTNAME,
                                COLUMN_M_WAREHOUSE_ID, COLUMN_M_LOCATOR_ID, COLUMN_C_UOM_ID, COLUMN_QTYORDERED, COLUMN_M_PRICELIST_ID, COLUMN_PRICELIST,
                                COLUMN_PRICEACTUAL, COLUMN_PRICELIMIT, COLUMN_LINENETAMT, COLUMN_DISCOUNT, COLUMN_C_TAX_ID, COLUMN_SYNCDATE },
                        COLUMN_ID + "=" + id, null, null, null, null, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            orderitem.setID(Long.parseLong(mCursor.getString(0)));
            orderitem.setOrderID(Long.parseLong(mCursor.getString(1)));
            if (mCursor.getString(2) != null)
                orderitem.setC_Order_ID(Long.parseLong(mCursor.getString(2)));
            if (mCursor.getString(3) != null)
                orderitem.setC_OrderLine_ID(Long.parseLong(mCursor.getString(3)));
            orderitem.setLineNo(Integer.parseInt(mCursor.getString(4)));
            orderitem.setC_BPartner_ID(Long.parseLong(mCursor.getString(5)));
            orderitem.setC_BPartner_Location_ID(Long.parseLong((mCursor.getString(6))));
            orderitem.setDateOrdered(DateUtil.loadDate(mCursor, 7));
            orderitem.setM_Product_ID(Long.parseLong((mCursor.getString(8))));
            orderitem.setProductName(mCursor.getString(9));
            orderitem.setM_Warehouse_ID(Long.parseLong(mCursor.getString(10)));
            orderitem.setM_Locator_ID(Long.parseLong(mCursor.getString(11)));
            orderitem.setC_Uom_ID(Long.parseLong(mCursor.getString(12)));
            orderitem.setQtyOrdered(Integer.parseInt(mCursor.getString(13)));
            orderitem.setM_Pricelist_ID(Long.parseLong(mCursor.getString(14)));
            orderitem.setPriceList(Integer.parseInt(mCursor.getString(15)));
            orderitem.setPriceActual(Integer.parseInt(mCursor.getString(16)));
            orderitem.setPriceLimit(Integer.parseInt(mCursor.getString(17)));
            orderitem.setLineNetAmt(Integer.parseInt(mCursor.getString(18)));
            orderitem.setDiscount(Integer.parseInt(mCursor.getString(19)));
            orderitem.setC_Tax_ID(Long.parseLong(mCursor.getString(20)));
            orderitem.setSyncDate(DateUtil.loadDate(mCursor,21));
        }
        else {
            close();
            return null;
        }
        close();
        return orderitem;
    }

    /**
     * Return a Cursor positioned at the order item that matches the given id
     * @param c_orderline_id
     * @return Cursor positioned to matching order item, if found, else null
     */
    public C_OrderLine getC_OrderLineByC_OrderLine_ID(long c_orderline_id) {
        open();

        C_OrderLine orderitem = new C_OrderLine();

        Cursor mCursor =

                mDb.query(true, DATABASE_TABLE, new String[] { COLUMN_ID, COLUMN_ORDERID, COLUMN_C_ORDER_ID, COLUMN_C_ORDERLINE_ID, COLUMN_LINENO,
                                COLUMN_C_BPARTNER_ID, COLUMN_C_BPARTNER_LOCATION_ID, COLUMN_DATEORDERED, COLUMN_M_PRODUCT_ID, COLUMN_PRODUCTNAME,
                                COLUMN_M_WAREHOUSE_ID, COLUMN_M_LOCATOR_ID, COLUMN_C_UOM_ID, COLUMN_QTYORDERED, COLUMN_M_PRICELIST_ID, COLUMN_PRICELIST,
                                COLUMN_PRICEACTUAL, COLUMN_PRICELIMIT, COLUMN_LINENETAMT, COLUMN_DISCOUNT, COLUMN_C_TAX_ID, COLUMN_SYNCDATE },
                        COLUMN_C_ORDERLINE_ID + "=" + c_orderline_id, null, null, null, null, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            orderitem.setID(Long.parseLong(mCursor.getString(0)));
            orderitem.setOrderID(Long.parseLong(mCursor.getString(1)));
            if (mCursor.getString(2) != null)
                orderitem.setC_Order_ID(Long.parseLong(mCursor.getString(2)));
            if (mCursor.getString(3) != null)
                orderitem.setC_OrderLine_ID(Long.parseLong(mCursor.getString(3)));
            orderitem.setLineNo(Integer.parseInt(mCursor.getString(4)));
            orderitem.setC_BPartner_ID(Long.parseLong(mCursor.getString(5)));
            orderitem.setC_BPartner_Location_ID(Long.parseLong((mCursor.getString(6))));
            orderitem.setDateOrdered(DateUtil.loadDate(mCursor, 7));
            orderitem.setM_Product_ID(Long.parseLong((mCursor.getString(8))));
            orderitem.setProductName(mCursor.getString(9));
            orderitem.setM_Warehouse_ID(Long.parseLong(mCursor.getString(10)));
            orderitem.setM_Locator_ID(Long.parseLong(mCursor.getString(11)));
            orderitem.setC_Uom_ID(Long.parseLong(mCursor.getString(12)));
            orderitem.setQtyOrdered(Integer.parseInt(mCursor.getString(13)));
            orderitem.setM_Pricelist_ID(Long.parseLong(mCursor.getString(14)));
            orderitem.setPriceList(Integer.parseInt(mCursor.getString(15)));
            orderitem.setPriceActual(Integer.parseInt(mCursor.getString(16)));
            orderitem.setPriceLimit(Integer.parseInt(mCursor.getString(17)));
            orderitem.setLineNetAmt(Integer.parseInt(mCursor.getString(18)));
            orderitem.setDiscount(Integer.parseInt(mCursor.getString(19)));
            orderitem.setC_Tax_ID(Long.parseLong(mCursor.getString(20)));
            orderitem.setSyncDate(DateUtil.loadDate(mCursor,21));
        }
        else {
            close();
            return null;
        }
        close();
        return orderitem;
    }

    /**
     * Update the order item.
     *
     * @param orderitem
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateC_OrderLine(C_OrderLine orderitem){
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_ORDERID, orderitem.getOrderID());

        args.put(COLUMN_LINENO, orderitem.getLineNo());
        args.put(COLUMN_C_BPARTNER_ID, orderitem.getC_BPartner_ID());
        args.put(COLUMN_C_BPARTNER_LOCATION_ID, orderitem.getC_BPartner_Location_ID());
        args.put(COLUMN_DATEORDERED, DateUtil.persistDate(orderitem.getDateOrdered()));
        args.put(COLUMN_M_PRODUCT_ID, orderitem.getM_Product_ID());
        args.put(COLUMN_PRODUCTNAME, orderitem.getProductName());
        args.put(COLUMN_M_WAREHOUSE_ID, orderitem.getM_Warehouse_ID());
        args.put(COLUMN_M_LOCATOR_ID, orderitem.getM_Locator_ID());
        args.put(COLUMN_C_UOM_ID, orderitem.getC_Uom_ID());
        args.put(COLUMN_QTYORDERED, orderitem.getQtyOrdered());
        args.put(COLUMN_M_PRICELIST_ID, orderitem.getM_Pricelist_ID());
        args.put(COLUMN_PRICELIST, orderitem.getPriceList());
        args.put(COLUMN_PRICEACTUAL, orderitem.getPriceActual());
        args.put(COLUMN_PRICELIMIT, orderitem.getPriceLimit());
        args.put(COLUMN_LINENETAMT, orderitem.getLineNetAmt());
        args.put(COLUMN_DISCOUNT, orderitem.getDiscount());
        args.put(COLUMN_C_TAX_ID, orderitem.getC_Tax_ID());

        return mDb.update(DATABASE_TABLE, args, COLUMN_ID + "=" + orderitem.getID(), null) >0;
    }

    /**
     * Update the order item.
     *
     * @param orderitem
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateC_OrderLineWith_C_OrderLine_ID(C_OrderLine orderitem){
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_ORDERID, orderitem.getOrderID());

        args.put(COLUMN_LINENO, orderitem.getLineNo());
        args.put(COLUMN_C_BPARTNER_ID, orderitem.getC_BPartner_ID());
        args.put(COLUMN_C_BPARTNER_LOCATION_ID, orderitem.getC_BPartner_Location_ID());
        args.put(COLUMN_DATEORDERED, DateUtil.persistDate(orderitem.getDateOrdered()));
        args.put(COLUMN_M_PRODUCT_ID, orderitem.getM_Product_ID());
        args.put(COLUMN_PRODUCTNAME, orderitem.getProductName());
        args.put(COLUMN_M_WAREHOUSE_ID, orderitem.getM_Warehouse_ID());
        if (orderitem.getM_Locator_ID() >0) {
            args.put(COLUMN_M_LOCATOR_ID, orderitem.getM_Locator_ID());
        }
        args.put(COLUMN_C_UOM_ID, orderitem.getC_Uom_ID());
        args.put(COLUMN_QTYORDERED, orderitem.getQtyOrdered());
        if (orderitem.getM_Pricelist_ID() >0) {
            args.put(COLUMN_M_PRICELIST_ID, orderitem.getM_Pricelist_ID());
        }
        args.put(COLUMN_PRICELIST, orderitem.getPriceList());
        args.put(COLUMN_PRICEACTUAL, orderitem.getPriceActual());
        args.put(COLUMN_PRICELIMIT, orderitem.getPriceLimit());
        args.put(COLUMN_LINENETAMT, orderitem.getLineNetAmt());
        args.put(COLUMN_DISCOUNT, orderitem.getDiscount());
        args.put(COLUMN_C_TAX_ID, orderitem.getC_Tax_ID());

        return mDb.update(DATABASE_TABLE, args, COLUMN_ID + "=" + orderitem.getID(), null) >0;
    }

    /**
     * Update the synchronization date of the order.
     *
     * @param id
     * @param c_orderline_id
     * @param syncdate
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateC_OrderLineSyncDate(long id, long c_orderline_id, Date syncdate){
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_C_ORDERLINE_ID, c_orderline_id);
        args.put(COLUMN_SYNCDATE, DateUtil.persistDate(syncdate));

        return mDb.update(DATABASE_TABLE, args, COLUMN_ID + "=" + id, null) >0;
    }

    /**
     * Update the synchronization date of the order.
     *
     * @param id
     * @param c_order_id
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateC_OrderLineC_Order_ID(long id, long c_order_id){
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_C_ORDER_ID, c_order_id);

        return mDb.update(DATABASE_TABLE, args, COLUMN_ID + "=" + id, null) >0;
    }
}
