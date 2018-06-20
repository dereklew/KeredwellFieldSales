package com.keredwell.fieldsales.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.keredwell.fieldsales.data.M_Pricelist_Version;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 18/8/2017.
 */

public class M_Pricelist_VersionDBAdapter extends DBAdapter {
    private static final String TAG = makeLogTag(M_Pricelist_VersionDBAdapter.class);

    public static final String COLUMN_M_PRICELIST_VERSION_ID = "_m_pricelist_version_id";
    public static final String COLUMN_M_PRICELIST_ID = "_m_pricelist_id";

    public static final String DATABASE_TABLE = "m_pricelist_version";

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx
     *            the Context within which to work
     */
    public M_Pricelist_VersionDBAdapter(Context ctx) {
        super(ctx);
    }

    /**
     * Create a new customer. If the customer is successfully created return the
     * id for that customer, otherwise return a -1 to indicate failure.
     *
     * @param c_bPartner
     * @return rowId or -1 if failed
     */
    public long createM_Pricelist_Version(M_Pricelist_Version c_bPartner){
        open();

        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_M_PRICELIST_VERSION_ID, c_bPartner.getM_Pricelist_Version_ID());
        initialValues.put(COLUMN_M_PRICELIST_ID, c_bPartner.getM_Pricelist_ID());

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the user with the given id
     *
     * @param m_picelist_version_id
     * @return true if deleted, false otherwise
     */
    public boolean deleteM_Pricelist_Version(long m_picelist_version_id) {
        open();

        return mDb.delete(DATABASE_TABLE, COLUMN_M_PRICELIST_VERSION_ID + "=" + m_picelist_version_id, null) > 0; //$NON-NLS-1$
    }

    /**
     * Return a Cursor positioned at the customer that matches the given rowId
     * @param m_picelist_version_id
     * @return Cursor positioned to matching customer, if found, else null
     */
    public M_Pricelist_Version getM_Pricelist_Version(long m_picelist_version_id) {
        open();

        M_Pricelist_Version m_productPrice = new M_Pricelist_Version();

        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[] { COLUMN_M_PRICELIST_VERSION_ID, COLUMN_M_PRICELIST_ID },
                        COLUMN_M_PRICELIST_VERSION_ID + "=" + m_picelist_version_id,
                        null, null, null, null, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            m_productPrice.setM_Pricelist_Version_ID(Long.parseLong(mCursor.getString(0)));
            m_productPrice.setM_Pricelist_ID(Long.parseLong(mCursor.getString(1)));
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
     * @param m_pricelist_version
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateM_Pricelist_Version(M_Pricelist_Version m_pricelist_version){
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_M_PRICELIST_ID, m_pricelist_version.getM_Pricelist_ID());

        return mDb.update(DATABASE_TABLE, args, COLUMN_M_PRICELIST_VERSION_ID + "=" + m_pricelist_version.getM_Pricelist_Version_ID(), null) >0;
    }
}
