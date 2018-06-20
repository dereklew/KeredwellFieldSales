package com.keredwell.fieldsales.webservice;

import com.keredwell.fieldsales.data.C_Order;
import com.keredwell.fieldsales.util.DateUtil;
import com.keredwell.fieldsales.util.LogUtil;
import com.keredwell.fieldsales.util.PropUtil;

import org.ksoap2.serialization.SoapObject;

import java.math.BigDecimal;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 25/11/2017.
 */

public class C_OrderSendWS {
    private static final String TAG = makeLogTag(C_OrderSendWS.class);

    public static long WSEvent(String mUser, String mPassword, C_Order c_order)
    {
        try{
            SoapObject IsActive = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            IsActive.addAttribute("column", "IsActive");
            IsActive.addProperty("val", "Y");

            SoapObject IsSOTrx = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            IsSOTrx.addAttribute("column", "IsSOTrx");
            IsSOTrx.addProperty("val", "Y");

            SoapObject DocStatus = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            DocStatus.addAttribute("column", "DocStatus");
            DocStatus.addProperty("val", "DR");

            SoapObject DocAction = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            DocAction.addAttribute("column", "DocAction");
            DocAction.addProperty("val", "CO");

            SoapObject Processing = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            Processing.addAttribute("column", "Processing");
            Processing.addProperty("val", "N");

            SoapObject Processed = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            Processed.addAttribute("column", "Processed");
            Processed.addProperty("val", "N");

            SoapObject C_DocType_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            C_DocType_ID.addAttribute("column", "C_DocType_ID");
            C_DocType_ID.addProperty("val", 0);

            SoapObject C_DocTypeTarget_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            C_DocTypeTarget_ID.addAttribute("column", "C_DocTypeTarget_ID");
            C_DocTypeTarget_ID.addProperty("val", Long.toString(c_order.getC_DocTypeTarget_ID()));

            SoapObject Description = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            Description.addAttribute("column", "Description");
            Description.addProperty("val", "");

            SoapObject IsApproved = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            IsApproved.addAttribute("column", "IsApproved");
            IsApproved.addProperty("val", "Y");

            SoapObject IsCreditApproved = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            IsCreditApproved.addAttribute("column", "IsCreditApproved");
            IsCreditApproved.addProperty("val", "N");

            SoapObject IsDelivered = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            IsDelivered.addAttribute("column", "IsDelivered");
            IsDelivered.addProperty("val", "N");

            SoapObject IsInvoiced = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            IsInvoiced.addAttribute("column", "IsInvoiced");
            IsInvoiced.addProperty("val", "N");

            SoapObject IsPrinted = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            IsPrinted.addAttribute("column", "IsPrinted");
            IsPrinted.addProperty("val", "N");

            SoapObject IsTransferred = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            IsTransferred.addAttribute("column", "IsTransferred");
            IsTransferred.addProperty("val", "N");

            SoapObject IsSelected = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            IsSelected.addAttribute("column", "IsSelected");
            IsSelected.addProperty("val", "N");

            SoapObject SalesRep_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            SalesRep_ID.addAttribute("column", "SalesRep_ID");
            SalesRep_ID.addProperty("val", Long.toString(c_order.getSalesRep_ID()));

            SoapObject DateOrdered = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            DateOrdered.addAttribute("column", "DateOrdered");
            DateOrdered.addProperty("val", DateUtil.ConvertToString(c_order.getDateOrdered()));

            SoapObject DatePromised = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            DatePromised.addAttribute("column", "DatePromised");
            DatePromised.addProperty("val", DateUtil.ConvertToString(c_order.getDateOrdered()));

            SoapObject C_BPartner_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            C_BPartner_ID.addAttribute("column", "C_BPartner_ID");
            C_BPartner_ID.addProperty("val", Long.toString(c_order.getC_BPartner_ID()));

            SoapObject C_BPartner_Location_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            C_BPartner_Location_ID.addAttribute("column", "C_BPartner_Location_ID");
            C_BPartner_Location_ID.addProperty("val", Long.toString(c_order.getC_BPartner_Location_ID()));

            SoapObject IsDiscountPrinted = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            IsDiscountPrinted.addAttribute("column", "IsDiscountPrinted");
            IsDiscountPrinted.addProperty("val", "Y");

            SoapObject C_Currency_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            C_Currency_ID.addAttribute("column", "C_Currency_ID");
            C_Currency_ID.addProperty("val", PropUtil.getProperty("c_currency_id"));

            SoapObject PaymentRule = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            PaymentRule.addAttribute("column", "PaymentRule");
            PaymentRule.addProperty("val", c_order.getPaymentRule());

            SoapObject C_PaymentTerm_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            C_PaymentTerm_ID.addAttribute("column", "C_PaymentTerm_ID");
            C_PaymentTerm_ID.addProperty("val", Long.toString(c_order.getC_PaymentTerm_ID()));

            SoapObject InvoiceRule = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            InvoiceRule.addAttribute("column", "InvoiceRule");
            InvoiceRule.addProperty("val", "D");

            SoapObject DeliveryRule = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            DeliveryRule.addAttribute("column", "DeliveryRule");
            DeliveryRule.addProperty("val", "F");

            SoapObject FreightCostRule = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            FreightCostRule.addAttribute("column", "FreightCostRule");
            FreightCostRule.addProperty("val", "I");

            SoapObject FreightAmt = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            FreightAmt.addAttribute("column", "FreightAmt");
            FreightAmt.addProperty("val", "0");

            SoapObject DeliveryViaRule = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            DeliveryViaRule.addAttribute("column", "DeliveryViaRule");
            DeliveryViaRule.addProperty("val", "D");

            SoapObject ChargeAmt = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            ChargeAmt.addAttribute("column", "ChargeAmt");
            ChargeAmt.addProperty("val", "0");

            SoapObject PriorityRule = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            PriorityRule.addAttribute("column", "PriorityRule");
            PriorityRule.addProperty("val", "5");

            SoapObject TotalLines = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            TotalLines.addAttribute("column", "TotalLines");
            TotalLines.addProperty("val", String.format( "%.2f", BigDecimal.valueOf(c_order.getTotalLines()).movePointLeft(2)));

            SoapObject GrandTotal = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            GrandTotal.addAttribute("column", "GrandTotal");
            GrandTotal.addProperty("val", String.format( "%.2f", BigDecimal.valueOf(c_order.getGrandTotal()).movePointLeft(2)));

            SoapObject M_Warehouse_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            M_Warehouse_ID.addAttribute("column", "M_Warehouse_ID");
            M_Warehouse_ID.addProperty("val", Long.toString(c_order.getM_Warehouse_ID()));

            SoapObject M_PriceList_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            M_PriceList_ID.addAttribute("column", "M_PriceList_ID");
            M_PriceList_ID.addProperty("val", Long.toString(c_order.getM_Pricelist_ID()));

            SoapObject IsTaxIncluded = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            IsTaxIncluded.addAttribute("column", "IsTaxIncluded");
            IsTaxIncluded.addProperty("val", "N");

            SoapObject Posted = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            Posted.addAttribute("column", "Posted");
            Posted.addProperty("val", "N");

            SoapObject SendEMail = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            SendEMail.addAttribute("column", "SendEMail");
            SendEMail.addProperty("val", "N");

            SoapObject CopyFrom = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            CopyFrom.addAttribute("column", "CopyFrom");
            CopyFrom.addProperty("val", "N");

            SoapObject IsSelfService = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            IsSelfService.addAttribute("column", "IsSelfService");
            IsSelfService.addProperty("val", "N");

            SoapObject Bill_BPartner_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            Bill_BPartner_ID.addAttribute("column", "Bill_BPartner_ID");
            Bill_BPartner_ID.addProperty("val", Long.toString(c_order.getC_Bill_BPartner_ID()));

            SoapObject Bill_Location_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            Bill_Location_ID.addAttribute("column", "Bill_Location_ID");
            Bill_Location_ID.addProperty("val", Long.toString(c_order.getC_Bill_Location_ID()));

            SoapObject IsDropShip = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            IsDropShip.addAttribute("column", "IsDropShip");
            IsDropShip.addProperty("val", "N");

            SoapObject IsPayScheduleValid = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            IsPayScheduleValid.addAttribute("column", "IsPayScheduleValid");
            IsPayScheduleValid.addProperty("val", "N");

            SoapObject IsPriviledgedRate = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            IsPriviledgedRate.addAttribute("column", "IsPriviledgedRate");
            IsPriviledgedRate.addProperty("val", "N");

            SoapObject C_Order_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            C_Order_ID.addAttribute("column", "C_Order_ID");
            C_Order_ID.addProperty("val", c_order.getC_Order_ID());

            //SoapObject DocumentNo = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            //DocumentNo.addAttribute("column", "DocumentNo");
            //DocumentNo.addProperty("val", c_order.getDocumentID());

            SoapObject dataRow = new SoapObject(PropUtil.getProperty("nameSpace"), "DataRow");
            dataRow.addSoapObject(IsActive);
            dataRow.addSoapObject(IsSOTrx);
            dataRow.addSoapObject(DocStatus);
            dataRow.addSoapObject(DocAction);
            dataRow.addSoapObject(Processing);
            dataRow.addSoapObject(Processed);
            dataRow.addSoapObject(C_DocType_ID);
            dataRow.addSoapObject(C_DocTypeTarget_ID);
            dataRow.addSoapObject(Description);
            dataRow.addSoapObject(IsApproved);
            dataRow.addSoapObject(IsCreditApproved);
            dataRow.addSoapObject(IsDelivered);
            dataRow.addSoapObject(IsInvoiced);
            dataRow.addSoapObject(IsPrinted);
            dataRow.addSoapObject(IsTransferred);
            dataRow.addSoapObject(IsSelected);
            dataRow.addSoapObject(SalesRep_ID);
            dataRow.addSoapObject(DateOrdered);
            dataRow.addSoapObject(DatePromised);
            dataRow.addSoapObject(C_BPartner_ID);
            dataRow.addSoapObject(C_BPartner_Location_ID);
            dataRow.addSoapObject(IsDiscountPrinted);
            dataRow.addSoapObject(C_Currency_ID);
            dataRow.addSoapObject(PaymentRule);
            dataRow.addSoapObject(C_PaymentTerm_ID);
            dataRow.addSoapObject(InvoiceRule);
            dataRow.addSoapObject(DeliveryRule);
            dataRow.addSoapObject(FreightCostRule);
            dataRow.addSoapObject(FreightAmt);
            dataRow.addSoapObject(DeliveryViaRule);
            dataRow.addSoapObject(ChargeAmt);
            dataRow.addSoapObject(PriorityRule);
            dataRow.addSoapObject(TotalLines);
            dataRow.addSoapObject(GrandTotal);
            dataRow.addSoapObject(M_Warehouse_ID);
            dataRow.addSoapObject(M_PriceList_ID);
            dataRow.addSoapObject(IsTaxIncluded);
            dataRow.addSoapObject(Posted);
            dataRow.addSoapObject(SendEMail);
            dataRow.addSoapObject(CopyFrom);
            dataRow.addSoapObject(IsSelfService);
            dataRow.addSoapObject(Bill_BPartner_ID);
            dataRow.addSoapObject(Bill_Location_ID);
            dataRow.addSoapObject(IsDropShip);
            dataRow.addSoapObject(IsPayScheduleValid);
            dataRow.addSoapObject(IsPriviledgedRate);
            dataRow.addSoapObject(C_Order_ID);
            //dataRow.addSoapObject(DocumentNo);

            SoapObject modelCRUD = new SoapObject(PropUtil.getProperty("nameSpace"), "ModelCRUD");
            modelCRUD.addProperty("serviceType", PropUtil.getProperty("orderServiceType"));
            modelCRUD.addSoapObject(dataRow);

            SoapObject aDLoginRequest = ADLoginRequest.GetADLoginRequest(mUser, mPassword);

            SoapObject modelCRUDRequest = new SoapObject(PropUtil.getProperty("nameSpace"), "ModelCRUDRequest");
            modelCRUDRequest.addSoapObject(modelCRUD);
            modelCRUDRequest.addSoapObject(aDLoginRequest);

            SoapObject queryData = new SoapObject(PropUtil.getProperty("nameSpace"), "createUpdateData");
            queryData.addSoapObject(modelCRUDRequest);

            //request to server and get Soap Primitive response
            return parseXml(WebServiceCall.callWSThreadSoapPrimitive(queryData));

        } catch (Exception e) {
            LogUtil.logE(TAG, e.getMessage(), e);
            return 0;
        }
    }

    private static long parseXml(SoapObject soap){
        try{
            if (soap.getAttributeCount() == 1)
            {
                if (soap.getAttribute(0).toString() != null)
                    return Long.parseLong(soap.getAttribute(0).toString());
            }
            return 0;
        } catch (Exception e) {
            LogUtil.logE(TAG, e.getMessage(), e);
            return 0;
        }
    }
}
