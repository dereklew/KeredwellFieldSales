package com.keredwell.fieldsales.data;

import java.io.Serializable;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 14/8/2017.
 */

public class M_Locator implements Serializable {
    private static final String TAG = makeLogTag(M_Locator.class);

    private long _m_locator_id;
    private long _m_warehouse_id;
    private String _x_aisle;
    private String _y_bin;
    private String _z_level;
    private int _rownumber;

    public M_Locator(){
    }

    public M_Locator(int m_locator_id, int m_warehouse_id, String x_aisle, String y_bin, String z_level) {
        this._m_locator_id = m_warehouse_id;
        this._m_warehouse_id = m_warehouse_id;
        this._x_aisle = x_aisle;
        this._y_bin = y_bin;
        this._z_level = z_level;
    }

    public void setM_Locator_ID(long m_locator_id) { this._m_locator_id = m_locator_id; }

    public long getM_Locator_ID() { return this._m_locator_id; }

    public void setM_Warehouse_ID(long m_warehouse_id) {
        this._m_warehouse_id = m_warehouse_id;
    }

    public long getM_Warehouse_ID() { return this._m_warehouse_id; }

    public void setX_Aisle(String x_aisle) { this._x_aisle = x_aisle; }

    public String getX_Aisle() {
        return this._x_aisle;
    }

    public void setY_Bin(String y_bin) {
        this._y_bin = y_bin;
    }

    public String getY_Bin() {
        return this._y_bin;
    }

    public void setZ_Level(String z_level) {
        this._z_level = z_level;
    }

    public String getZ_Level() { return this._z_level; }

    public void setRowNumber(int rownumber) {
        this._rownumber = rownumber;
    }

    public int getRowNumber() {
        return this._rownumber;
    }
}
