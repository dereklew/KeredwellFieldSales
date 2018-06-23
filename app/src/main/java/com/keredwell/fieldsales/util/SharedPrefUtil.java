package com.keredwell.fieldsales.util;

import android.content.SharedPreferences;

import com.keredwell.fieldsales.ApplicationContext;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class SharedPrefUtil {
    private static final String TAG = makeLogTag(SharedPrefUtil.class);

    private static final String PREFS_NAME = "KeredwellPrefFile";

    public static String getPersistedData(String key, String defaultValue) {
        SharedPreferences preferences = ApplicationContext.getAppContext().getSharedPreferences(PREFS_NAME, 0);
        return preferences.getString(key, defaultValue);
    }

    public static void setPersistedData(String key, String value) {
        SharedPreferences preferences = ApplicationContext.getAppContext().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(key, value);
        editor.apply();
    }

    public static void clearPersistedData() {
        SharedPreferences preferences = ApplicationContext.getAppContext().getSharedPreferences(PREFS_NAME, 0);
        preferences.edit().clear().commit();
    }
}
