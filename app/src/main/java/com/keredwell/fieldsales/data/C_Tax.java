package com.keredwell.fieldsales.data;

import java.io.Serializable;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 14/8/2017.
 */

public class C_Tax implements Serializable {
    private static final String TAG = makeLogTag(C_Tax.class);

    private long _c_tax_id;
    private String _name;
    private int _rate;
    private int _rownumber;

    public C_Tax(){
    }

    public C_Tax(int c_tax_id, String name, int rate) {
        this._c_tax_id = c_tax_id;
        this._name = name;
        this._rate = rate;
    }

    public void setC_Tax_ID(long c_tax_id) {
        this._c_tax_id = c_tax_id;
    }

    public long getC_Tax_ID() {
        return this._c_tax_id;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getName() {
        return this._name;
    }

    public void setRate(int rate) {
        this._rate = rate;
    }

    public int getRate() { return this._rate; }

    public void setRowNumber(int rownumber) {
        this._rownumber = rownumber;
    }

    public int getRowNumber() {
        return this._rownumber;
    }
}
