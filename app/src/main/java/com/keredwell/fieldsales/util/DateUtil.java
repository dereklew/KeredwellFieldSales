package com.keredwell.fieldsales.util;

import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 19/8/2017.
 */

public class DateUtil {
    private static final String TAG = makeLogTag(DateUtil.class);

    public static Long persistDate(Date date) {
        if (date != null) {
            return date.getTime();
        }
        return null;
    }

    public static Date loadDate(Cursor cursor, int index) {
        if (cursor.isNull(index)) {
            return null;
        }
        return new Date(cursor.getLong(index));
    }

    public static Date ConvertToDate(String dateString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            LogUtil.logE(TAG, e.getStackTrace().toString());
        }
        return convertedDate;
    }

    public static String ConvertToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String convertedDate = null;
        try {
            convertedDate = dateFormat.format(date);
        } catch (Exception e) {
            LogUtil.logE(TAG, e.getStackTrace().toString());
        }
        return convertedDate;
    }
}
