package com.keredwell.fieldsales.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.keredwell.fieldsales.data.AD_User_Roles;

import java.util.ArrayList;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class AD_User_RolesDBAdapter extends DBAdapter {
    private static final String TAG = makeLogTag(AD_User_RolesDBAdapter.class);

    public static final String COLUMN_AD_USER_ID = "_ad_user_id";
    public static final String COLUMN_AD_ROLE_ID = "_ad_role_id";

    public static final String DATABASE_TABLE = "ad_user_roles";

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx
     *            the Context within which to work
     */
    public AD_User_RolesDBAdapter(Context ctx) {
        super(ctx);
    }

    /**
     * Create a new customer. If the customer is successfully created return the
     * id for that customer, otherwise return a -1 to indicate failure.
     *
     * @param ad_user_roles
     * @return rowId or -1 if failed
     */
    public long createAD_User_Roles(AD_User_Roles ad_user_roles){
        open();

        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_AD_USER_ID, ad_user_roles.getAD_User_ID());
        initialValues.put(COLUMN_AD_ROLE_ID, ad_user_roles.getAD_Role_ID());

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the user with the given id
     *
     * @param ad_user_id
     * @return true if deleted, false otherwise
     */
    public boolean deleteAD_User_Roles(long ad_user_id) {
        open();

        return mDb.delete(DATABASE_TABLE, COLUMN_AD_USER_ID + "=" + ad_user_id, null) > 0; //$NON-NLS-1$
    }

    /**
     * Return a Cursor positioned at the customer that matches the given rowId
     * @param ad_user_id
     * @return Cursor positioned to matching customer, if found, else null
     */
    public AD_User_Roles getAD_User_Roles(long ad_user_id) {
        open();

        AD_User_Roles ad_user_roles = new AD_User_Roles();

        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[] { COLUMN_AD_USER_ID,
                                COLUMN_AD_ROLE_ID}, COLUMN_AD_USER_ID + "=" + ad_user_id,
                        null, null, null, null, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            ad_user_roles.setAD_User_ID(Long.parseLong(mCursor.getString(0)));
            ad_user_roles.setAD_Role_ID(Long.parseLong(mCursor.getString(1)));
            ad_user_roles.setRowNumber(0);
        }
        else {
            close();
            return null;
        }
        close();
        return ad_user_roles;
    }

    /**
     * Return a Cursor positioned at the customer that matches the given rowId
     * @param ad_user_id
     * @return Cursor positioned to matching customer, if found, else null
     */
    public Boolean isAdmin(long ad_user_id) {
        open();

        AD_User_Roles ad_user_roles = new AD_User_Roles();

        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[] { COLUMN_AD_USER_ID,
                                COLUMN_AD_ROLE_ID}, COLUMN_AD_USER_ID + "=" + ad_user_id,
                        null, null, null, null, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            ad_user_roles.setRowNumber(0);
            if (mCursor.getString(0) == "1000000")
                return true;
        }
        close();
        return false;
    }

    /**
     * Update the customer.
     *
     * @param ad_user_roles
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateAD_User_Roles(AD_User_Roles ad_user_roles){
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_AD_ROLE_ID, ad_user_roles.getAD_Role_ID());

        return mDb.update(DATABASE_TABLE, args, COLUMN_AD_USER_ID + "=" + ad_user_roles.getAD_User_ID(), null) >0;
    }
}
