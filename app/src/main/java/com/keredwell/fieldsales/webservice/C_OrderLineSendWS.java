package com.keredwell.fieldsales.webservice;

import com.keredwell.fieldsales.data.C_OrderLine;
import com.keredwell.fieldsales.util.DateUtil;
import com.keredwell.fieldsales.util.LogUtil;
import com.keredwell.fieldsales.util.PropUtil;

import org.ksoap2.serialization.SoapObject;

import java.math.BigDecimal;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 25/11/2017.
 */

public class C_OrderLineSendWS {
    private static final String TAG = makeLogTag(C_OrderLineSendWS.class);

    public static long WSEvent(String mUser, String mPassword, C_OrderLine c_orderItem)
    {
        try{
            SoapObject IsActive = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            IsActive.addAttribute("column", "IsActive");
            IsActive.addProperty("val", "Y");

            SoapObject C_Order_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            C_Order_ID.addAttribute("column", "C_Order_ID");
            C_Order_ID.addProperty("val", Long.toString(c_orderItem.getC_Order_ID()));

            SoapObject Line = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            Line.addAttribute("column", "Line");
            Line.addProperty("val", Integer.toString(c_orderItem.getLineNo()));

            SoapObject C_BPartner_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            C_BPartner_ID.addAttribute("column", "C_BPartner_ID");
            C_BPartner_ID.addProperty("val", Long.toString(c_orderItem.getC_BPartner_ID()));

            SoapObject C_BPartner_Location_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            C_BPartner_Location_ID.addAttribute("column", "C_BPartner_Location_ID");
            C_BPartner_Location_ID.addProperty("val", Long.toString(c_orderItem.getC_BPartner_Location_ID()));

            SoapObject DateOrdered = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            DateOrdered.addAttribute("column", "DateOrdered");
            DateOrdered.addProperty("val", DateUtil.ConvertToString(c_orderItem.getDateOrdered()));

            SoapObject DatePromised = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            DatePromised.addAttribute("column", "DatePromised");
            DatePromised.addProperty("val", DateUtil.ConvertToString(c_orderItem.getDateOrdered()));

            SoapObject M_Product_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            M_Product_ID.addAttribute("column", "M_Product_ID");
            M_Product_ID.addProperty("val", Long.toString(c_orderItem.getM_Product_ID()));

            SoapObject M_Warehouse_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            M_Warehouse_ID.addAttribute("column", "M_Warehouse_ID");
            M_Warehouse_ID.addProperty("val", Long.toString(c_orderItem.getM_Warehouse_ID()));

            SoapObject C_UOM_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            C_UOM_ID.addAttribute("column", "C_UOM_ID");
            C_UOM_ID.addProperty("val", Long.toString(c_orderItem.getC_Uom_ID()));

            SoapObject QtyOrdered = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            QtyOrdered.addAttribute("column", "QtyOrdered");
            QtyOrdered.addProperty("val", Integer.toString(c_orderItem.getQtyOrdered()));

            SoapObject QtyReserved = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            QtyReserved.addAttribute("column", "QtyReserved");
            QtyReserved.addProperty("val", "0");

            SoapObject QtyDelivered = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            QtyDelivered.addAttribute("column", "QtyDelivered");
            QtyDelivered.addProperty("val", "0");

            SoapObject C_Currency_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            C_Currency_ID.addAttribute("column", "C_Currency_ID");
            C_Currency_ID.addProperty("val", PropUtil.getProperty("c_currency_id"));

            SoapObject PriceList = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            PriceList.addAttribute("column", "PriceList");
            PriceList.addProperty("val", String.format( "%.2f", BigDecimal.valueOf(c_orderItem.getPriceList()).movePointLeft(2)));

            SoapObject PriceActual = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            PriceActual.addAttribute("column", "PriceActual");
            PriceActual.addProperty("val", String.format( "%.2f", BigDecimal.valueOf(c_orderItem.getPriceActual()).movePointLeft(2)));

            SoapObject PriceLimit = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            PriceLimit.addAttribute("column", "PriceLimit");
            PriceLimit.addProperty("val", String.format( "%.2f", BigDecimal.valueOf(c_orderItem.getPriceLimit()).movePointLeft(2)));

            SoapObject LineNetAmt = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            LineNetAmt.addAttribute("column", "LineNetAmt");
            LineNetAmt.addProperty("val", String.format( "%.2f", BigDecimal.valueOf(c_orderItem.getLineNetAmt()).movePointLeft(2)));

            SoapObject Discount = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            Discount.addAttribute("column", "Discount");
            Discount.addProperty("val", String.format( "%.2f", BigDecimal.valueOf(c_orderItem.getDiscount()).movePointLeft(2)));

            SoapObject FreightAmt = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            FreightAmt.addAttribute("column", "FreightAmt");
            FreightAmt.addProperty("val", "0");

            SoapObject C_Tax_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            C_Tax_ID.addAttribute("column", "C_Tax_ID");
            C_Tax_ID.addProperty("val", Long.toString(c_orderItem.getC_Tax_ID()));

            SoapObject M_AttributeSetInstance_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            M_AttributeSetInstance_ID.addAttribute("column", "M_AttributeSetInstance_ID");
            M_AttributeSetInstance_ID.addProperty("val", "0");

            SoapObject IsDescription = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            IsDescription.addAttribute("column", "IsDescription");
            IsDescription.addProperty("val", "N");

            SoapObject QtyEntered = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            QtyEntered.addAttribute("column", "QtyEntered");
            QtyEntered.addProperty("val", Integer.toString(c_orderItem.getQtyOrdered()));

            SoapObject PriceEntered = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            PriceEntered.addAttribute("column", "PriceEntered");
            PriceEntered.addProperty("val", String.format( "%.2f", BigDecimal.valueOf(c_orderItem.getPriceActual()).movePointLeft(2)));

            SoapObject QtyLostSales = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            QtyLostSales.addAttribute("column", "QtyLostSales");
            QtyLostSales.addProperty("val", "0");

            SoapObject CreateProduction = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            CreateProduction.addAttribute("column", "CreateProduction");
            CreateProduction.addProperty("val", "N");

            SoapObject C_OrderLine_ID = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            C_OrderLine_ID.addAttribute("column", "C_OrderLine_ID");
            C_OrderLine_ID.addProperty("val", Long.toString(c_orderItem.getC_OrderLine_ID()));

            SoapObject dataRow = new SoapObject(PropUtil.getProperty("nameSpace"), "DataRow");
            dataRow.addSoapObject(IsActive);
            dataRow.addSoapObject(C_Order_ID);
            dataRow.addSoapObject(Line);
            dataRow.addSoapObject(C_BPartner_ID);
            dataRow.addSoapObject(C_BPartner_Location_ID);
            dataRow.addSoapObject(DateOrdered);
            dataRow.addSoapObject(DatePromised);
            dataRow.addSoapObject(M_Product_ID);
            dataRow.addSoapObject(M_Warehouse_ID);
            dataRow.addSoapObject(C_UOM_ID);
            dataRow.addSoapObject(QtyOrdered);
            dataRow.addSoapObject(QtyReserved);
            dataRow.addSoapObject(QtyDelivered);
            dataRow.addSoapObject(C_Currency_ID);
            dataRow.addSoapObject(PriceList);
            dataRow.addSoapObject(PriceActual);
            dataRow.addSoapObject(PriceLimit);
            dataRow.addSoapObject(LineNetAmt);
            dataRow.addSoapObject(Discount);
            dataRow.addSoapObject(FreightAmt);
            dataRow.addSoapObject(C_Tax_ID);
            dataRow.addSoapObject(M_AttributeSetInstance_ID);
            dataRow.addSoapObject(IsDescription);
            dataRow.addSoapObject(QtyEntered);
            dataRow.addSoapObject(PriceEntered);
            dataRow.addSoapObject(QtyLostSales);
            dataRow.addSoapObject(CreateProduction);
            dataRow.addSoapObject(C_OrderLine_ID);

            SoapObject modelCRUD = new SoapObject(PropUtil.getProperty("nameSpace"), "ModelCRUD");
            modelCRUD.addProperty("serviceType", PropUtil.getProperty("orderlineServiceType"));
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
