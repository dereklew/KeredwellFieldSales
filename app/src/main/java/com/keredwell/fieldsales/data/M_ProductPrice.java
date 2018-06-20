package com.keredwell.fieldsales.data;

import java.io.Serializable;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 14/8/2017.
 */

public class M_ProductPrice implements Serializable {
    private static final String TAG = makeLogTag(M_ProductPrice.class);

    private long _m_product_id;
    private long _m_pricelist_version_id;
    private int _pricelist;
    private int _pricestd;
    private int _pricelimit;
    private int _rownumber;

    public M_ProductPrice(){
    }

    public M_ProductPrice(long m_product_id, int pricelist, int pricestd, int pricelimit) {
        this._m_product_id = m_product_id;
        this._pricelist = pricelist;
        this._pricestd = pricestd;
        this._pricelimit = pricelimit;
    }

    public void setM_Product_ID(long m_product_id) {
        this._m_product_id = m_product_id;
    }

    public long getM_Product_ID() {
        return this._m_product_id;
    }

    public void setM_Pricelist_Version_ID(long m_pricelist_version_id) { this._m_pricelist_version_id = m_pricelist_version_id; }

    public long getM_Pricelist_Version_ID() { return this._m_pricelist_version_id; }

    public void setPriceList(int pricelist) {
        this._pricelist = pricelist;
    }

    public int getPriceList() {
        return this._pricelist;
    }

    public void setPriceStd(int pricestd) {
        this._pricestd = pricestd;
    }

    public int getPriceStd() {
        return this._pricestd;
    }

    public void setPriceLimit(int pricelimit) { this._pricelimit = pricelimit; }

    public int getPriceLimit() {
        return this._pricelimit;
    }

    public void setRowNumber(int rownumber) {
        this._rownumber = rownumber;
    }

    public int getRowNumber() {
        return this._rownumber;
    }
}
