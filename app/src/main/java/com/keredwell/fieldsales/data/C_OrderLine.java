package com.keredwell.fieldsales.data;

import java.io.Serializable;
import java.util.Date;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 14/8/2017.
 */

public class C_OrderLine implements Serializable {
    private static final String TAG = makeLogTag(C_OrderLine.class);

    private long _id;
    private long _orderid;
    private long _c_order_id;
    private long _c_orderline_id;
    private int _lineno;
    private long _c_bpartner_id;
    private long _c_bpartner_location_id;
    private Date _dateordered;
    private long _m_product_id;
    private String _productname;
    private long _m_warehouse_id;
    private long _m_locator_id;
    private long _c_uom_id;
    private int _qtyordered;
    private long _m_pricelist_id;;
    private int _pricelist;
    private int _priceactual;
    private int _pricelimit;
    private int _linenetamt;
    private int _discount;
    private long _c_tax_id;
    private Date _syncdate;
    private int _rownumber;

    public C_OrderLine() {
    }

    public C_OrderLine(long orderid, int lineno, long c_bpartner_id, long c_bpartner_location_id, Date dateordered,
                       long m_product_id, String productname, long m_warehouse_id, long m_locator_id,
                       long c_uom_id, int qtyordered, long m_pricelist_id,
                       int pricelist, int priceactual, int pricelimit, int linenetamt, int discount,
                       long c_tax_id) {
        this._orderid = orderid;
        this._lineno = lineno;
        this._c_bpartner_id = c_bpartner_id;
        this._c_bpartner_location_id = c_bpartner_location_id;
        this._dateordered = dateordered;
        this._m_product_id = m_product_id;
        this._productname = productname;
        this._m_warehouse_id = m_warehouse_id;
        this._m_locator_id = m_locator_id;
        this._c_uom_id = c_uom_id;
        this._qtyordered = qtyordered;
        this._m_pricelist_id = m_pricelist_id;
        this._pricelist = pricelist;
        this._priceactual = priceactual;
        this._pricelimit = pricelimit;
        this._linenetamt = linenetamt;
        this._discount = discount;
        this._c_tax_id = c_tax_id;
    }

    public void setID(long id) {
        this._id = id;
    }

    public long getID() {
        return this._id;
    }

    public void setOrderID(long orderid) {
        this._orderid = orderid;
    }

    public long getOrderID() {
        return this._orderid;
    }

    public void setC_Order_ID(long c_order_id) {
        this._c_order_id = c_order_id;
    }

    public long getC_Order_ID() {
        return this._c_order_id;
    }

    public void setC_OrderLine_ID(long c_orderline_id) {
        this._c_orderline_id = c_orderline_id;
    }

    public long getC_OrderLine_ID() { return this._c_orderline_id; }

    public void setLineNo(int lineno) {
        this._lineno = lineno;
    }

    public int getLineNo() {
        return this._lineno;
    }

    public void setC_BPartner_ID(long c_bpartner_id) { this._c_bpartner_id = c_bpartner_id; }

    public long getC_BPartner_ID() {
        return this._c_bpartner_id;
    }

    public void setC_BPartner_Location_ID(long c_bpartner_location_id) { this._c_bpartner_location_id = c_bpartner_location_id; }

    public long getC_BPartner_Location_ID() {
        return this._c_bpartner_location_id;
    }

    public void setDateOrdered(Date dateordered) {
        this._dateordered = dateordered;
    }

    public Date getDateOrdered() {
        return this._dateordered;
    }

    public void setM_Product_ID(long m_product_id) {
        this._m_product_id = m_product_id;
    }

    public long getM_Product_ID() {
        return this._m_product_id;
    }

    public void setProductName(String productname) {
        this._productname = productname;
    }

    public String getProductName() {
        return this._productname;
    }

    public void setM_Warehouse_ID(long m_warehouse_id) {
        this._m_warehouse_id = m_warehouse_id;
    }

    public long getM_Warehouse_ID() {
        return this._m_warehouse_id;
    }

    public void setM_Locator_ID(long m_locator_id) { this._m_locator_id = m_locator_id; }

    public long getM_Locator_ID() { return this._m_locator_id; }

    public void setC_Uom_ID(long c_uom_id) { this._c_uom_id = c_uom_id; }

    public long getC_Uom_ID() {
        return this._c_uom_id;
    }

    public void setQtyOrdered(int qtyordered) {
        this._qtyordered = qtyordered;
    }

    public int getQtyOrdered() {
        return this._qtyordered;
    }

    public void setM_Pricelist_ID(long m_pricelist_id) { this._m_pricelist_id = m_pricelist_id; }

    public long getM_Pricelist_ID() {
        return this._m_pricelist_id;
    }

    public void setPriceList(int pricelist) {
        this._pricelist = pricelist;
    }

    public int getPriceList() {
        return this._pricelist;
    }

    public void setPriceActual(int priceactual) {
        this._priceactual = priceactual;
    }

    public int getPriceActual() {
        return this._priceactual;
    }

    public void setPriceLimit(int pricelimit) {
        this._pricelimit = pricelimit;
    }

    public int getPriceLimit() {
        return this._pricelimit;
    }

    public void setLineNetAmt(int linenetamt) {
        this._linenetamt = linenetamt;
    }

    public int getLineNetAmt() {
        return this._linenetamt;
    }

    public void setDiscount(int discount) {
        this._discount = discount;
    }

    public int getDiscount() { return this._discount; }

    public void setC_Tax_ID(long c_tax_id) {
        this._c_tax_id = c_tax_id;
    }

    public long getC_Tax_ID() {
        return this._c_tax_id;
    }

    public void setSyncDate(Date syncdate) {
        this._syncdate = syncdate;
    }

    public Date getSyncDate() {
        return this._syncdate;
    }

    public void setRowNumber(int rownumber) {
        this._rownumber = rownumber;
    }

    public int getRowNumber() {
        return this._rownumber;
    }
}
