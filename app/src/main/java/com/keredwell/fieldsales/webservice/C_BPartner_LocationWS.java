package com.keredwell.fieldsales.webservice;

import com.keredwell.fieldsales.ApplicationContext;
import com.keredwell.fieldsales.data.C_BPartner_Location;
import com.keredwell.fieldsales.util.DateUtil;
import com.keredwell.fieldsales.dbhelper.C_BPartner_LocationDBAdapter;
import com.keredwell.fieldsales.util.LogUtil;
import com.keredwell.fieldsales.util.PropUtil;

import org.ksoap2.serialization.SoapObject;

import java.util.Date;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 25/11/2017.
 */

public class C_BPartner_LocationWS {
    private static final String TAG = makeLogTag(C_BPartner_LocationWS.class);

    public static Boolean WSEvent(String mUser, String mPassword, Date lastUpdatedDate)
    {
        try{
            SoapObject field = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            field.addAttribute("column", "UpdatedDateTime");
            field.addProperty("val", DateUtil.ConvertToString(lastUpdatedDate));

            SoapObject dataRow = new SoapObject(PropUtil.getProperty("nameSpace"), "DataRow");
            dataRow.addSoapObject(field);

            SoapObject modelCRUD = new SoapObject(PropUtil.getProperty("nameSpace"), "ModelCRUD");
            modelCRUD.addProperty("serviceType", PropUtil.getProperty("bpartnerLocationServiceType"));
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
            return false;
        }
    }

    private static Boolean parseXml(SoapObject soap){
        try{
            if (soap.getPropertyCount() == 3)
            {
                if (soap.getProperty(2).toString().equals("true")) {
                    C_BPartner_LocationDBAdapter db = new C_BPartner_LocationDBAdapter(ApplicationContext.getAppContext());
                    for(int i=0; i<Integer.parseInt(soap.getProperty(1).toString()); i++) {

                        C_BPartner_Location c_bpartner_location = new C_BPartner_Location();

                        c_bpartner_location.setC_BPartner_ID(Long.parseLong(((SoapObject)((SoapObject)((SoapObject)soap.getProperty(0)).getProperty(i)).getProperty(0)).getProperty(0).toString()));
                         c_bpartner_location.setC_Location_ID(Long.parseLong(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(1)).getProperty(0).toString()));
                        if (((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(2)).getProperty(0) != null) {
                            c_bpartner_location.setPhone(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(2)).getProperty(0).toString());
                        }
                        c_bpartner_location.setC_BPartner_Location_ID(Long.parseLong(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(3)).getProperty(0).toString()));

                        if (db.getC_BPartner_Location(c_bpartner_location.getC_BPartner_Location_ID()) == null)
                        {
                            db.createC_BPartner_Location(c_bpartner_location);
                        }
                        else
                        {
                            db.updateC_BPartner_Location(c_bpartner_location);
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
