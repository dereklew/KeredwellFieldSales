package com.keredwell.fieldsales.webservice;

import com.keredwell.fieldsales.util.LogUtil;
import com.keredwell.fieldsales.util.PropUtil;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 25/11/2017.
 */

public class ProcessOrderWS {
    private static final String TAG = makeLogTag(ProcessOrderWS.class);

    public static String WSEvent(String mUser, String mPassword, long c_order_id)
    {
        try{
            SoapObject modelRunProcess = new SoapObject(PropUtil.getProperty("nameSpace"), "ModelRunProcess");
            modelRunProcess.addAttribute("AD_Process_ID", "104");
            modelRunProcess.addAttribute("AD_Menu_ID", "0");
            modelRunProcess.addAttribute("AD_Record_ID", Long.toString(c_order_id));
            modelRunProcess.addAttribute("DocAction", "CO");
            modelRunProcess.addProperty("serviceType", PropUtil.getProperty("processOrderServiceType"));

            SoapObject aDLoginRequest = ADLoginRequest.GetADLoginRequest(mUser, mPassword);

            SoapObject modelCRUDRequest = new SoapObject(PropUtil.getProperty("nameSpace"), "ModelRunProcessRequest");
            modelCRUDRequest.addSoapObject(modelRunProcess);
            modelCRUDRequest.addSoapObject(aDLoginRequest);

            SoapObject queryData = new SoapObject(PropUtil.getProperty("nameSpace"), "runProcess");
            queryData.addSoapObject(modelCRUDRequest);

            //request to server and get Soap Primitive response
            return parseXml(WebServiceCall.callWSThreadSoapPrimitive(queryData));

        } catch (Exception e) {
            LogUtil.logE(TAG, e.getMessage(), e);
            return "";
        }
    }

    private static String parseXml(SoapObject soap){
        try{
            if (soap.getPropertyCount() == 2)
            {
                if (((SoapPrimitive)soap.getProperty(0)) != null)
                    return ((SoapPrimitive)soap.getProperty(0)).getValue().toString();
            }
            return "";
        } catch (Exception e) {
            LogUtil.logE(TAG, e.getMessage(), e);
            return "";
        }
    }
}
