package com.keredwell.fieldsales;

import android.app.Application;
import android.content.Context;

import com.keredwell.fieldsales.util.PrintDef;

import rego.printlib.export.regoPrinter;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 1/11/2017.
 */

public class ApplicationContext extends Application {
    private static final String TAG = makeLogTag(ApplicationContext.class);

    public static final String USERID = "UserId";
    public static final String USERNAME = "UserName";
    public static final String USERPASS = "UserPass";

    private static Context context;

    public void onCreate() {
        super.onCreate();
        ApplicationContext.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return ApplicationContext.context;
    }


    private regoPrinter printer;
    private int myState = 0;
    private String printName="RG-MTP58B";
    private int alignTypetext;

    private PrintDef.TransferMode printmode = PrintDef.TransferMode.TM_NONE;
    private boolean labelmark = true;

    public regoPrinter getObject() {
        return printer;
    }

    public int setAlignType(int n){
        alignTypetext=n;
        return alignTypetext;

    }
    public int getAlignType(){
        return alignTypetext;
    }

    public void setObject() {
        printer = new regoPrinter(this);
    }

    public String getName() {
        return printName;
    }

    public void setName(String name) {
        printName = name;
    }
    public void setState(int state) {
        myState = state;
    }

    public int getState() {
        return myState;
    }

    public void setPrintway(int printway) {

        switch (printway) {
            case 0:
                printmode = PrintDef.TransferMode.TM_NONE;
                break;
            case 1:
                printmode = PrintDef.TransferMode.TM_DT_V1;
                break;
            default:
                printmode = PrintDef.TransferMode.TM_DT_V2;
                break;
        }
    }

    public int getPrintway() {
        return printmode.getValue();
    }

    public boolean getlabel() {
        return labelmark;
    }

    public void setlabel(boolean labelprint) {
        labelmark = labelprint;
    }

    /**
     * 将打印n行空行。n的值应该在0-255之间
     *
     * @param lines
     */

    public void PrintNLine(int lines) {
        // TODO Auto-generated method stub
        printer.ASCII_PrintBuffer(myState, new byte[] { 0x1B, 0x66, 1,
                (byte) lines }, 4);
    }

    public void printSettings() {
        // 1D 28 41 00 00 00 02
        byte[] printdata = { 0x1d, 0x28, 0x41, 0x00, 0x00, 0x00, 0x02 };
        printer.ASCII_PrintBuffer(myState, printdata, printdata.length);
    }
}