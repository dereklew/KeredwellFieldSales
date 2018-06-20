package com.keredwell.fieldsales.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.keredwell.fieldsales.data.C_BPartner_Location;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 18/8/2017.
 */

public class C_BPartner_LocationDBAdapter extends DBAdapter {
    private static final String TAG = makeLogTag(C_BPartner_LocationDBAdapter.class);

    public static final String COLUMN_C_BPARTNER_LOCATION_ID = "_c_bpartner_location_id";
    public static final String COLUMN_C_BPARTNER_ID = "_c_bpartner_id";
    public static final String COLUMN_C_LOCATION_ID = "_c_location_id";
    public static final String COLUMN_PHONE = "_phone";

    public static final String DATABASE_TABLE = "c_bpartner_location";

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx
     *            the Context within which to work
     */
    public C_BPartner_LocationDBAdapter(Context ctx) {
        super(ctx);
    }

    /**
     * Create a new customer. If the customer is successfully created return the
     * id for that customer, otherwise return a -1 to indicate failure.
     *
     * @param c_bpartner_location
     * @return rowId or -1 if failed
     */
    public long createC_BPartner_Location(C_BPartner_Location c_bpartner_location){
        open();

        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_C_BPARTNER_LOCATION_ID, c_bpartner_location.getC_BPartner_Location_ID());
        initialValues.put(COLUMN_C_BPARTNER_ID, c_bpartner_location.getC_BPartner_ID());
        initialValues.put(COLUMN_C_LOCATION_ID, c_bpartner_location.getC_Location_ID());
        initialValues.put(COLUMN_PHONE, c_bpartner_location.getPhone());

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the user with the given id
     *
     * @param c_bpartner_location_id
     * @return true if deleted, false otherwise
     */
    public boolean deleteC_BPartner_Location(long c_bpartner_location_id) {
        open();

        return mDb.delete(DATABASE_TABLE, COLUMN_C_BPARTNER_LOCATION_ID + "=" + c_bpartner_location_id, null) > 0; //$NON-NLS-1$
    }

    /**
     * Return a Cursor positioned at the customer that matches the given rowId
     * @param c_bpartner_location_id
     * @return Cursor positioned to matching customer, if found, else null
     */
    public C_BPartner_Location getC_BPartner_Location(long c_bpartner_location_id) {
        open();

        C_BPartner_Location c_bpartner_location = new C_BPartner_Location();

        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[] { COLUMN_C_BPARTNER_LOCATION_ID, COLUMN_C_BPARTNER_ID,
                                COLUMN_C_LOCATION_ID, COLUMN_PHONE}, COLUMN_C_BPARTNER_LOCATION_ID + "=" + c_bpartner_location_id,
                        null, null, null, null, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            c_bpartner_location.setC_BPartner_Location_ID(Long.parseLong(mCursor.getString(0)));
            c_bpartner_location.setC_BPartner_ID(Long.parseLong(mCursor.getString(1)));
            c_bpartner_location.setC_Location_ID(Long.parseLong(mCursor.getString(2)));
            c_bpartner_location.setPhone(mCursor.getString(3));
            c_bpartner_location.setRowNumber(0);
        }
        else {
            close();
            return null;
        }
        close();
        return c_bpartner_location;
    }

    /**
     * Update the customer.
     *
     * @param c_bpartner_location
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateC_BPartner_Location(C_BPartner_Location c_bpartner_location){
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_C_BPARTNER_ID, c_bpartner_location.getC_BPartner_ID());
        args.put(COLUMN_C_LOCATION_ID, c_bpartner_location.getC_Location_ID());
        args.put(COLUMN_PHONE, c_bpartner_location.getPhone());

        return mDb.update(DATABASE_TABLE, args, COLUMN_C_BPARTNER_LOCATION_ID + "=" + c_bpartner_location.getC_BPartner_Location_ID(), null) >0;
    }
}
