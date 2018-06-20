package com.keredwell.fieldsales.data;

import java.io.Serializable;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 14/8/2017.
 */

public class C_BPartner_Location implements Serializable {
    private static final String TAG = makeLogTag(C_BPartner_Location.class);

    private long _c_bpartner_id;
    private long _c_location_id;
    private long _c_bpartner_location_id;
    private String _phone;
    private int _rownumber;

    public C_BPartner_Location(){
    }

    public C_BPartner_Location(long c_bpartner_id, long c_location_id, long c_bpartner_location_id, String phone) {
        this._c_bpartner_id = c_bpartner_id;
        this._c_location_id = c_location_id;
        this._c_bpartner_location_id = c_bpartner_location_id;
        this._phone = phone;
    }

    public void setC_BPartner_ID(long c_bpartner_id) {
        this._c_bpartner_id = c_bpartner_id;
    }

    public long getC_BPartner_ID() {
        return this._c_bpartner_id;
    }

    public void setC_Location_ID(long c_location_id) {
        this._c_location_id = c_location_id;
    }

    public long getC_Location_ID() {
        return this._c_location_id;
    }

    public void setC_BPartner_Location_ID(long c_bpartner_location_id) { this._c_bpartner_location_id = c_bpartner_location_id; }

    public long getC_BPartner_Location_ID() { return this._c_bpartner_location_id; }

    public void setPhone(String phone) {
        this._phone = phone;
    }

    public String getPhone() {
        return this._phone;
    }

    public void setRowNumber(int rownumber) {
        this._rownumber = rownumber;
    }

    public int getRowNumber() {
        return this._rownumber;
    }
}
