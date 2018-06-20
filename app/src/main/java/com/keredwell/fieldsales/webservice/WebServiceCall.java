package com.keredwell.fieldsales.webservice;

import com.keredwell.fieldsales.util.LogUtil;
import com.keredwell.fieldsales.util.PropUtil;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 7/11/2017.
 */

public class WebServiceCall {
    private static final String TAG = makeLogTag(WebServiceCall.class);

    public static SoapObject callWSThreadSoapPrimitive(SoapObject request) {

        try {
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE ht = new HttpTransportSE(PropUtil.getProperty("serverUri"));
            ht.debug = true;
            ht.call("", envelope);
            LogUtil.logD(TAG,"dump Request: " + ht.requestDump);
            LogUtil.logD(TAG,"dump response: " + ht.responseDump);
            SoapObject response = (SoapObject) envelope.getResponse();

            return response;
        } catch (Exception e) {
            LogUtil.logE(TAG, e.getMessage(), e);
            return null;
        }
    }
}
