package com.keredwell.fieldsales.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.keredwell.fieldsales.data.C_BP_Group;

import java.util.ArrayList;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 18/8/2017.
 */

public class C_BP_GroupDBAdapter extends DBAdapter {
    private static final String TAG = makeLogTag(C_BP_GroupDBAdapter.class);

    public static final String COLUMN_C_BP_GROUP_ID = "_c_bp_group_id";
    public static final String COLUMN_NAME = "_name";

    public static final String DATABASE_TABLE = "c_bp_group";

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx
     *            the Context within which to work
     */
    public C_BP_GroupDBAdapter(Context ctx) {
        super(ctx);
    }

    /**
     * Create a new customer. If the customer is successfully created return the
     * id for that customer, otherwise return a -1 to indicate failure.
     *
     * @param c_bp_group
     * @return rowId or -1 if failed
     */
    public long createC_BP_Group(C_BP_Group c_bp_group){
        open();

        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_C_BP_GROUP_ID, c_bp_group.getC_BP_Group_ID());
        initialValues.put(COLUMN_NAME, c_bp_group.getName());

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the user with the given id
     *
     * @param c_bp_group_id
     * @return true if deleted, false otherwise
     */
    public boolean deleteC_BP_Group(long c_bp_group_id) {
        open();

        return mDb.delete(DATABASE_TABLE, COLUMN_C_BP_GROUP_ID + "=" + c_bp_group_id, null) > 0; //$NON-NLS-1$
    }

    /**
     * Return a List<Customer>  over the list of all customers in the database
     *
     * @return List<Customer>  over all customers
     */
    public ArrayList<String> getAllC_BP_Groups() {
        open();

        ArrayList<String> c_bp_groups = new ArrayList<>();

        Cursor mCursor =

                mDb.query(DATABASE_TABLE, new String[] { COLUMN_NAME },
                        null, null, null, null, COLUMN_NAME);

        while (mCursor.moveToNext()){
            c_bp_groups.add(mCursor.getString(0));
        }
        close();

        return c_bp_groups;
    }

    /**
     * Return a Cursor positioned at the customer that matches the given rowId
     * @param name
     * @return Cursor positioned to matching customer, if found, else null
     */
    public C_BP_Group getC_BP_Group(String name) {
        open();

        C_BP_Group c_bp_group = new C_BP_Group();

        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[] { COLUMN_NAME,
                                COLUMN_C_BP_GROUP_ID}, COLUMN_NAME + "=\"" + name + "\"",
                        null, null, null, null, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            c_bp_group.setName(mCursor.getString(0));
            c_bp_group.setC_BP_Group_ID(Long.parseLong(mCursor.getString(1)));
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
     * @param c_bp_group_id
     * @return Cursor positioned to matching customer, if found, else null
     */
    public C_BP_Group getC_BP_Group(long c_bp_group_id) {
        open();

        C_BP_Group c_bp_group = new C_BP_Group();

        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[] { COLUMN_NAME,
                                COLUMN_C_BP_GROUP_ID}, COLUMN_C_BP_GROUP_ID + "=" + c_bp_group_id,
                        null, null, null, null, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            c_bp_group.setName(mCursor.getString(0));
            c_bp_group.setC_BP_Group_ID(Integer.parseInt(mCursor.getString(1)));
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
     * @param c_bp_group
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateC_BP_Group(C_BP_Group c_bp_group){
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_NAME, c_bp_group.getName());

        return mDb.update(DATABASE_TABLE, args, COLUMN_C_BP_GROUP_ID + "=" + c_bp_group.getC_BP_Group_ID(), null) >0;
    }
}
