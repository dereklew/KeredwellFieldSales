package com.keredwell.fieldsales.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.keredwell.fieldsales.data.M_Locator;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 18/8/2017.
 */

public class M_LocatorDBAdapter extends DBAdapter {
    private static final String TAG = makeLogTag(M_LocatorDBAdapter.class);

    public static final String COLUMN_M_LOCATOR_ID = "_m_locator_id";
    public static final String COLUMN_M_WAREHOUSE_ID = "_m_warehouse_id";
    public static final String COLUMN_X_AISLE = "_x_aisle";
    public static final String COLUMN_Y_BIN = "_y_bin";
    public static final String COLUMN_Z_LEVEL = "_z_level";

    public static final String DATABASE_TABLE = "m_locator";

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx
     *            the Context within which to work
     */
    public M_LocatorDBAdapter(Context ctx) {
        super(ctx);
    }

    /**
     * Create a new customer. If the customer is successfully created return the
     * id for that customer, otherwise return a -1 to indicate failure.
     *
     * @param m_locator
     * @return rowId or -1 if failed
     */
    public long createM_Locator(M_Locator m_locator){
        open();

        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_M_LOCATOR_ID, m_locator.getM_Locator_ID());
        initialValues.put(COLUMN_M_WAREHOUSE_ID, m_locator.getM_Warehouse_ID());
        initialValues.put(COLUMN_X_AISLE, m_locator.getX_Aisle());
        initialValues.put(COLUMN_Y_BIN, m_locator.getY_Bin());
        initialValues.put(COLUMN_Z_LEVEL, m_locator.getZ_Level());

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the user with the given id
     *
     * @param m_locator_id
     * @return true if deleted, false otherwise
     */
    public boolean deleteM_Locator(long m_locator_id) {
        open();

        return mDb.delete(DATABASE_TABLE, COLUMN_M_LOCATOR_ID + "=" + m_locator_id, null) > 0; //$NON-NLS-1$
    }

    /**
     * Return a Cursor positioned at the customer that matches the given rowId
     * @return Cursor positioned to matching customer, if found, else null
     */
    public M_Locator getM_Locator() {
        open();

        M_Locator m_locator = new M_Locator();

        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[] { COLUMN_M_LOCATOR_ID, COLUMN_M_WAREHOUSE_ID,
                                COLUMN_X_AISLE, COLUMN_Y_BIN, COLUMN_Z_LEVEL}, null,
                        null, null, null, null, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            m_locator.setM_Locator_ID(Long.parseLong(mCursor.getString(0)));
            m_locator.setM_Warehouse_ID(Long.parseLong(mCursor.getString(1)));
            m_locator.setX_Aisle(mCursor.getString(2));
            m_locator.setY_Bin(mCursor.getString(3));
            m_locator.setZ_Level(mCursor.getString(4));
            m_locator.setRowNumber(0);
        }
        else {
            close();
            return null;
        }
        close();
        return m_locator;
    }

     /**
     * Return a Cursor positioned at the customer that matches the given rowId
     * @param m_locator_id
     * @return Cursor positioned to matching customer, if found, else null
     */
    public M_Locator getM_Locator(long m_locator_id) {
        open();

        M_Locator m_locator = new M_Locator();

        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[] { COLUMN_M_LOCATOR_ID, COLUMN_M_WAREHOUSE_ID,
                                COLUMN_X_AISLE, COLUMN_Y_BIN, COLUMN_Z_LEVEL}, COLUMN_M_LOCATOR_ID + "=" + m_locator_id,
                        null, null, null, null, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            m_locator.setM_Locator_ID(Long.parseLong(mCursor.getString(0)));
            m_locator.setM_Warehouse_ID(Long.parseLong(mCursor.getString(1)));
            m_locator.setX_Aisle(mCursor.getString(2));
            m_locator.setY_Bin(mCursor.getString(3));
            m_locator.setZ_Level(mCursor.getString(4));
            m_locator.setRowNumber(0);
        }
        else {
            close();
            return null;
        }
        close();
        return m_locator;
    }

    /**
     * Update the customer.
     *
     * @param c_location
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateM_Locator(M_Locator c_location){
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_M_WAREHOUSE_ID, c_location.getM_Warehouse_ID());
        args.put(COLUMN_X_AISLE, c_location.getX_Aisle());
        args.put(COLUMN_Y_BIN, c_location.getY_Bin());
        args.put(COLUMN_Z_LEVEL, c_location.getZ_Level());

        return mDb.update(DATABASE_TABLE, args, COLUMN_M_LOCATOR_ID + "=" + c_location.getM_Locator_ID(), null) >0;
    }
}
