package com.keredwell.fieldsales.util;

import android.content.res.AssetManager;

import com.keredwell.fieldsales.ApplicationContext;

import java.io.IOException;
import java.io.*;
import java.util.Properties;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class PropUtil {
    private static final String TAG = makeLogTag(PropUtil.class);

    private static File file = new File(ApplicationContext.getAppContext().getFilesDir(), "config.properties");

    public static String getProperty(String key) {
        Properties properties = new Properties();
        try{

            if(file.exists()) {
                FileInputStream in = new FileInputStream(file);
                properties.load(in);
                in.close();
            }
            else
            {
                AssetManager assetManager = ApplicationContext.getAppContext().getAssets();
                InputStream inputStream = assetManager.open("config.properties");
                properties.load(inputStream);
                inputStream.close();
                FileOutputStream out = new FileOutputStream(file);
                properties.store(out,null);
                out.close();
            }

        } catch (FileNotFoundException e) {
            LogUtil.logE(TAG, e.getMessage(), e);
        } catch (IOException e) {
            LogUtil.logE(TAG, e.getMessage(), e);
        }
        return properties.getProperty(key);
    }

    public static String setProperty(String key,String value) {
        Properties properties = new Properties();
        try{
            if(file.exists()) {
                FileInputStream in = new FileInputStream(file);
                properties.load(in);
                in.close();
            }
            else {
                AssetManager assetManager = ApplicationContext.getAppContext().getAssets();
                InputStream inputStream = assetManager.open("config.properties");
                properties.load(inputStream);
                inputStream.close();
            }

            FileOutputStream out = new FileOutputStream(file);
            properties.setProperty(key, value);
            properties.store(out,null);
            out.close();
        } catch (FileNotFoundException e) {
            LogUtil.logE(TAG, e.getMessage(), e);
        } catch (IOException e) {
            LogUtil.logE(TAG, e.getMessage(), e);
        }
        return properties.getProperty(key);
    }
}
