package com.keredwell.fieldsales.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.keredwell.fieldsales.data.C_Location;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 18/8/2017.
 */

public class C_LocationDBAdapter extends DBAdapter {
    private static final String TAG = makeLogTag(C_LocationDBAdapter.class);

    public static final String COLUMN_C_LOCATION_ID = "_c_location_id";
    public static final String COLUMN_ADDRESS = "_address";
    public static final String COLUMN_POSTAL = "_postal";

    public static final String DATABASE_TABLE = "c_location";

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx
     *            the Context within which to work
     */
    public C_LocationDBAdapter(Context ctx) {
        super(ctx);
    }

    /**
     * Create a new customer. If the customer is successfully created return the
     * id for that customer, otherwise return a -1 to indicate failure.
     *
     * @param c_location
     * @return rowId or -1 if failed
     */
    public long createC_Location(C_Location c_location){
        open();

        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_C_LOCATION_ID, c_location.getC_Location_ID());
        initialValues.put(COLUMN_ADDRESS, c_location.getAddress());
        initialValues.put(COLUMN_POSTAL, c_location.getPostal());

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the user with the given id
     *
     * @param c_location_id
     * @return true if deleted, false otherwise
     */
    public boolean deleteC_Location(long c_location_id) {
        open();

        return mDb.delete(DATABASE_TABLE, COLUMN_C_LOCATION_ID + "=" + c_location_id, null) > 0; //$NON-NLS-1$
    }

    /**
     * Return a Cursor positioned at the customer that matches the given rowId
     * @param c_location_id
     * @return Cursor positioned to matching customer, if found, else null
     */
    public C_Location getC_Location(long c_location_id) {
        open();

        C_Location c_location = new C_Location();

        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[] { COLUMN_C_LOCATION_ID, COLUMN_ADDRESS,
                                COLUMN_POSTAL}, COLUMN_C_LOCATION_ID + "=" + c_location_id,
                        null, null, null, null, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            c_location.setC_Location_ID(Long.parseLong(mCursor.getString(0)));
            c_location.setAddress(mCursor.getString(1));
            c_location.setPostal(mCursor.getString(2));
            c_location.setRowNumber(0);
        }
        else {
            close();
            return null;
        }
        close();
        return c_location;
    }

    /**
     * Update the customer.
     *
     * @param c_location
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateC_Location(C_Location c_location){
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_ADDRESS, c_location.getAddress());
        args.put(COLUMN_POSTAL, c_location.getPostal());

        return mDb.update(DATABASE_TABLE, args, COLUMN_C_LOCATION_ID + "=" + c_location.getC_Location_ID(), null) >0;
    }
}
