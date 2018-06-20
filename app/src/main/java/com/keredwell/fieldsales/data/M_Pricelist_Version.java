package com.keredwell.fieldsales.data;

import java.io.Serializable;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 14/8/2017.
 */

public class M_Pricelist_Version implements Serializable {
    private static final String TAG = makeLogTag(M_Pricelist_Version.class);

    private long _m_pricelist_version_id;
    private long _m_pricelist_id;
    private int _rownumber;

    public M_Pricelist_Version(){
    }

    public M_Pricelist_Version(int m_pricelist_version_id, int m_pricelist_id) {
        this._m_pricelist_version_id = m_pricelist_version_id;
        this._m_pricelist_id = m_pricelist_id;
    }

    public void setM_Pricelist_Version_ID(long m_pricelist_version_id) { this._m_pricelist_version_id = m_pricelist_version_id; }

    public long getM_Pricelist_Version_ID() {
        return this._m_pricelist_version_id;
    }

    public void setM_Pricelist_ID(long m_pricelist_id) {
        this._m_pricelist_id = m_pricelist_id;
    }

    public long getM_Pricelist_ID() { return this._m_pricelist_id; }

    public void setRowNumber(int rownumber) {
        this._rownumber = rownumber;
    }

    public int getRowNumber() {
        return this._rownumber;
    }
}
