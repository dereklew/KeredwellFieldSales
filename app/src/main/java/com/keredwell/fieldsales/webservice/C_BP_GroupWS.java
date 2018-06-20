package com.keredwell.fieldsales.webservice;

import com.keredwell.fieldsales.ApplicationContext;
import com.keredwell.fieldsales.data.C_BP_Group;
import com.keredwell.fieldsales.util.DateUtil;
import com.keredwell.fieldsales.dbhelper.C_BP_GroupDBAdapter;
import com.keredwell.fieldsales.util.LogUtil;
import com.keredwell.fieldsales.util.PropUtil;

import org.ksoap2.serialization.SoapObject;

import java.util.Date;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 25/11/2017.
 */

public class C_BP_GroupWS {
    private static final String TAG = makeLogTag(C_BP_GroupWS.class);

    public static Boolean WSEvent(String mUser, String mPassword, Date lastUpdatedDate)
    {
        try{
            SoapObject field = new SoapObject(PropUtil.getProperty("nameSpace"), "field");
            field.addAttribute("column", "UpdatedDateTime");
            field.addProperty("val", DateUtil.ConvertToString(lastUpdatedDate));

            SoapObject dataRow = new SoapObject(PropUtil.getProperty("nameSpace"), "DataRow");
            dataRow.addSoapObject(field);

            SoapObject modelCRUD = new SoapObject(PropUtil.getProperty("nameSpace"), "ModelCRUD");
            modelCRUD.addProperty("serviceType", PropUtil.getProperty("bpGroupServiceType"));
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
                    C_BP_GroupDBAdapter db = new C_BP_GroupDBAdapter(ApplicationContext.getAppContext());
                    for(int i=0; i<Integer.parseInt(soap.getProperty(1).toString()); i++) {

                        C_BP_Group c_bp_group = new C_BP_Group();

                        c_bp_group.setC_BP_Group_ID(Long.parseLong(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(0)).getProperty(0).toString()));
                        c_bp_group.setName(((SoapObject) ((SoapObject) ((SoapObject) soap.getProperty(0)).getProperty(i)).getProperty(1)).getProperty(0).toString());

                        if (db.getC_BP_Group(c_bp_group.getC_BP_Group_ID()) == null)
                        {
                            db.createC_BP_Group(c_bp_group);
                        }
                        else
                        {
                            db.updateC_BP_Group(c_bp_group);
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
