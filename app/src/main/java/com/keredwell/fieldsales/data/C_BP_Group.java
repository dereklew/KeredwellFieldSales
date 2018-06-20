package com.keredwell.fieldsales.data;

import java.io.Serializable;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 14/8/2017.
 */

public class C_BP_Group implements Serializable {
    private static final String TAG = makeLogTag(C_BP_Group.class);

    private long _c_bp_group_id;
    private String _name;
    private int _rownumber;

    public C_BP_Group(){
    }

    public C_BP_Group(long c_bp_group_id, String name) {
        this._c_bp_group_id = c_bp_group_id;
        this._name = name;
    }

    public void setC_BP_Group_ID(long c_bp_group_id) {
        this._c_bp_group_id = c_bp_group_id;
    }

    public long getC_BP_Group_ID() {
        return this._c_bp_group_id;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getName() {
        return this._name;
    }

    public void setRowNumber(int rownumber) {
        this._rownumber = rownumber;
    }

    public int getRowNumber() {
        return this._rownumber;
    }
}
