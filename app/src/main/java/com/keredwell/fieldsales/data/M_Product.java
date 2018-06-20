package com.keredwell.fieldsales.data;

import java.io.Serializable;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 14/8/2017.
 */

public class M_Product implements Serializable {
    private static final String TAG = makeLogTag(M_Product.class);

    private long _m_product_id;
    private String _name;
    private long _c_uom_id;
    private long _m_product_category_id;
    private long _m_locator_id;
    private long _m_pricelist_id;
    private int _pricelist;
    private int _pricestd;
    private int _pricelimit;
    private int _rownumber;

    public M_Product(){
    }

    public M_Product(long m_product_id, String name, long c_uom_id, long m_product_category_id, long m_locator_id) {
        this._m_product_id = m_product_id;
        this._name = name;
        this._c_uom_id = c_uom_id;
        this._m_product_category_id = m_product_category_id;
        this._m_locator_id = m_locator_id;
    }

    public void setM_Product_ID(long m_product_id) {
        this._m_product_id = m_product_id;
    }

    public long getM_Product_ID() {
        return this._m_product_id;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getName() {
        return this._name;
    }

    public void setC_Uom_ID(long c_uom_id) {
        this._c_uom_id = c_uom_id;
    }

    public long getC_Uom_ID() {
        return this._c_uom_id;
    }

    public void setM_Product_Category_ID(long m_product_category_id) { this._m_product_category_id = m_product_category_id; }

    public long getM_Product_Category_ID() {
        return this._m_product_category_id;
    }

    public void setM_Locator_ID(long m_locator_id) {
        this._m_locator_id = m_locator_id;
    }

    public long getM_Locator_ID() {
        return this._m_locator_id;
    }

    public void setM_ProductList_ID(long m_pricelist_id) {
        this._m_pricelist_id = m_pricelist_id;
    }

    public long getM_ProductList_ID() {
        return this._m_pricelist_id;
    }

    public void setPriceList(int pricelist) {
        this._pricelist = pricelist;
    }

    public int getPriceList() {
        return this._pricelist;
    }

    public void setPriceStd(int pricestd) {
        this._pricestd = pricestd;
    }

    public int getPriceStd() { return this._pricestd; }

    public void setPriceLimit(int pricelimit) {
        this._pricelimit = pricelimit;
    }

    public int getPriceLimit() { return this._pricelimit; }

    public void setRowNumber(int rownumber) {
        this._rownumber = rownumber;
    }

    public int getRowNumber() {
        return this._rownumber;
    }
}
