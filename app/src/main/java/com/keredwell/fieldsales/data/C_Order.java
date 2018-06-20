package com.keredwell.fieldsales.data;

import java.io.Serializable;
import java.util.Date;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 14/8/2017.
 */

public class C_Order implements Serializable {
    private static final String TAG = makeLogTag(C_Order.class);

    public static final int STATUS_INCOMPLETE = 0;
    public static final int STATUS_COMPLETED = 1;
    public static final int STATUS_SYNCED = 2;
    public static final int STATUS_PROCESSED = 3;

    private long _id;
    private long _c_order_id;
    private long _c_doctype_id;
    private long _c_doctypetarget_id;
    private long _salesrep_id;
    private Date _dateordered;
    private long _c_bpartner_id;
    private String _customername;
    private long _c_bpartner_location_id;
    private long _c_bill_bpartner_id;
    private long _c_bill_location_id;
    private int _totallines;
    private int _grandtotal;
    private long _m_warehouse_id;
    private long _m_pricelist_id;
    private String _paymentrule;
    private long _c_paymentterm_id;
    private boolean _iscash;
    private Date _syncdate;
    private String _processOrder_Retval;
    private Date _syncdateProcessOrder;
    private long _m_inout_id;
    private long _c_invoice_id;
    private int _internal_status;
    private long _documentid;
    private int _rownumber;

    public C_Order() {
    }

    public C_Order(int c_doctype_id, int c_doctypetarget_id, long salesrep_id, Date dateordered, long c_bpartner_id, String customername,
                   long c_bpartner_location_id,  long c_bill_bpartner_id, long c_bill_location_id, int totallines, int grandtotal,
                   long m_warehouse_id, long m_pricelist_id, String paymentrule, long c_paymentterm_id, boolean iscash, int internal_status) {
        this._c_doctype_id = c_doctype_id;
        this._c_doctypetarget_id = c_doctypetarget_id;
        this._salesrep_id = salesrep_id;
        this._dateordered = dateordered;
        this._c_bpartner_id = c_bpartner_id;
        this._customername = customername;
        this._c_bpartner_location_id = c_bpartner_location_id;
        this._c_bill_bpartner_id = c_bill_bpartner_id;
        this._c_bill_location_id = c_bill_location_id;
        this._totallines = totallines;
        this._grandtotal = grandtotal;
        this._m_warehouse_id = m_warehouse_id;
        this._m_pricelist_id = m_pricelist_id;
        this._paymentrule = paymentrule;
        this._c_paymentterm_id = c_paymentterm_id;
        this._iscash = iscash;
        this._internal_status = internal_status;
    }

    public void setID(long id) {
        this._id = id;
    }

    public long getID() {
        return this._id;
    }

    public void setC_Order_ID(long c_order_id) {
        this._c_order_id = c_order_id;
    }

    public long getC_Order_ID() {
        return this._c_order_id;
    }

    public void setC_DocType_ID(long c_doctype_id) {
        this._c_doctype_id = c_doctype_id;
    }

    public long getC_DocType_ID() {
        return this._c_doctype_id;
    }

    public void setC_DocTypeTarget_ID(long c_doctypetarget_id) { this._c_doctypetarget_id = c_doctypetarget_id; }

    public long getC_DocTypeTarget_ID() {
        return this._c_doctypetarget_id;
    }

    public void setSalesRep_ID(long salesrep_id) {
        this._salesrep_id = salesrep_id;
    }

    public long getSalesRep_ID() {
        return this._salesrep_id;
    }

    public void setDateOrdered(Date dateordered) { this._dateordered = dateordered; }

    public Date getDateOrdered() {
        return this._dateordered;
    }

    public void setC_BPartner_ID(long c_bpartner_id) { this._c_bpartner_id = c_bpartner_id; }

    public long getC_BPartner_ID() {
        return this._c_bpartner_id;
    }

    public void setCustomerName(String customername) {
        this._customername = customername;
    }

    public String getCustomerName() { return this._customername; }

    public void setC_BPartner_Location_ID(long c_bpartner_location_id) { this._c_bpartner_location_id = c_bpartner_location_id; }

    public long getC_BPartner_Location_ID() {
        return this._c_bpartner_location_id;
    }

    public void setC_Bill_BPartner_ID(long c_bill_bpartner_id) { this._c_bill_bpartner_id = c_bill_bpartner_id; }

    public long getC_Bill_BPartner_ID() {
        return this._c_bill_bpartner_id;
    }

    public void setC_Bill_Location_ID(long c_bill_location_id) { this._c_bill_location_id = c_bill_location_id; }

    public long getC_Bill_Location_ID() {
        return this._c_bill_location_id;
    }

    public void setTotalLines(int totallines) {
        this._totallines = totallines;
    }

    public int getTotalLines() { return this._totallines; }

    public void setGrandTotal(int grandtotal) {
        this._grandtotal = grandtotal;
    }

    public int getGrandTotal() { return this._grandtotal; }

    public void setM_Warehouse_ID(long m_warehouse_id) { this._m_warehouse_id = m_warehouse_id; }

    public long getM_Warehouse_ID() {
        return this._m_warehouse_id;
    }

    public void setM_Pricelist_ID(long m_pricelist_id) { this._m_pricelist_id = m_pricelist_id; }

    public long getM_Pricelist_ID() {
        return this._m_pricelist_id;
    }

    public void setPaymentRule(String paymentrule) { this._paymentrule = paymentrule; }

    public String getPaymentRule() {
        return this._paymentrule;
    }

    public void setC_PaymentTerm_ID(long c_paymentterm_id) { this._c_paymentterm_id = c_paymentterm_id; }

    public long getC_PaymentTerm_ID() { return this._c_paymentterm_id; }

    public void setIsCash(boolean iscash) {
        this._iscash = iscash;
    }

    public boolean getIsCash() { return this._iscash; }

    public void setSyncDate(Date syncdate) {
        this._syncdate = syncdate;
    }

    public Date getSyncDate() {
        return this._syncdate;
    }

    public void setProcessOrder_Retval(String processOrder_Retval) { this._processOrder_Retval = processOrder_Retval; }

    public String getProcessOrder_Retval() {
        return this._processOrder_Retval;
    }

    public void setSyncDateProcessOrder(Date syncdateProcessOrder) { this._syncdateProcessOrder = syncdateProcessOrder; }

    public Date getSyncDateProcessOrder() {
        return this._syncdateProcessOrder;
    }

    public void setM_InOut_ID(long m_inout_id) { this._m_inout_id = m_inout_id; }

    public long getM_InOut_ID() {
        return this._m_inout_id;
    }

    public void setC_Invoice_ID(long c_invoice_id) { this._c_invoice_id = c_invoice_id; }

    public long getC_Invoice_ID() { return this._c_invoice_id; }

    public void setInternal_Status(int internal_status) {
        this._internal_status = internal_status;
    }

    public int getInternal_Status() {
        return this._internal_status;
    }

    public void setDocumentID(long documentid) { this._documentid = documentid; }

    public long getDocumentID() { return this._documentid; }

    public void setRowNumber(int rownumber) {
        this._rownumber = rownumber;
    }

    public int getRowNumber() {
        return this._rownumber;
    }
}
