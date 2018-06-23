package com.keredwell.fieldsales.webservice;

import com.keredwell.fieldsales.ApplicationContext;
import com.keredwell.fieldsales.data.C_Order;
import com.keredwell.fieldsales.data.C_OrderLine;
import com.keredwell.fieldsales.dbhelper.C_OrderDBAdapter;
import com.keredwell.fieldsales.dbhelper.C_OrderLineDBAdapter;
import com.keredwell.fieldsales.dbhelper.M_ProductDBAdapter;
import com.keredwell.fieldsales.util.DateUtil;
import com.keredwell.fieldsales.util.LogUtil;
import com.keredwell.fieldsales.util.PropUtil;

import org.ksoap2.serialization.SoapObject;

import java.util.Date;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class C_OrderLineReceiveWS {
    private static final String TAG = makeLogTag(C_OrderLineReceiveWS.class);

    public static Boolean WSEvent(String mUser, String mPassword, String mUserID, Date lastUpdatedDate)
    {
        try{
            SoapObject updatedDateTimeField = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            updatedDateTimeField.addAttribute("column", "UpdatedDateTime");
            updatedDateTimeField.addProperty("val", DateUtil.ConvertToString(lastUpdatedDate));

            SoapObject salesRepIDField = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            salesRepIDField.addAttribute("column", "SalesRepID");
            salesRepIDField.addProperty("val", mUserID);

            SoapObject dataRow = new SoapObject(PropUtil.getProperty("nameSpace"), "DataRow");
            dataRow.addSoapObject(salesRepIDField);
            dataRow.addSoapObject(updatedDateTimeField);

            SoapObject modelCRUD = new SoapObject(PropUtil.getProperty("nameSpace"), "ModelCRUD");
            modelCRUD.addProperty("serviceType", PropUtil.getProperty("orderlineReceiveServiceType"));
            modelCRUD.addSoapObject(dataRow);

            SoapObject aDLoginRequest = ADLoginRequest.GetADLoginRequest(mUser, mPassword);

            SoapObject modelCRUDRequest = new SoapObject(PropUtil.getProperty("nameSpace"), "ModelCRUDRequest");
            modelCRUDRequest.addSoapObject(modelCRUD);
            modelCRUDRequest.addSoapObject(aDLoginRequest);

            SoapObject queryData = new SoapObject(PropUtil.getProperty("nameSpace"), "queryData");
            queryData.addSoapObject(modelCRUDRequest);

            return parseXml(WebServiceCall.callWSThreadSoapPrimitive(queryData));

        } catch (Exception e) {
            LogUtil.logE(TAG, e.getMessage(), e);
            return false;
        }
    }

    private static Boolean parseXml(SoapObject soap){
        try{
            if (soap.getPropertyCount() == 3)
            {
                if (soap.getProperty(2).toString().equals("true")) {
                    C_OrderDBAdapter c_orderDBAdapter = new C_OrderDBAdapter(ApplicationContext.getAppContext());
                    C_OrderLineDBAdapter c_orderLineDBAdapter = new C_OrderLineDBAdapter(ApplicationContext.getAppContext());
                    M_ProductDBAdapter m_productDBAdapter = new M_ProductDBAdapter(ApplicationContext.getAppContext());

                    for(int i=0; i<Integer.parseInt(soap.getProperty(1).toString()); i++) {

                        C_Order c_order = c_orderDBAdapter.getC_OrderByC_Order_ID(Long.parseLong(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(1)).getProperty(0).toString()));

                        if (c_order != null) {
                            C_OrderLine c_orderLine = new C_OrderLine();
                            c_orderLine.setOrderID(c_order.getID());
                            c_orderLine.setC_OrderLine_ID(Long.parseLong(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(0)).getProperty(0).toString()));
                            c_orderLine.setC_Order_ID(Long.parseLong(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(1)).getProperty(0).toString()));
                            c_orderLine.setLineNo(Integer.parseInt(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(2)).getProperty(0).toString()));
                            c_orderLine.setDateOrdered(DateUtil.ConvertToDate(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(3)).getProperty(0).toString()));
                            c_orderLine.setM_Product_ID(Long.parseLong(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(4)).getProperty(0).toString()));
                            c_orderLine.setProductName(m_productDBAdapter.getM_Product(Long.parseLong(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(4)).getProperty(0).toString())).getName());
                            c_orderLine.setC_Uom_ID(Long.parseLong(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(5)).getProperty(0).toString()));
                            c_orderLine.setM_Warehouse_ID(Long.parseLong(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(6)).getProperty(0).toString()));
                            c_orderLine.setQtyOrdered(Integer.parseInt(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(7)).getProperty(0).toString()));
                            c_orderLine.setPriceList((int) (Double.parseDouble(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(8)).getProperty(0).toString()) * 100));
                            c_orderLine.setPriceActual((int) (Double.parseDouble(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(9)).getProperty(0).toString()) * 100));
                            c_orderLine.setC_Tax_ID(Long.parseLong(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(10)).getProperty(0).toString()));
                            c_orderLine.setC_BPartner_ID(Long.parseLong(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(11)).getProperty(0).toString()));
                            c_orderLine.setC_BPartner_Location_ID(Long.parseLong(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(12)).getProperty(0).toString()));
                            c_orderLine.setLineNetAmt((int) (Double.parseDouble(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(13)).getProperty(0).toString()) * 100));
                            c_orderLine.setPriceLimit((int) (Double.parseDouble(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(14)).getProperty(0).toString()) * 100));
                            c_orderLine.setDiscount((int) (Double.parseDouble(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(15)).getProperty(0).toString()) * 100));

                            if (c_order.getInternal_Status() == C_Order.STATUS_INCOMPLETE) {
                                if (c_orderLineDBAdapter.getC_OrderLineByC_OrderLine_ID(c_orderLine.getC_OrderLine_ID()) == null) {
                                    c_orderLineDBAdapter.createC_OrderLineWithC_OrderLine_ID(c_orderLine);
                                } else {
                                    c_orderLineDBAdapter.updateC_OrderLineWith_C_OrderLine_ID(c_orderLine);
                                }
                            }
                        }
                    }
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            LogUtil.logE(TAG, e.getMessage(), e);
            return false;
        }
    }
}
