package com.keredwell.fieldsales.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.keredwell.fieldsales.data.M_Product;

import java.util.ArrayList;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 18/8/2017.
 */

public class M_ProductDBAdapter extends DBAdapter {
    private static final String TAG = makeLogTag(M_ProductDBAdapter.class);

    public static final String COLUMN_M_PRODUCT_ID = "_m_product_id";
    public static final String COLUMN_NAME = "_name";
    public static final String COLUMN_C_UOM_ID = "_c_uom_id";
    public static final String COLUMN_M_PRODUCT_CATEGORY_ID = "_m_product_category_id";
    public static final String COLUMN_M_LOCATOR_ID = "_m_locator_id";

    public static final String DATABASE_TABLE = "m_product";

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx
     *            the Context within which to work
     */
    public M_ProductDBAdapter(Context ctx) {
        super(ctx);
    }

    /**
     * Create a new customer. If the customer is successfully created return the
     * id for that customer, otherwise return a -1 to indicate failure.
     *
     * @param m_product
     * @return rowId or -1 if failed
     */
    public long createM_Product(M_Product m_product){
        open();

        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_M_PRODUCT_ID, m_product.getM_Product_ID());
        initialValues.put(COLUMN_NAME, m_product.getName());
        initialValues.put(COLUMN_C_UOM_ID, m_product.getC_Uom_ID());
        initialValues.put(COLUMN_M_PRODUCT_CATEGORY_ID, m_product.getM_Product_Category_ID());
        initialValues.put(COLUMN_M_LOCATOR_ID, m_product.getM_Locator_ID());

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the user with the given id
     *
     * @param m_product_id
     * @return true if deleted, false otherwise
     */
    public boolean deleteM_Product(long m_product_id) {
        open();

        return mDb.delete(DATABASE_TABLE, COLUMN_M_PRODUCT_ID + "=" + m_product_id, null) > 0; //$NON-NLS-1$
    }

    /**
     * Return a List<Customer>  over the list of all customers in the database
     *
     * @param m_product_category_id
     * @return List<Customer>  over all customers
     */
    public ArrayList<M_Product> getAllProductsByCategoryId(long m_product_category_id) {
        open();

        ArrayList<M_Product> m_products = new ArrayList<>();

        String selectQuery = "SELECT (SELECT COUNT(0) from " + DATABASE_TABLE + " t1 where t1." + COLUMN_NAME + " >= t2." + COLUMN_NAME
                + " ) as 'RowNumber', t2." + COLUMN_M_PRODUCT_ID + ", " + COLUMN_NAME +  ", " + COLUMN_C_UOM_ID + ", " + COLUMN_M_PRODUCT_CATEGORY_ID + ", t2." + COLUMN_M_LOCATOR_ID
                + ", " + M_ProductPriceDBAdapter.COLUMN_PRICELIST + ", " + M_ProductPriceDBAdapter.COLUMN_PRICESTD + ", " + M_ProductPriceDBAdapter.COLUMN_PRICELIMIT
                + ", " + M_Pricelist_VersionDBAdapter.COLUMN_M_PRICELIST_ID
                + " FROM " + DATABASE_TABLE + " t2 "
                + " INNER JOIN " + M_ProductPriceDBAdapter.DATABASE_TABLE + " t3 "
                + " ON t2." + COLUMN_M_PRODUCT_ID + " = t3." + M_ProductPriceDBAdapter.COLUMN_M_PRODUCT_ID
                + " INNER JOIN " + M_Pricelist_VersionDBAdapter.DATABASE_TABLE + " t4 "
                + " ON t3." + M_ProductPriceDBAdapter.COLUMN_M_PRICELIST_VERSION_ID + " = t4." + M_Pricelist_VersionDBAdapter.COLUMN_M_PRICELIST_VERSION_ID
                + " WHERE " + COLUMN_M_PRODUCT_CATEGORY_ID + " =  " + m_product_category_id + " ORDER BY " + COLUMN_NAME;

        Cursor mCursor = mDb.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        while (mCursor.moveToNext()){
            M_Product m_product = new M_Product();
            m_product.setRowNumber(Integer.parseInt(mCursor.getString(0)));
            m_product.setM_Product_ID(Long.parseLong(mCursor.getString(1)));
            m_product.setName(mCursor.getString(2));
            m_product.setC_Uom_ID(Long.parseLong(mCursor.getString(3)));
            m_product.setM_Product_Category_ID(Long.parseLong(mCursor.getString(4)));
            m_product.setM_Locator_ID(Long.parseLong(mCursor.getString(5)));
            m_product.setPriceList(Integer.parseInt(mCursor.getString(6)));
            m_product.setPriceStd(Integer.parseInt(mCursor.getString(7)));
            m_product.setPriceLimit(Integer.parseInt(mCursor.getString(8)));
            m_product.setM_ProductList_ID(Long.parseLong(mCursor.getString(9)));
            m_products.add(m_product);
        }
        close();
        return m_products;
    }

    /**
     * Return a List<Customer>  over the list of all customers in the database
     *
     * @param searchterm
     * @return List<Customer>  over all customers
     */
    public ArrayList<M_Product> getAllProductsBySearch(String searchterm) {
        open();

        ArrayList<M_Product> m_products = new ArrayList<>();

        String selectQuery = "SELECT (SELECT COUNT(0) from " + DATABASE_TABLE + " t1 where t1." + COLUMN_NAME + " >= t2." + COLUMN_NAME
                + " ) as 'RowNumber', t2." + COLUMN_M_PRODUCT_ID + ", " + COLUMN_NAME +  ", " + COLUMN_C_UOM_ID + ", " + COLUMN_M_PRODUCT_CATEGORY_ID + ", t2." + COLUMN_M_LOCATOR_ID
                + ", " + M_ProductPriceDBAdapter.COLUMN_PRICELIST + ", " + M_ProductPriceDBAdapter.COLUMN_PRICESTD + ", " + M_ProductPriceDBAdapter.COLUMN_PRICELIMIT
                + ", " + M_Pricelist_VersionDBAdapter.COLUMN_M_PRICELIST_ID
                + " FROM " + DATABASE_TABLE + " t2 "
                + " INNER JOIN " + M_ProductPriceDBAdapter.DATABASE_TABLE + " t3 "
                + " ON t2." + COLUMN_M_PRODUCT_ID + " = t3." + M_ProductPriceDBAdapter.COLUMN_M_PRODUCT_ID
                + " INNER JOIN " + M_Pricelist_VersionDBAdapter.DATABASE_TABLE + " t4 "
                + " ON t3." + M_ProductPriceDBAdapter.COLUMN_M_PRICELIST_VERSION_ID + " = t4." + M_Pricelist_VersionDBAdapter.COLUMN_M_PRICELIST_VERSION_ID
                + " WHERE " + COLUMN_NAME + " LIKE '%" + searchterm + "%' ORDER BY " + COLUMN_NAME;

        Cursor mCursor = mDb.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        while (mCursor.moveToNext()){
            M_Product m_product = new M_Product();
            m_product.setRowNumber(Integer.parseInt(mCursor.getString(0)));
            m_product.setM_Product_ID(Long.parseLong(mCursor.getString(1)));
            m_product.setName(mCursor.getString(2));
            m_product.setC_Uom_ID(Long.parseLong(mCursor.getString(3)));
            m_product.setM_Product_Category_ID(Long.parseLong(mCursor.getString(4)));
            m_product.setM_Locator_ID(Long.parseLong(mCursor.getString(5)));
            m_product.setPriceList(Integer.parseInt(mCursor.getString(6)));
            m_product.setPriceStd(Integer.parseInt(mCursor.getString(7)));
            m_product.setPriceLimit(Integer.parseInt(mCursor.getString(8)));
            m_product.setM_ProductList_ID(Long.parseLong(mCursor.getString(9)));
            m_products.add(m_product);
        }
        close();
        return m_products;
    }

    /**
     * Return a Cursor positioned at the customer that matches the given rowId
     * @param name
     * @return Cursor positioned to matching customer, if found, else null
     */
    public M_Product getM_Product(String name) {
        open();

        M_Product m_product = new M_Product();

        String selectQuery = "SELECT (SELECT COUNT(0) from " + DATABASE_TABLE + " t1 where t1." + COLUMN_NAME + " >= t2." + COLUMN_NAME
                + " ) as 'RowNumber', t2." + COLUMN_M_PRODUCT_ID + ", " + COLUMN_NAME +  ", " + COLUMN_C_UOM_ID + ", " + COLUMN_M_PRODUCT_CATEGORY_ID + ", t2." + COLUMN_M_LOCATOR_ID
                + ", " + M_ProductPriceDBAdapter.COLUMN_PRICELIST + ", " + M_ProductPriceDBAdapter.COLUMN_PRICESTD + ", " + M_ProductPriceDBAdapter.COLUMN_PRICELIMIT
                + ", " + M_Pricelist_VersionDBAdapter.COLUMN_M_PRICELIST_ID
                + " FROM " + DATABASE_TABLE + " t2 "
                + " INNER JOIN " + M_ProductPriceDBAdapter.DATABASE_TABLE + " t3 "
                + " ON t2." + COLUMN_M_PRODUCT_ID + " = t3." + M_ProductPriceDBAdapter.COLUMN_M_PRODUCT_ID
                + " INNER JOIN " + M_Pricelist_VersionDBAdapter.DATABASE_TABLE + " t4 "
                + " ON t3." + M_ProductPriceDBAdapter.COLUMN_M_PRICELIST_VERSION_ID + " = t4." + M_Pricelist_VersionDBAdapter.COLUMN_M_PRICELIST_VERSION_ID
                + " WHERE " + COLUMN_NAME + " =  " + name + " ORDER BY " + COLUMN_NAME;

        Cursor mCursor = mDb.rawQuery(selectQuery, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            m_product.setRowNumber(Integer.parseInt(mCursor.getString(0)));
            m_product.setM_Product_ID(Long.parseLong(mCursor.getString(1)));
            m_product.setName(mCursor.getString(2));
            m_product.setC_Uom_ID(Long.parseLong(mCursor.getString(3)));
            m_product.setM_Product_Category_ID(Long.parseLong(mCursor.getString(4)));
            m_product.setM_Locator_ID(Long.parseLong(mCursor.getString(5)));
            m_product.setPriceList(Integer.parseInt(mCursor.getString(6)));
            m_product.setPriceStd(Integer.parseInt(mCursor.getString(7)));
            m_product.setPriceLimit(Integer.parseInt(mCursor.getString(8)));
            m_product.setM_ProductList_ID(Long.parseLong(mCursor.getString(9)));
        }
        else {
            close();
            return null;
        }
        close();
        return m_product;
    }

    /**
     * Return a Cursor positioned at the customer that matches the given rowId
     * @param m_product_id
     * @return Cursor positioned to matching customer, if found, else null
     */
    public M_Product getM_Product(long m_product_id) {
        open();

        M_Product m_product = new M_Product();

        String selectQuery = "SELECT (SELECT COUNT(0) from " + DATABASE_TABLE + " t1 where t1." + COLUMN_NAME + " >= t2." + COLUMN_NAME
                + " ) as 'RowNumber', t2." + COLUMN_M_PRODUCT_ID + ", " + COLUMN_NAME +  ", " + COLUMN_C_UOM_ID + ", " + COLUMN_M_PRODUCT_CATEGORY_ID + ", t2." + COLUMN_M_LOCATOR_ID
                + ", " + M_ProductPriceDBAdapter.COLUMN_PRICELIST + ", " + M_ProductPriceDBAdapter.COLUMN_PRICESTD + ", " + M_ProductPriceDBAdapter.COLUMN_PRICELIMIT
                + ", " + M_Pricelist_VersionDBAdapter.COLUMN_M_PRICELIST_ID
                + " FROM " + DATABASE_TABLE + " t2 "
                + " INNER JOIN " + M_ProductPriceDBAdapter.DATABASE_TABLE + " t3 "
                + " ON t2." + COLUMN_M_PRODUCT_ID + " = t3." + M_ProductPriceDBAdapter.COLUMN_M_PRODUCT_ID
                + " INNER JOIN " + M_Pricelist_VersionDBAdapter.DATABASE_TABLE + " t4 "
                + " ON t3." + M_ProductPriceDBAdapter.COLUMN_M_PRICELIST_VERSION_ID + " = t4." + M_Pricelist_VersionDBAdapter.COLUMN_M_PRICELIST_VERSION_ID
                + " WHERE t2." + COLUMN_M_PRODUCT_ID + " =  " + m_product_id + " ORDER BY " + COLUMN_NAME;

        Cursor mCursor = mDb.rawQuery(selectQuery, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            m_product.setRowNumber(Integer.parseInt(mCursor.getString(0)));
            m_product.setM_Product_ID(Long.parseLong(mCursor.getString(1)));
            m_product.setName(mCursor.getString(2));
            m_product.setC_Uom_ID(Long.parseLong(mCursor.getString(3)));
            m_product.setM_Product_Category_ID(Long.parseLong(mCursor.getString(4)));
            m_product.setM_Locator_ID(Long.parseLong(mCursor.getString(5)));
            m_product.setPriceList(Integer.parseInt(mCursor.getString(6)));
            m_product.setPriceStd(Integer.parseInt(mCursor.getString(7)));
            m_product.setPriceLimit(Integer.parseInt(mCursor.getString(8)));
            m_product.setM_ProductList_ID(Long.parseLong(mCursor.getString(9)));
        }
        else {
            close();
            return null;
        }
        close();
        return m_product;
    }

    /**
     * Update the customer.
     *
     * @param m_product
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateM_Product(M_Product m_product){
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_NAME, m_product.getName());
        args.put(COLUMN_C_UOM_ID, m_product.getC_Uom_ID());
        args.put(COLUMN_M_PRODUCT_CATEGORY_ID, m_product.getM_Product_Category_ID());
        args.put(COLUMN_M_LOCATOR_ID, m_product.getM_Locator_ID());

        return mDb.update(DATABASE_TABLE, args, COLUMN_M_PRODUCT_ID + "=" + m_product.getM_Product_ID(), null) >0;
    }
}
