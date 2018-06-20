package com.keredwell.fieldsales.webservice;

import com.keredwell.fieldsales.util.PropUtil;

import org.ksoap2.serialization.SoapObject;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 25/11/2017.
 */

public class ADLoginRequest {
    private static final String TAG = makeLogTag(ADLoginRequest.class);

    public static SoapObject GetADLoginRequest(String mUser, String mPassword)
    {
        SoapObject aDLoginRequest = new SoapObject(PropUtil.getProperty("nameSpace"), "ADLoginRequest");
        aDLoginRequest.addProperty("user", mUser);
        aDLoginRequest.addProperty("pass", mPassword);
        aDLoginRequest.addProperty("lang", PropUtil.getProperty("lang"));
        aDLoginRequest.addProperty("ClientID", PropUtil.getProperty("clientId"));
        aDLoginRequest.addProperty("RoleID", PropUtil.getProperty("roleId"));
        aDLoginRequest.addProperty("OrgID", PropUtil.getProperty("orgId"));
        aDLoginRequest.addProperty("WarehouseID", PropUtil.getProperty("warehouseId"));
        aDLoginRequest.addProperty("stage", PropUtil.getProperty("stage"));

        return aDLoginRequest;
    }
}
