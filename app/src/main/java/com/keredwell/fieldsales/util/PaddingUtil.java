package com.keredwell.fieldsales.util;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 21/12/2017.
 */

public class PaddingUtil {
    private static final String TAG = makeLogTag(PaddingUtil.class);

    public static String getLeftPaddedString(String orig, int length) {
        String retval = orig;
        try{
            for (int i = 0; i < length - orig.length(); i++) {
                retval = " " + retval;
            }
        } catch (Exception e) {
            LogUtil.logE(TAG, e.getMessage(), e);
        }
        return retval;
    }

    public static String getRightPaddedString(String orig, int length) {
        String retval = orig;
        try{
            for (int i = 0; i < length - orig.length(); i++) {
                retval += " ";
            }
        } catch (Exception e) {
            LogUtil.logE(TAG, e.getMessage(), e);
        }
        return retval;
    }
}
