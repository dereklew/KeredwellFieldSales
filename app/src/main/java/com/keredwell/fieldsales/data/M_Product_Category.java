package com.keredwell.fieldsales.data;

import java.io.Serializable;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 14/8/2017.
 */

public class M_Product_Category implements Serializable {
    private static final String TAG = makeLogTag(M_Product_Category.class);

    private long _m_product_category_id;
    private String _name;
    private int _rownumber;

    public M_Product_Category(){
    }

    public M_Product_Category(int m_product_category_id, String name) {
        this._m_product_category_id = m_product_category_id;
        this._name = name;
    }

    public void setM_Product_Category_ID(long m_product_category_id) { this._m_product_category_id = m_product_category_id; }

    public long getM_Product_Category_ID() {
        return this._m_product_category_id;
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
