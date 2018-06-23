package com.keredwell.fieldsales.data;

import java.io.Serializable;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 14/8/2017.
 */

public class AD_User_Roles implements Serializable {
    private static final String TAG = makeLogTag(AD_User_Roles.class);

    private long _ad_user_id;
    private long _ad_role_id;
    private int _rownumber;

    public AD_User_Roles(){
    }

    public AD_User_Roles(long ad_user_id, long ad_role_id) {
        this._ad_user_id = ad_user_id;
        this._ad_role_id = ad_role_id;
    }

    public void setAD_User_ID(long ad_user_id) {
        this._ad_user_id = ad_user_id;
    }

    public long getAD_User_ID() {
        return this._ad_user_id;
    }

    public void setAD_Role_ID(long ad_role_id) { this._ad_role_id = ad_role_id; }

    public long getAD_Role_ID() {
        return this._ad_role_id;
    }

    public void setRowNumber(int rownumber) {
        this._rownumber = rownumber;
    }

    public int getRowNumber() {
        return this._rownumber;
    }
}
