package com.keredwell.fieldsales.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.keredwell.fieldsales.data.M_ProductPrice;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 18/8/2017.
 */

public class M_ProductPriceDBAdapter extends DBAdapter {
    private static final String TAG = makeLogTag(M_ProductPriceDBAdapter.class);

    public static final String COLUMN_M_PRODUCT_ID = "_m_product_id";
    public static final String COLUMN_M_PRICELIST_VERSION_ID = "_m_pricelist_version_id";
    public static final String COLUMN_PRICELIST = "_pricelist";
    public static final String COLUMN_PRICESTD = "_pricestd";
    public static final String COLUMN_PRICELIMIT = "_pricelimit";

    public static final String DATABASE_TABLE = "m_productprice";

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx
     *            the Context within which to work
     */
    public M_ProductPriceDBAdapter(Context ctx) {
        super(ctx);
    }

    /**
     * Create a new customer. If the customer is successfully created return the
     * id for that customer, otherwise return a -1 to indicate failure.
     *
     * @param c_bPartner
     * @return rowId or -1 if failed
     */
    public long createM_ProductPrice(M_ProductPrice c_bPartner){
        open();

        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_M_PRODUCT_ID, c_bPartner.getM_Product_ID());
        initialValues.put(COLUMN_M_PRICELIST_VERSION_ID, c_bPartner.getM_Pricelist_Version_ID());
        initialValues.put(COLUMN_PRICELIST, c_bPartner.getPriceList());
        initialValues.put(COLUMN_PRICESTD, c_bPartner.getPriceStd());
        initialValues.put(COLUMN_PRICELIMIT, c_bPartner.getPriceLimit());

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the user with the given id
     *
     * @param m_productprice_id
     * @return true if deleted, false otherwise
     */
    public boolean deleteM_ProductPrice(long m_productprice_id) {
        open();

        return mDb.delete(DATABASE_TABLE, COLUMN_M_PRODUCT_ID + "=" + m_productprice_id, null) > 0; //$NON-NLS-1$
    }

    /**
     * Return a Cursor positioned at the customer that matches the given rowId
     * @param m_productprice_id
     * @return Cursor positioned to matching customer, if found, else null
     */
    public M_ProductPrice getM_ProductPrice(long m_productprice_id) {
        open();

        M_ProductPrice m_productPrice = new M_ProductPrice();

        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[] { COLUMN_M_PRODUCT_ID, COLUMN_M_PRICELIST_VERSION_ID, COLUMN_PRICELIST,
                                COLUMN_PRICESTD, COLUMN_PRICELIMIT}, COLUMN_M_PRODUCT_ID + "=" + m_productprice_id,
                        null, null, null, null, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            m_productPrice.setM_Product_ID(Long.parseLong(mCursor.getString(0)));
            m_productPrice.setM_Pricelist_Version_ID(Long.parseLong(mCursor.getString(1)));
            m_productPrice.setPriceList(Integer.parseInt(mCursor.getString(2)));
            m_productPrice.setPriceStd(Integer.parseInt(mCursor.getString(3)));
            m_productPrice.setPriceLimit(Integer.parseInt(mCursor.getString(4)));
            m_productPrice.setRowNumber(0);
        }
        else {
            close();
            return null;
        }
        close();
        return m_productPrice;
    }

    /**
     * Update the customer.
     *
     * @param m_productPrice
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateM_ProductPrice(M_ProductPrice m_productPrice){
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_PRICELIST, m_productPrice.getPriceList());
        args.put(COLUMN_M_PRICELIST_VERSION_ID, m_productPrice.getM_Pricelist_Version_ID());
        args.put(COLUMN_PRICESTD, m_productPrice.getPriceStd());
        args.put(COLUMN_PRICELIMIT, m_productPrice.getPriceLimit());

        return mDb.update(DATABASE_TABLE, args, COLUMN_M_PRODUCT_ID + "=" + m_productPrice.getM_Product_ID(), null) >0;
    }
}
