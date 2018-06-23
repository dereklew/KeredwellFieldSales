package com.keredwell.fieldsales.data;

import java.io.Serializable;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class AD_User implements Serializable {
    private static final String TAG = makeLogTag(AD_User.class);

    private long _ad_user_id;
    private long _c_bpartner_id;
    private String _name;

    public AD_User() {
    }

    public AD_User(long ad_user_id, long c_bpartner_id, String name) {
        this._ad_user_id = ad_user_id;
        this._c_bpartner_id = c_bpartner_id;
        this._name = name;
    }

    public void setAD_User_ID(long ad_user_id) {
        this._ad_user_id = ad_user_id;
    }

    public long getAD_User_ID() {
        return this._ad_user_id;
    }

    public void setC_BPartner_ID(long c_bpartner_id) { this._c_bpartner_id = c_bpartner_id; }

    public long getC_BPartner_ID() {
        return this._c_bpartner_id;
    }

    public void setName(String name) { this._name = name; }

    public String getName() {
        return this._name;
    }
}
