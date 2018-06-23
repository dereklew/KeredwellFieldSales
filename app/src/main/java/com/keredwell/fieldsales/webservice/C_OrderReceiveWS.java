package com.keredwell.fieldsales.webservice;

import com.keredwell.fieldsales.ApplicationContext;
import com.keredwell.fieldsales.data.C_Order;
import com.keredwell.fieldsales.dbhelper.C_BPartnerDBAdapter;
import com.keredwell.fieldsales.dbhelper.C_OrderDBAdapter;
import com.keredwell.fieldsales.util.DateUtil;
import com.keredwell.fieldsales.util.LogUtil;
import com.keredwell.fieldsales.util.PropUtil;

import org.ksoap2.serialization.SoapObject;

import java.util.Date;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class C_OrderReceiveWS {
    private static final String TAG = makeLogTag(C_OrderReceiveWS.class);

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
            modelCRUD.addProperty("serviceType", PropUtil.getProperty("orderReceiveServiceType"));
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
                    C_BPartnerDBAdapter c_bPartnerDBAdapter = new C_BPartnerDBAdapter(ApplicationContext.getAppContext());

                    for(int i=0; i<Integer.parseInt(soap.getProperty(1).toString()); i++) {

                        C_Order c_order = new C_Order();

                        c_order.setC_Order_ID(Long.parseLong(((SoapObject)((SoapObject)((SoapObject)soap.getProperty(0)).getProperty(i)).getProperty(0)).getProperty(0).toString()));

                        c_order.setC_DocType_ID(0);
                        c_order.setDocumentID(Long.parseLong(((SoapObject)((SoapObject)((SoapObject)soap.getProperty(0)).getProperty(i)).getProperty(1)).getProperty(0).toString()));
                        c_order.setC_DocTypeTarget_ID(Long.parseLong(((SoapObject)((SoapObject)((SoapObject)soap.getProperty(0)).getProperty(i)).getProperty(2)).getProperty(0).toString()));
                        c_order.setDateOrdered(DateUtil.ConvertToDate(((SoapObject)((SoapObject)((SoapObject)soap.getProperty(0)).getProperty(i)).getProperty(3)).getProperty(0).toString()));
                        c_order.setSalesRep_ID(Integer.parseInt(((SoapObject)((SoapObject)((SoapObject)soap.getProperty(0)).getProperty(i)).getProperty(4)).getProperty(0).toString()));
                        c_order.setC_PaymentTerm_ID(Long.parseLong(((SoapObject)((SoapObject)((SoapObject)soap.getProperty(0)).getProperty(i)).getProperty(5)).getProperty(0).toString()));
                        c_order.setTotalLines((int)(Double.parseDouble(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(6)).getProperty(0).toString())*100));
                        c_order.setGrandTotal((int)(Double.parseDouble(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(7)).getProperty(0).toString())*100));
                        c_order.setM_Warehouse_ID(Long.parseLong(((SoapObject)((SoapObject)((SoapObject)soap.getProperty(0)).getProperty(i)).getProperty(8)).getProperty(0).toString()));
                        c_order.setM_Pricelist_ID(Long.parseLong(((SoapObject)((SoapObject)((SoapObject)soap.getProperty(0)).getProperty(i)).getProperty(9)).getProperty(0).toString()));
                        c_order.setC_BPartner_ID(Long.parseLong(((SoapObject)((SoapObject)((SoapObject)soap.getProperty(0)).getProperty(i)).getProperty(10)).getProperty(0).toString()));
                        c_order.setCustomerName(c_bPartnerDBAdapter.getC_BPartner(Long.parseLong(((SoapObject)((SoapObject)((SoapObject)soap.getProperty(0)).getProperty(i)).getProperty(10)).getProperty(0).toString())).getName());
                        c_order.setC_BPartner_Location_ID(Long.parseLong(((SoapObject)((SoapObject)((SoapObject)soap.getProperty(0)).getProperty(i)).getProperty(11)).getProperty(0).toString()));
                        c_order.setPaymentRule(((SoapObject)((SoapObject)((SoapObject)soap.getProperty(0)).getProperty(i)).getProperty(12)).getProperty(0).toString());
                        c_order.setC_Bill_BPartner_ID(Long.parseLong(((SoapObject)((SoapObject)((SoapObject)soap.getProperty(0)).getProperty(i)).getProperty(13)).getProperty(0).toString()));
                        c_order.setC_Bill_Location_ID(Long.parseLong(((SoapObject)((SoapObject)((SoapObject)soap.getProperty(0)).getProperty(i)).getProperty(14)).getProperty(0).toString()));
                        c_order.setIsCash(((SoapObject)((SoapObject)((SoapObject)soap.getProperty(0)).getProperty(i)).getProperty(12)).getProperty(0).toString().equals("B"));
                        c_order.setInternal_Status(C_Order.STATUS_INCOMPLETE);

                        C_Order tempC_Order = c_orderDBAdapter.getC_OrderByC_Order_ID(c_order.getC_Order_ID());

                        if (tempC_Order == null)
                        {
                            c_orderDBAdapter.createC_OrderWithC_Order_ID(c_order);
                        }
                        else
                        {
                            if (tempC_Order.getInternal_Status() == C_Order.STATUS_INCOMPLETE)
                                c_orderDBAdapter.updateC_OrderWithC_Order_ID(c_order);
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
