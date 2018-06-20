package com.keredwell.fieldsales.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.keredwell.fieldsales.data.C_BPartner;

import java.util.ArrayList;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 18/8/2017.
 */

public class C_BPartnerDBAdapter extends DBAdapter {
    private static final String TAG = makeLogTag(C_BPartnerDBAdapter.class);

    public static final String COLUMN_C_BPARTNER_ID = "_c_bpartner_id";
    public static final String COLUMN_C_BP_GROUP_ID = "_c_bp_group_id";
    public static final String COLUMN_NAME = "_name";

    public static final String DATABASE_TABLE = "c_bpartner";

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx
     *            the Context within which to work
     */
    public C_BPartnerDBAdapter(Context ctx) {
        super(ctx);
    }

    /**
     * Create a new customer. If the customer is successfully created return the
     * id for that customer, otherwise return a -1 to indicate failure.
     *
     * @param c_bPartner
     * @return rowId or -1 if failed
     */
    public long createC_BPartner(C_BPartner c_bPartner){
        open();

        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_C_BPARTNER_ID, c_bPartner.getC_BPartner_ID());
        initialValues.put(COLUMN_C_BP_GROUP_ID, c_bPartner.getC_BP_Group_ID());
        initialValues.put(COLUMN_NAME, c_bPartner.getName());

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the user with the given id
     *
     * @param c_bpartner_id
     * @return true if deleted, false otherwise
     */
    public boolean deleteC_BPartner(long c_bpartner_id) {
        open();

        return mDb.delete(DATABASE_TABLE, COLUMN_C_BPARTNER_ID + "=" + c_bpartner_id, null) > 0; //$NON-NLS-1$
    }

    /**
     * Return a List<Customer>  over the list of all customers in the database
     *
     * @param groupid
     * @return List<Customer>  over all customers
     */
    public ArrayList<C_BPartner> getAllC_BPartnersByGroupID(long groupid) {
        open();

        ArrayList<C_BPartner> c_bPartners = new ArrayList<C_BPartner>();

        String selectQuery = "SELECT (SELECT COUNT(0) from " + DATABASE_TABLE + " t1 where t1." + COLUMN_NAME + " >= t2." + COLUMN_NAME +
                " ) as 'RowNumber', t2." + COLUMN_C_BPARTNER_ID + ", " + COLUMN_NAME +  ", " + C_BPartner_LocationDBAdapter.COLUMN_PHONE
                +  ", " + C_BPartner_LocationDBAdapter.COLUMN_C_BPARTNER_LOCATION_ID +   ", " + C_LocationDBAdapter.COLUMN_ADDRESS +  ", " + C_LocationDBAdapter.COLUMN_POSTAL
                + " FROM "
                + DATABASE_TABLE + " t2 INNER JOIN " + C_BPartner_LocationDBAdapter.DATABASE_TABLE + " t3 ON t2." + COLUMN_C_BPARTNER_ID + " = t3." + C_BPartner_LocationDBAdapter.COLUMN_C_BPARTNER_ID
                + " INNER JOIN " + C_LocationDBAdapter.DATABASE_TABLE + " t4 ON t3." + C_BPartner_LocationDBAdapter.COLUMN_C_LOCATION_ID + " = t4." + C_LocationDBAdapter.COLUMN_C_LOCATION_ID
                + " WHERE " + COLUMN_C_BP_GROUP_ID + " = " + groupid + " ORDER BY " + COLUMN_NAME;

        Cursor mCursor = mDb.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        while (mCursor.moveToNext()){
            C_BPartner c_bpartner = new C_BPartner();
            c_bpartner.setRowNumber(Integer.parseInt(mCursor.getString(0)));
            c_bpartner.setC_BPartner_ID(Long.parseLong(mCursor.getString(1)));
            c_bpartner.setName(mCursor.getString(2));
            c_bpartner.setPhone(mCursor.getString(3));
            c_bpartner.setC_BPartner_Location_ID(Long.parseLong(mCursor.getString(4)));
            c_bpartner.setAddress(mCursor.getString(5));
            c_bpartner.setPostal(mCursor.getString(6));
            c_bPartners.add(c_bpartner);
        }
        close();
        return c_bPartners;
    }

    /**
     * Return a List<Customer>  over the list of all customers in the database
     *
     * @param searchterm
     * @return List<Customer>  over all customers
     */
    public ArrayList<C_BPartner> getAllC_BPartnersBySearch(String searchterm) {
        open();

        ArrayList<C_BPartner> c_bPartners = new ArrayList<C_BPartner>();

        String selectQuery = "SELECT (SELECT COUNT(0) from " + DATABASE_TABLE + " t1 where t1." + COLUMN_NAME + " >= t2." + COLUMN_NAME +
                " ) as 'RowNumber', t2." + COLUMN_C_BPARTNER_ID + ", " + COLUMN_NAME +  ", " + C_BPartner_LocationDBAdapter.COLUMN_PHONE
                +  ", " + C_BPartner_LocationDBAdapter.COLUMN_C_BPARTNER_LOCATION_ID +   ", " + C_LocationDBAdapter.COLUMN_ADDRESS +  ", " + C_LocationDBAdapter.COLUMN_POSTAL
                + " FROM "
                + DATABASE_TABLE + " t2 INNER JOIN " + C_BPartner_LocationDBAdapter.DATABASE_TABLE + " t3 ON t2." + COLUMN_C_BPARTNER_ID + " = t3." + C_BPartner_LocationDBAdapter.COLUMN_C_BPARTNER_ID
                + " INNER JOIN " + C_LocationDBAdapter.DATABASE_TABLE + " t4 ON t3." + C_BPartner_LocationDBAdapter.COLUMN_C_LOCATION_ID + " = t4." + C_LocationDBAdapter.COLUMN_C_LOCATION_ID
                + " WHERE " + COLUMN_NAME + " LIKE '%" + searchterm + "%' OR " + C_LocationDBAdapter.COLUMN_ADDRESS + " LIKE '%" + searchterm + "%' ORDER BY " + COLUMN_NAME;

        Cursor mCursor = mDb.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        while (mCursor.moveToNext()){
            C_BPartner c_bpartner = new C_BPartner();
            c_bpartner.setRowNumber(Integer.parseInt(mCursor.getString(0)));
            c_bpartner.setC_BPartner_ID(Long.parseLong(mCursor.getString(1)));
            c_bpartner.setName(mCursor.getString(2));
            c_bpartner.setPhone(mCursor.getString(3));
            c_bpartner.setC_BPartner_Location_ID(Long.parseLong(mCursor.getString(4)));
            c_bpartner.setAddress(mCursor.getString(5));
            c_bpartner.setPostal(mCursor.getString(6));
            c_bPartners.add(c_bpartner);
        }
        close();
        return c_bPartners;
    }

    /**
     * Return a Cursor positioned at the customer that matches the given rowId
     * @param name
     * @return Cursor positioned to matching customer, if found, else null
     */
    public C_BPartner getC_BPartner(String name) {
        open();

        C_BPartner c_bpartner = new C_BPartner();

        String selectQuery = "SELECT (SELECT COUNT(0) from " + DATABASE_TABLE + " t1 where t1." + COLUMN_NAME + " >= t2." + COLUMN_NAME +
                " ) as 'RowNumber', t2." + COLUMN_C_BPARTNER_ID + ", " + COLUMN_NAME +  ", " + C_BPartner_LocationDBAdapter.COLUMN_PHONE
                +  ", " + C_BPartner_LocationDBAdapter.COLUMN_C_BPARTNER_LOCATION_ID +   ", " + C_LocationDBAdapter.COLUMN_ADDRESS +  ", " + C_LocationDBAdapter.COLUMN_POSTAL
                + " FROM "
                + DATABASE_TABLE + " t2 INNER JOIN " + C_BPartner_LocationDBAdapter.DATABASE_TABLE + " t3 ON t2." + COLUMN_C_BPARTNER_ID + " = t3." + C_BPartner_LocationDBAdapter.COLUMN_C_BPARTNER_ID
                + " INNER JOIN " + C_LocationDBAdapter.DATABASE_TABLE + " t4 ON t3." + C_BPartner_LocationDBAdapter.COLUMN_C_LOCATION_ID + " = t4." + C_LocationDBAdapter.COLUMN_C_LOCATION_ID
                + " WHERE " + COLUMN_NAME + " = " + name + " ORDER BY " + COLUMN_NAME;

        Cursor mCursor = mDb.rawQuery(selectQuery, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            c_bpartner.setRowNumber(Integer.parseInt(mCursor.getString(0)));
            c_bpartner.setC_BPartner_ID(Long.parseLong(mCursor.getString(1)));
            c_bpartner.setName(mCursor.getString(2));
            c_bpartner.setPhone(mCursor.getString(3));
            c_bpartner.setC_BPartner_Location_ID(Long.parseLong(mCursor.getString(4)));
            c_bpartner.setAddress(mCursor.getString(5));
            c_bpartner.setPostal(mCursor.getString(6));
        }
        else {
            close();
            return null;
        }
        close();
        return c_bpartner;
    }

    /**
     * Return a Cursor positioned at the customer that matches the given rowId
     * @param c_bpartner_id
     * @return Cursor positioned to matching customer, if found, else null
     */
    public C_BPartner getC_BPartner(long c_bpartner_id) {
        open();

        C_BPartner c_bpartner = new C_BPartner();

        String selectQuery = "SELECT (SELECT COUNT(0) from " + DATABASE_TABLE + " t1 where t1." + COLUMN_NAME + " >= t2." + COLUMN_NAME +
                " ) as 'RowNumber', t2." + COLUMN_C_BPARTNER_ID + ", " + COLUMN_NAME +  ", " + C_BPartner_LocationDBAdapter.COLUMN_PHONE
                +  ", " + C_BPartner_LocationDBAdapter.COLUMN_C_BPARTNER_LOCATION_ID +   ", " + C_LocationDBAdapter.COLUMN_ADDRESS +  ", " + C_LocationDBAdapter.COLUMN_POSTAL
                + " FROM "
                + DATABASE_TABLE + " t2 INNER JOIN " + C_BPartner_LocationDBAdapter.DATABASE_TABLE + " t3 ON t2." + COLUMN_C_BPARTNER_ID + " = t3." + C_BPartner_LocationDBAdapter.COLUMN_C_BPARTNER_ID
                + " INNER JOIN " + C_LocationDBAdapter.DATABASE_TABLE + " t4 ON t3." + C_BPartner_LocationDBAdapter.COLUMN_C_LOCATION_ID + " = t4." + C_LocationDBAdapter.COLUMN_C_LOCATION_ID
                + " WHERE t2." + COLUMN_C_BPARTNER_ID + " = " + c_bpartner_id + " ORDER BY t2." + COLUMN_C_BPARTNER_ID;

        Cursor mCursor = mDb.rawQuery(selectQuery, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            c_bpartner.setRowNumber(Integer.parseInt(mCursor.getString(0)));
            c_bpartner.setC_BPartner_ID(Long.parseLong(mCursor.getString(1)));
            c_bpartner.setName(mCursor.getString(2));
            c_bpartner.setPhone(mCursor.getString(3));
            c_bpartner.setC_BPartner_Location_ID(Long.parseLong(mCursor.getString(4)));
            c_bpartner.setAddress(mCursor.getString(5));
            c_bpartner.setPostal(mCursor.getString(6));
        }
        else {
            close();
            return null;
        }
        close();
        return c_bpartner;
    }

    /**
     * Update the customer.
     *
     * @param c_bpartner
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateC_BPartner(C_BPartner c_bpartner){
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_NAME, c_bpartner.getName());
        args.put(COLUMN_C_BP_GROUP_ID, c_bpartner.getC_BP_Group_ID());

        return mDb.update(DATABASE_TABLE, args, COLUMN_C_BPARTNER_ID + "=" + c_bpartner.getC_BPartner_ID(), null) >0;
    }
}
