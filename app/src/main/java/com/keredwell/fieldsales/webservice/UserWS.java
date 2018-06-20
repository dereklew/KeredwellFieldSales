package com.keredwell.fieldsales.webservice;

import com.keredwell.fieldsales.data.User;
import com.keredwell.fieldsales.util.LogUtil;
import com.keredwell.fieldsales.util.PropUtil;

import org.ksoap2.serialization.SoapObject;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 25/11/2017.
 */

public class UserWS {
    private static final String TAG = makeLogTag(UserWS.class);

    public static User UserWSEvent(String mUser, String mPassword)
    {
        try{
            SoapObject field = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            field.addAttribute("column", "LoginName");
            field.addProperty("val", mUser);

            SoapObject dataRow = new SoapObject(PropUtil.getProperty("nameSpace"), "DataRow");
            dataRow.addSoapObject(field);

            SoapObject modelCRUD = new SoapObject(PropUtil.getProperty("nameSpace"), "ModelCRUD");
            modelCRUD.addProperty("serviceType", PropUtil.getProperty("userServiceType"));
            modelCRUD.addSoapObject(dataRow);

            SoapObject aDLoginRequest = ADLoginRequest.GetADLoginRequest(mUser, mPassword);

            SoapObject modelCRUDRequest = new SoapObject(PropUtil.getProperty("nameSpace"), "ModelCRUDRequest");
            modelCRUDRequest.addSoapObject(modelCRUD);
            modelCRUDRequest.addSoapObject(aDLoginRequest);

            SoapObject queryData = new SoapObject(PropUtil.getProperty("nameSpace"), "queryData");
            queryData.addSoapObject(modelCRUDRequest);

            //request to server and get Soap Primitive response
            return parseXml(WebServiceCall.callWSThreadSoapPrimitive(queryData));

        } catch (Exception e) {
            LogUtil.logE(TAG, e.getMessage(), e);
            return null;
        }
    }

    private static User parseXml(SoapObject soap){
        try{
            User user = null;
            if (soap.getPropertyCount() == 3)
            {
                user = new User();
                user.setID(((SoapObject)((SoapObject)((SoapObject)soap.getProperty(0)).getProperty(0)).getProperty(0)).getProperty(0).toString());
                user.setName(((SoapObject)((SoapObject)((SoapObject)soap.getProperty(0)).getProperty(0)).getProperty(1)).getProperty(0).toString());
            }

            return user;

        } catch (Exception e) {
            LogUtil.logE(TAG, e.getMessage(), e);
            return null;
        }
    }
}
