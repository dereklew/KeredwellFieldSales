package com.keredwell.fieldsales.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.keredwell.fieldsales.data.AD_User;

import java.util.ArrayList;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class AD_UserDBAdapter extends DBAdapter {
    private static final String TAG = makeLogTag(AD_UserDBAdapter.class);

    public static final String COLUMN_AD_USER_ID = "_ad_user_id";
    public static final String COLUMN_C_BPARTNER_ID = "_c_bpartner_id";
    public static final String COLUMN_NAME = "_name";

    public static final String DATABASE_TABLE = "ad_user";

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx
     *            the Context within which to work
     */
    public AD_UserDBAdapter(Context ctx) {
        super(ctx);
    }

    /**
     * Create a new customer. If the customer is successfully created return the
     * id for that customer, otherwise return a -1 to indicate failure.
     *
     * @param ad_user
     * @return rowId or -1 if failed
     */
    public long createAD_User(AD_User ad_user){
        open();

        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_AD_USER_ID, ad_user.getAD_User_ID());
        initialValues.put(COLUMN_C_BPARTNER_ID, ad_user.getC_BPartner_ID());
        initialValues.put(COLUMN_NAME, ad_user.getName());

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the user with the given id
     *
     * @param ad_user_id
     * @return true if deleted, false otherwise
     */
    public boolean deleteAD_User(long ad_user_id) {
        open();

        return mDb.delete(DATABASE_TABLE, COLUMN_AD_USER_ID + "=" + ad_user_id, null) > 0; //$NON-NLS-1$
    }

    /**
     * Return a Cursor positioned at the customer that matches the given rowId
     * @param ad_user_id
     * @return Cursor positioned to matching customer, if found, else null
     */
    public AD_User getAD_User(long ad_user_id) {
        open();

        AD_User ad_user = new AD_User();

        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[] { COLUMN_AD_USER_ID,
                                COLUMN_C_BPARTNER_ID, COLUMN_NAME }, COLUMN_AD_USER_ID + "=" + ad_user_id,
                        null, null, null, null, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            ad_user.setAD_User_ID(Long.parseLong(mCursor.getString(0)));
            ad_user.setC_BPartner_ID(Long.parseLong(mCursor.getString(1)));
            ad_user.setName(mCursor.getString(2));
        }
        else {
            close();
            return null;
        }
        close();
        return ad_user;
    }

    /**
     * Return a List<AD_User>  over the list of all AD_Users in the database
     *
     * @return List<AD_User>  over all customers
     */
    public ArrayList<AD_User> getAllAD_Users() {
        open();

        ArrayList<AD_User> ad_users = new ArrayList<>();

        String selectQuery = "SELECT " + COLUMN_AD_USER_ID +  ", " + COLUMN_C_BPARTNER_ID +  ", " + COLUMN_NAME
                + " FROM "
                + DATABASE_TABLE
                + " ORDER BY " + COLUMN_AD_USER_ID;

        Cursor mCursor = mDb.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        while (mCursor.moveToNext()){
            AD_User ad_user = new AD_User();
            ad_user.setAD_User_ID(Long.parseLong(mCursor.getString(0)));
            ad_user.setC_BPartner_ID(Long.parseLong(mCursor.getString(1)));
            ad_user.setName(mCursor.getString(2));
            ad_users.add(ad_user);
        }
        close();
        return ad_users;
    }

    /**
     * Return a List<AD_User>  over the list of all customers in the database
     *
     * @param searchterm
     * @return List<Customer>  over all customers
     */
    public ArrayList<AD_User> getAllAD_UsersBySearch(String searchterm) {
        open();

        ArrayList<AD_User> ad_users = new ArrayList<>();

        String selectQuery =
                "SELECT " + COLUMN_AD_USER_ID +  ", " + COLUMN_C_BPARTNER_ID +  ", " + COLUMN_NAME
                        + " FROM "
                        + DATABASE_TABLE
                        + " WHERE " + COLUMN_NAME + " LIKE '%" + searchterm + "% "
                        + " ORDER BY " + COLUMN_AD_USER_ID;

        Cursor mCursor = mDb.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        while (mCursor.moveToNext()){
            AD_User ad_user = new AD_User();
            ad_user.setAD_User_ID(Long.parseLong(mCursor.getString(0)));
            ad_user.setC_BPartner_ID(Long.parseLong(mCursor.getString(1)));
            ad_user.setName(mCursor.getString(2));
            ad_users.add(ad_user);
        }
        close();
        return ad_users;
    }

    /**
     * Update the customer.
     *
     * @param ad_user
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateAD_User(AD_User ad_user){
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_C_BPARTNER_ID, ad_user.getC_BPartner_ID());

        return mDb.update(DATABASE_TABLE, args, COLUMN_AD_USER_ID + "=" + ad_user.getAD_User_ID(), null) >0;
    }
}