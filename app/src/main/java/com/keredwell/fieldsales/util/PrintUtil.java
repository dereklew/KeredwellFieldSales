package com.keredwell.fieldsales.util;

import android.serialport.DeviceControl;

import com.keredwell.fieldsales.ApplicationContext;
import com.keredwell.fieldsales.R;
import com.keredwell.fieldsales.data.C_BPartner;
import com.keredwell.fieldsales.data.C_Order;
import com.keredwell.fieldsales.data.C_OrderLine;
import com.keredwell.fieldsales.data.C_Tax;
import com.speedata.libutils.ConfigUtils;
import com.speedata.libutils.ReadBean;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class PrintUtil {
    private static final String TAG = makeLogTag(PrintUtil.class);

    // Printing
    public static int state;
    public static boolean mBconnect = false;
    private static ReadBean mRead;
    private static DeviceControl deviceControl;
    private static ApplicationContext context;

    private static void connect() {

        try{
            // 读取配置文件
            modelJudgment();
            if (mBconnect) {
                context.getObject().CON_CloseDevices(context.getState());
                mBconnect = false;
            } else {
                LogUtil.logD(TAG, "----RG---CON_ConnectDevices");
                if (state > 0) {
                    mBconnect = true;
                    context.setState(state);
                    context.setName("RG-E48");
                } else {
                    mBconnect = false;
                }
            }
        } catch (Exception e) {
            LogUtil.logE(TAG, e.getMessage(), e);
        }
    }

    private static void modelJudgment() {
        try{
            boolean configFileExists = ConfigUtils.isConfigFileExists();
            mRead = ConfigUtils.readConfig(ApplicationContext.getAppContext());
            ReadBean.PrintBean print = mRead.getPrint();
            String powerType = print.getPowerType();
            int braut = print.getBraut();
            List<Integer> gpio = print.getGpio();
            String serialPort = print.getSerialPort();
            state = context.getObject().CON_ConnectDevices("RG-E487",
                    serialPort + ":" + braut + ":1:1", 200);
            int[] intArray = new int[gpio.size()];
            String intArrayStr="";
            for (int i = 0; i < gpio.size(); i++) {
                intArray[i] = gpio.get(i);
                intArrayStr=intArrayStr+intArray[i]+" ";
            }
            try {
                deviceControl = new DeviceControl(powerType,intArray);
                deviceControl.PowerOnDevice();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            LogUtil.logE(TAG, e.getMessage(), e);
        }
    }

    public static boolean printOrder(C_Order c_order, ArrayList<C_OrderLine> c_orderItems, boolean isCash, C_Tax c_tax, C_BPartner c_bPartner)
    {
        context = (ApplicationContext) ApplicationContext.getAppContext();
        context.setObject();
        connect();
        //控制输入,确保不为空
        context.getObject().CON_PageStart(context.getState(), false,
                Integer.parseInt(PropUtil.getProperty("print_width")),
                Integer.parseInt(PropUtil.getProperty("print_height")));

        //context.getObject().CON_PageStart(context.getState(),false,0,0);

        printHeader(c_order.getDateOrdered(), c_order.getIsCash(), c_order.getDocumentID());
        printCustomerDetail(c_bPartner);
        printOrderLine(c_orderItems);
        printFooter(c_order, c_tax);

        context.getObject().CON_PageEnd(context.getState(),
                context.getPrintway());
        return mBconnect;
    }

    private static void printHeader(Date dateOrdered, boolean isCash, long documentid)
    {
        context.getObject().ASCII_CtrlAlignType(context.getState(),
            PrintDef.AlignType.AT_CENTER.getValue()); // left:0, center:1, right=2

        //加粗打印指令
        context.getObject().ASCII_PrintBuffer(
                context.getState(),
                new byte[] { 0x1b, 0x45, 0x01 }, 3);

        context.getObject().ASCII_PrintString(context.getState(),
                0,      //Width
                0,      //Height
                0,      //Thick
                0,      //Underline
                0,      //small
                "三兴食品厂", "gb2312");
        context.getObject().ASCII_CtrlPrintCRLF(context.getState(),
                1);

        context.getObject().ASCII_PrintString(context.getState(),
                0,      //Width
                0,      //Height
                0,      //Thick
                0,      //Underline
                0,      //small
                "SAN HSING FOOD MANUFACTURING", "utf8");
        context.getObject().ASCII_CtrlPrintCRLF(context.getState(),
                1);

        // 取消加粗打印指令
        context.getObject().ASCII_PrintBuffer(
                context.getState(),
                new byte[] { 0x1b, 0x45, 0x00 }, 3);

        context.getObject().ASCII_PrintString(context.getState(),
            0,      //Width
            0,      //Height
            0,      //Thick
            0,      //Underline
            0,      //small
            "No. 20 Bukit Batok Crescent\n#13-07\nEnterprise Centre,\nSingapore 658080\nTel:6316 4301  Fax:6316 4302", "utf8");
        context.getObject().ASCII_CtrlFeedLines(context.getState(),
                1);
        context.getObject().ASCII_CtrlFeedLines(context.getState(),
                1);
        context.getObject().ASCII_CtrlPrintCRLF(context.getState(),
                1);

        context.getObject().ASCII_PrintString(context.getState(),
            0,      //Width
            0,      //Height
            0,      //Thick
            0,      //Underline
            0,      //small
            Long.toString(documentid), "utf8");
        context.getObject().ASCII_CtrlFeedLines(context.getState(),
                1);
        context.getObject().ASCII_CtrlFeedLines(context.getState(),
                1);
        context.getObject().ASCII_CtrlPrintCRLF(context.getState(),
                1);

        context.getObject().ASCII_PrintString(context.getState(),
                0,      //Width
                0,      //Height
                0,      //Thick
                0,      //Underline
                0,      //small
                SharedPrefUtil.getPersistedData(ApplicationContext.USERNAME, ""), "utf8");
        context.getObject().ASCII_CtrlFeedLines(context.getState(),
                1);
        context.getObject().ASCII_CtrlFeedLines(context.getState(),
                1);
        context.getObject().ASCII_CtrlPrintCRLF(context.getState(),
                1);

        context.getObject().ASCII_PrintString(context.getState(),
                0,      //Width
                0,      //Height
                0,      //Thick
                0,      //Underline
                0,      //small
                Long.toString(documentid), "utf8");
        context.getObject().ASCII_CtrlFeedLines(context.getState(),
                1);
        context.getObject().ASCII_CtrlFeedLines(context.getState(),
                1);
        context.getObject().ASCII_CtrlPrintCRLF(context.getState(),
                1);

        context.getObject().ASCII_PrintString(context.getState(),
                0,      //Width
                0,      //Height
                0,      //Thick
                0,      //Underline
                0,      //small
                isCash ? ApplicationContext.getAppContext().getString(R.string.cash_or_credit_cash)
                        : ApplicationContext.getAppContext().getString(R.string.cash_or_credit_credit), "gb2312");
        context.getObject().ASCII_CtrlFeedLines(context.getState(),
                1);
        context.getObject().ASCII_CtrlPrintCRLF(context.getState(),
                1);

        context.getObject().ASCII_PrintString(context.getState(),
                0,      //Width
                0,      //Height
                0,      //Thick
                0,      //Underline
                0,      //small
                DateUtil.ConvertToString(dateOrdered), "utf8");
        context.getObject().ASCII_CtrlFeedLines(context.getState(),
                1);
        context.getObject().ASCII_CtrlPrintCRLF(context.getState(),
                1);
    }

    private static void printCustomerDetail(C_BPartner c_bPartner)
    {
        context.getObject().ASCII_CtrlAlignType(context.getState(),
                PrintDef.AlignType.AT_LEFT.getValue()); // left:0, center:1, right=2

        context.getObject().ASCII_PrintString(context.getState(),
                0,      //Width
                0,      //Height
                0,      //Thick
                0,      //Underline
                0,      //small
                c_bPartner.getName()+"\n"+c_bPartner.getAddress()+"\nS"+c_bPartner.getPostal(), "gb2312");
        context.getObject().ASCII_CtrlFeedLines(context.getState(),
                1);
        context.getObject().ASCII_CtrlPrintCRLF(context.getState(),
                1);
    }

    private static void printOrderLine(ArrayList<C_OrderLine> c_orderItems)
    {
        context.getObject().ASCII_CtrlAlignType(context.getState(),
                PrintDef.AlignType.AT_LEFT.getValue()); // left:0, center:1, right=2

        // 取消加粗打印指令
        context.getObject().ASCII_PrintBuffer(
                context.getState(),
                new byte[] { 0x1b, 0x45, 0x00 }, 3);

        context.getObject().ASCII_CtrlAlignType(context.getState(),
                PrintDef.AlignType.AT_LEFT.getValue()); // left:0, center:1, right=2

        context.getObject().ASCII_PrintString(context.getState(),0,
                0, 1, 0, 0,
                PaddingUtil.getRightPaddedString(ApplicationContext.getAppContext().getString(R.string.header_order_product_name),9) +
                    PaddingUtil.getLeftPaddedString(ApplicationContext.getAppContext().getString(R.string.header_order_unit_price), 7) +
                    PaddingUtil.getLeftPaddedString(ApplicationContext.getAppContext().getString(R.string.header_order_qty),4)  +
                    PaddingUtil.getLeftPaddedString(ApplicationContext.getAppContext().getString(R.string.header_order_price),4),
                "gb2312");

        context.getObject().ASCII_CtrlFeedLines(context.getState(),
                1);
        context.getObject().ASCII_CtrlPrintCRLF(context.getState(),1);

        for (Iterator<C_OrderLine> i = c_orderItems.iterator(); i.hasNext();) {
            C_OrderLine c_orderLine = i.next();
            context.getObject().ASCII_CtrlAlignType(context.getState(),
                    PrintDef.AlignType.AT_LEFT.getValue()); // left:0, center:1, right=2

            context.getObject().ASCII_PrintString(context.getState(),0,
                    0, 0, 0, 0,
                       c_orderLine.getProductName(),
                    "gb2312");
            context.getObject().ASCII_CtrlPrintCRLF(context.getState(),1);

            context.getObject().ASCII_CtrlAlignType(context.getState(),
                    PrintDef.AlignType.AT_RIGHT.getValue()); // left:0, center:1, right=2

            context.getObject().ASCII_PrintString(context.getState(),0,
                    0, 0, 0, 0,
                    PaddingUtil.getLeftPaddedString(String.format( "%.2f", BigDecimal.valueOf(c_orderLine.getPriceActual()).movePointLeft(2)),7) +
                       PaddingUtil.getLeftPaddedString(Integer.toString(c_orderLine.getQtyOrdered()),4) +
                       PaddingUtil.getLeftPaddedString(String.format( "%.2f", BigDecimal.valueOf(c_orderLine.getLineNetAmt()).movePointLeft(2)),7),
                    "gb2312");

            context.getObject().ASCII_CtrlFeedLines(context.getState(),
                    1);
            context.getObject().ASCII_CtrlPrintCRLF(context.getState(),1);
        }
    }

    private static void printFooter(C_Order c_order, C_Tax c_tax)
    {
        context.getObject().ASCII_CtrlAlignType(context.getState(),
                PrintDef.AlignType.AT_RIGHT.getValue()); // left:0, center:1, right=2

        // 取消加粗打印指令
        context.getObject().ASCII_PrintBuffer(
                context.getState(),
                new byte[] { 0x1b, 0x45, 0x00 }, 3);

        context.getObject().ASCII_PrintString(context.getState(),
                0,      //Width
                0,      //Height
                0,      //Thick
                0,      //Underline
                0,      //small
                ApplicationContext.getAppContext().getString(R.string.order_subtotal) + ": SGD " +
                        PaddingUtil.getLeftPaddedString(String.format( "%.2f", BigDecimal.valueOf(c_order.getTotalLines()).movePointLeft(2)), 8),
                "gb2312");
        context.getObject().ASCII_CtrlFeedLines(context.getState(),
                1);
        context.getObject().ASCII_CtrlPrintCRLF(context.getState(),
                1);

        context.getObject().ASCII_PrintString(context.getState(),
                0,      //Width
                0,      //Height
                0,      //Thick
                0,      //Underline
                0,      //small
                c_tax.getName() + ": SGD " +
                        PaddingUtil.getLeftPaddedString(String.format( "%.2f", BigDecimal.valueOf(c_order.getGrandTotal() -  c_order.getTotalLines()).movePointLeft(2)), 8),
                "UTF-8");
        context.getObject().ASCII_CtrlFeedLines(context.getState(),
                1);
        context.getObject().ASCII_CtrlPrintCRLF(context.getState(),
                1);

        context.getObject().ASCII_PrintString(context.getState(),
                0,      //Width
                0,      //Height
                0,      //Thick
                0,      //Underline
                0,      //small
                ApplicationContext.getAppContext().getString(R.string.order_total) + ": SGD " +
                        PaddingUtil.getLeftPaddedString(String.format( "%.2f", BigDecimal.valueOf(c_order.getGrandTotal()).movePointLeft(2)), 8),
                "gb2312");
        context.getObject().ASCII_CtrlFeedLines(context.getState(),
                1);
        context.getObject().ASCII_CtrlPrintCRLF(context.getState(),
                1);

        context.getObject().ASCII_CtrlFeedLines(context.getState(),
                10);


        context.getObject().ASCII_PrintString(context.getState(),
                0,      //Width
                0,      //Height
                1,      //Thick
                0,      //Underline
                0,      //small
                ApplicationContext.getAppContext().getString(R.string.dashedline),
                "gb2312");

        context.getObject().ASCII_PrintString(context.getState(),
                0,     //Width
                0,     //Height
                1,     //Thick
                0,     //Underline
                0,     //small
                ApplicationContext.getAppContext().getString(R.string.signature),
                "gb2312");

        context.getObject().ASCII_CtrlFeedLines(context.getState(),
                5);
    }

    // testIN进纸
    public static void testIN(int i) {
        context.PrintNLine(i);
    }

    // 出纸
    public static void testUnroll(byte length) {
        byte[] setCmd = new byte[4];
        setCmd[0] = 0x1b;
        setCmd[1] = 0x6a;
        setCmd[2] = length;
        setCmd[3] = (byte) 0x00;

        context.getObject().ASCII_PrintBuffer(context.getState(), setCmd,
                setCmd.length);
    }

    // 设置灰度
    public static void setLevel(int level) {
        // TODO Auto-generated method stub
        byte[] setCmd = new byte[7];
        setCmd[0] = 0x1b;
        setCmd[1] = 0x06;
        setCmd[2] = 0x1b;
        setCmd[3] = (byte) 0xfd;
        setCmd[4] = (byte) level;// (level - 1);
        setCmd[5] = 0x1b;
        setCmd[6] = 0x16;
        context.getObject().ASCII_PrintBuffer(context.getState(), setCmd,
                setCmd.length);
        System.out.println("setOk");
    }

    // 程序退出时需要关闭电源 省电
    public static void powerOffDevice() {
        try {
            deviceControl.PowerOffDevice();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            LogUtil.logE(TAG, e.getMessage(), e);
        }
    }
}
