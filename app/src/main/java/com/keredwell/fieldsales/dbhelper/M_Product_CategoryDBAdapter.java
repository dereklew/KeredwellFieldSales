package com.keredwell.fieldsales.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.keredwell.fieldsales.data.M_Product_Category;

import java.util.ArrayList;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 18/8/2017.
 */

public class M_Product_CategoryDBAdapter extends DBAdapter {
    private static final String TAG = makeLogTag(M_Product_CategoryDBAdapter.class);

    public static final String COLUMN_M_PRODUCT_CATEGORY_ID = "_m_product_category_id";
    public static final String COLUMN_NAME = "_name";

    public static final String DATABASE_TABLE = "m_product_category";

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx
     *            the Context within which to work
     */
    public M_Product_CategoryDBAdapter(Context ctx) {
        super(ctx);
    }

    /**
     * Create a new customer. If the customer is successfully created return the
     * id for that customer, otherwise return a -1 to indicate failure.
     *
     * @param m_product_category
     * @return rowId or -1 if failed
     */
    public long createM_Product_Category(M_Product_Category m_product_category){
        open();

        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_M_PRODUCT_CATEGORY_ID, m_product_category.getM_Product_Category_ID());
        initialValues.put(COLUMN_NAME, m_product_category.getName());

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the user with the given id
     *
     * @param m_product_category_id
     * @return true if deleted, false otherwise
     */
    public boolean deleteM_Product_Category(long m_product_category_id) {
        open();

        return mDb.delete(DATABASE_TABLE, COLUMN_M_PRODUCT_CATEGORY_ID + "=" + m_product_category_id, null) > 0; //$NON-NLS-1$
    }

    /**
     * Return a List<Customer>  over the list of all customers in the database
     *
     * @return List<Customer>  over all customers
     */
    public ArrayList<String> getAllProductCategories() {
        open();

        ArrayList<String> customers = new ArrayList<>();

        Cursor mCursor =

                mDb.query(DATABASE_TABLE, new String[] { COLUMN_NAME },
                        null, null, null, null, COLUMN_NAME +" DESC");

        while (mCursor.moveToNext()){
            customers.add(mCursor.getString(0));
        }
        close();
        return customers;
    }

    /**
     * Return a Cursor positioned at the customer that matches the given rowId
     * @param name
     * @return Cursor positioned to matching customer, if found, else null
     */
    public M_Product_Category getM_Product_Category(String name) {
        open();

        M_Product_Category c_bp_group = new M_Product_Category();

        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[] { COLUMN_NAME,
                                COLUMN_M_PRODUCT_CATEGORY_ID }, COLUMN_NAME + "=\"" + name + "\"",
                        null, null, null, null, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            c_bp_group.setName(mCursor.getString(0));
            c_bp_group.setM_Product_Category_ID(Long.parseLong(mCursor.getString(1)));
            c_bp_group.setRowNumber(0);
        }
        else {
            close();
            return null;
        }
        close();
        return c_bp_group;
    }

    /**
     * Return a Cursor positioned at the customer that matches the given rowId
     * @param m_product_category_id
     * @return Cursor positioned to matching customer, if found, else null
     */
    public M_Product_Category getM_Product_Category(long m_product_category_id) {
        open();

        M_Product_Category c_bp_group = new M_Product_Category();

        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[] { COLUMN_NAME,
                                COLUMN_M_PRODUCT_CATEGORY_ID}, COLUMN_M_PRODUCT_CATEGORY_ID + "=" + m_product_category_id,
                        null, null, null, null, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            c_bp_group.setName(mCursor.getString(0));
            c_bp_group.setM_Product_Category_ID(Long.parseLong(mCursor.getString(1)));
            c_bp_group.setRowNumber(0);
        }
        else {
            close();
            return null;
        }
        close();
        return c_bp_group;
    }

    /**
     * Update the customer.
     *
     * @param m_product_category
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateM_Product_Category(M_Product_Category m_product_category){
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_NAME, m_product_category.getName());

        return mDb.update(DATABASE_TABLE, args, COLUMN_M_PRODUCT_CATEGORY_ID + "=" + m_product_category.getM_Product_Category_ID(), null) >0;
    }
}
