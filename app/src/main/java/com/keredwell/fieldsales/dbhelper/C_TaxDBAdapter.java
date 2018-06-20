package com.keredwell.fieldsales.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.keredwell.fieldsales.data.C_Tax;

import java.util.ArrayList;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 18/8/2017.
 */

public class C_TaxDBAdapter extends DBAdapter {
    private static final String TAG = makeLogTag(C_TaxDBAdapter.class);

    public static final String COLUMN_C_TAX_ID = "_c_tax_id";
    public static final String COLUMN_NAME = "_name";
    public static final String COLUMN_RATE = "_rate";

    public static final String DATABASE_TABLE = "c_tax";

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx
     *            the Context within which to work
     */
    public C_TaxDBAdapter(Context ctx) {
        super(ctx);
    }

    /**
     * Create a new tax. If the tax is successfully created return the
     * id for that tax, otherwise return a -1 to indicate failure.
     *
     * @param c_tax
     * @return id or -1 if failed
     */
    public long createC_Tax(C_Tax c_tax){
        open();

        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_C_TAX_ID, c_tax.getC_Tax_ID());
        initialValues.put(COLUMN_NAME, c_tax.getName());
        initialValues.put(COLUMN_RATE, c_tax.getRate());

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the tax with the given id
     *
     * @param c_tax_id
     * @return true if deleted, false otherwise
     */
    public boolean deleteC_Tax(long c_tax_id) {
        open();

        return mDb.delete(DATABASE_TABLE, COLUMN_C_TAX_ID + "=" + c_tax_id, null) > 0; //$NON-NLS-1$
    }

    /**
     * Return a Cursor over the list of all taxes in the database
     *
     * @return Cursor over all taxes
     */
    public ArrayList<String> getAllTaxesName() {
        open();

        ArrayList<String> c_taxs = new ArrayList<String>();

        Cursor mCursor =

                mDb.query(DATABASE_TABLE, new String[] { COLUMN_C_TAX_ID, COLUMN_NAME },
                        null, null, null, null, null);

        while (mCursor.moveToNext()) {
            c_taxs.add(mCursor.getString(1));
        }

        return c_taxs;
    }

    /**
     * Return a List<Tax> over the list of all taxes in the database
     *
     * @return List<Tax> over all taxes
     */
    public ArrayList<C_Tax> getAllTaxes() {
        open();

        ArrayList<C_Tax> c_taxs = new ArrayList<>();

        Cursor mCursor =

                mDb.query(DATABASE_TABLE, new String[] { COLUMN_C_TAX_ID, COLUMN_NAME, COLUMN_RATE },
                        null, null, null, null, null);

        while (mCursor.moveToNext()){
            C_Tax tax = new C_Tax();
            tax.setC_Tax_ID(Integer.parseInt(mCursor.getString(0)));
            tax.setName(mCursor.getString(1));
            tax.setRate(Integer.parseInt(mCursor.getString(2)));
            c_taxs.add(tax);
        }
        close();
        return c_taxs;
    }

    /**
     * Return a Cursor positioned at the tax that matches the given id
     * @param c_tax_id
     * @return Cursor positioned to matching tax, if found, else null
     */
    public C_Tax getC_Tax(long c_tax_id) {
        open();

        C_Tax tax = new C_Tax();

        Cursor mCursor =

                mDb.query(true, DATABASE_TABLE, new String[] { COLUMN_C_TAX_ID, COLUMN_NAME, COLUMN_RATE},
                        COLUMN_C_TAX_ID + "=" + c_tax_id, null, null, null, null, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            tax.setC_Tax_ID(Integer.parseInt(mCursor.getString(0)));
            tax.setName(mCursor.getString(1));
            tax.setRate(Integer.parseInt(mCursor.getString(2)));
        }
        else {
            close();
            return null;
        }
        close();
        return tax;
    }

    /**
     * Return a Cursor positioned at the tax that matches the given id
     * @param name
     * @return Cursor positioned to matching tax, if found, else null
     */
    public C_Tax getC_Tax(String name) {
        open();

        C_Tax c_tax = new C_Tax();

        Cursor mCursor =

                mDb.query(true, DATABASE_TABLE, new String[] { COLUMN_C_TAX_ID, COLUMN_NAME, COLUMN_RATE},
                        COLUMN_NAME + "=\"" + name + "\"", null, null, null, null, null);

        if (mCursor != null && mCursor.moveToFirst()) {
            c_tax.setC_Tax_ID(Integer.parseInt(mCursor.getString(0)));
            c_tax.setName(mCursor.getString(1));
            c_tax.setRate(Integer.parseInt(mCursor.getString(2)));
        }
        else {
            close();
            return null;
        }
        close();
        return c_tax;
    }

    /**
     * Update the tax.
     *
     * @param c_tax
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateC_Tax(C_Tax c_tax){
        open();

        ContentValues args = new ContentValues();
        args.put(COLUMN_NAME, c_tax.getName());
        args.put(COLUMN_RATE, c_tax.getRate());

        return mDb.update(DATABASE_TABLE, args, COLUMN_C_TAX_ID + "=" + c_tax.getC_Tax_ID(), null) >0;
    }
}
