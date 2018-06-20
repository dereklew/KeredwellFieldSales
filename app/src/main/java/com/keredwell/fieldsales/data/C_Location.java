package com.keredwell.fieldsales.data;

import java.io.Serializable;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 14/8/2017.
 */

public class C_Location implements Serializable {
    private static final String TAG = makeLogTag(C_Location.class);

    private long _c_location_id;
    private String _address;
    private String _postal;
    private int _rownumber;

    public C_Location(){
    }

    public C_Location(long c_location_id, String address, String postal) {
        this._c_location_id = c_location_id;
        this._address = address;
        this._postal = postal;
    }

    public void setC_Location_ID(long c_location_id) {
        this._c_location_id = c_location_id;
    }

    public long getC_Location_ID() { return this._c_location_id; }

    public void setAddress(String address) {
        this._address = address;
    }

    public String getAddress() {
        return this._address;
    }

    public void setPostal(String postal) {
        this._postal = postal;
    }

    public String getPostal() {
        return this._postal;
    }

    public void setRowNumber(int rownumber) {
        this._rownumber = rownumber;
    }

    public int getRowNumber() {
        return this._rownumber;
    }
}
