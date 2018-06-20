package com.keredwell.fieldsales.dbhelper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 18/8/2017.
 */

public class DBAdapter {
    private static final String TAG = makeLogTag(DBAdapter.class);

    public static final String DATABASE_NAME = "POSDB.db";

    public static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_C_BP_GROUP = "CREATE TABLE " +
            C_BP_GroupDBAdapter.DATABASE_TABLE + "("
            + C_BP_GroupDBAdapter.COLUMN_C_BP_GROUP_ID + " INTEGER PRIMARY KEY, "
            + C_BP_GroupDBAdapter.COLUMN_NAME + " TEXT " + ");";

    private static final String CREATE_TABLE_C_BPARTNER_LOCATION = "CREATE TABLE " +
            C_BPartner_LocationDBAdapter.DATABASE_TABLE + "("
            + C_BPartner_LocationDBAdapter.COLUMN_C_BPARTNER_LOCATION_ID + " INTEGER PRIMARY KEY, "
            + C_BPartner_LocationDBAdapter.COLUMN_C_BPARTNER_ID + " INTEGER, "
            + C_BPartner_LocationDBAdapter.COLUMN_C_LOCATION_ID + " INTEGER, "
            + C_BPartner_LocationDBAdapter.COLUMN_PHONE + " TEXT " + ");";

    private static final String CREATE_TABLE_C_BPARTNER = "CREATE TABLE " +
            C_BPartnerDBAdapter.DATABASE_TABLE + "("
            + C_BPartnerDBAdapter.COLUMN_C_BPARTNER_ID + " INTEGER PRIMARY KEY, "
            + C_BPartnerDBAdapter.COLUMN_C_BP_GROUP_ID + " INTEGER, "
            + C_BPartnerDBAdapter.COLUMN_NAME + " TEXT " + ");";

    private static final String CREATE_TABLE_C_LOCATION = "CREATE TABLE " +
            C_LocationDBAdapter.DATABASE_TABLE + "("
            + C_LocationDBAdapter.COLUMN_C_LOCATION_ID + " INTEGER PRIMARY KEY, "
            + C_LocationDBAdapter.COLUMN_ADDRESS + " TEXT, "
            + C_LocationDBAdapter.COLUMN_POSTAL + " TEXT " + ");";

    private static final String CREATE_TABLE_C_TAX = "CREATE TABLE " +
            C_TaxDBAdapter.DATABASE_TABLE + "("
            + C_TaxDBAdapter.COLUMN_C_TAX_ID + " INTEGER PRIMARY KEY, "
            + C_TaxDBAdapter.COLUMN_NAME + " TEXT, "
            + C_TaxDBAdapter.COLUMN_RATE + " REAL " + ");";

    private static final String CREATE_TABLE_M_PRODUCT_CATEGORY = "CREATE TABLE " +
            M_Product_CategoryDBAdapter.DATABASE_TABLE + "("
            + M_Product_CategoryDBAdapter.COLUMN_M_PRODUCT_CATEGORY_ID + " INTEGER PRIMARY KEY, "
            + M_Product_CategoryDBAdapter.COLUMN_NAME + " TEXT " + ");";

    private static final String CREATE_TABLE_M_PRODUCT = "CREATE TABLE " +
            M_ProductDBAdapter.DATABASE_TABLE + "("
            + M_ProductDBAdapter.COLUMN_M_PRODUCT_ID + " INTEGER PRIMARY KEY, "
            + M_ProductDBAdapter.COLUMN_NAME + " TEXT, "
            + M_ProductDBAdapter.COLUMN_C_UOM_ID + " INTEGER, "
            + M_ProductDBAdapter.COLUMN_M_PRODUCT_CATEGORY_ID + " INTEGER, "
            + M_ProductDBAdapter.COLUMN_M_LOCATOR_ID + " INTEGER " + ");";

    private static final String CREATE_TABLE_M_PRODUCTPRICE = "CREATE TABLE " +
            M_ProductPriceDBAdapter.DATABASE_TABLE + "("
            + M_ProductPriceDBAdapter.COLUMN_M_PRODUCT_ID + " INTEGER PRIMARY KEY, "
            + M_ProductPriceDBAdapter.COLUMN_M_PRICELIST_VERSION_ID + " INTEGER, "
            + M_ProductPriceDBAdapter.COLUMN_PRICELIST + " INTEGER, "
            + M_ProductPriceDBAdapter.COLUMN_PRICESTD + " INTEGER, "
            + M_ProductPriceDBAdapter.COLUMN_PRICELIMIT + " INTEGER " + ");";

    private static final String CREATE_TABLE_M_PRICELIST_VERSION = "CREATE TABLE " +
            M_Pricelist_VersionDBAdapter.DATABASE_TABLE + "("
            + M_Pricelist_VersionDBAdapter.COLUMN_M_PRICELIST_VERSION_ID + " INTEGER PRIMARY KEY, "
            + M_Pricelist_VersionDBAdapter.COLUMN_M_PRICELIST_ID + " INTEGER " + ");";

    private static final String CREATE_TABLE_M_LOCATOR = "CREATE TABLE " +
            M_LocatorDBAdapter.DATABASE_TABLE + "("
            + M_LocatorDBAdapter.COLUMN_M_LOCATOR_ID + " INTEGER PRIMARY KEY, "
            + M_LocatorDBAdapter.COLUMN_M_WAREHOUSE_ID + " INTEGER, "
            + M_LocatorDBAdapter.COLUMN_X_AISLE + " TEXT, "
            + M_LocatorDBAdapter.COLUMN_Y_BIN + " TEXT, "
            + M_LocatorDBAdapter.COLUMN_Z_LEVEL + " TEXT " + ");";

    private static final String CREATE_TABLE_C_ORDER = "CREATE TABLE " +
            C_OrderDBAdapter.DATABASE_TABLE + "("
            + C_OrderDBAdapter.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_OrderDBAdapter.COLUMN_C_ORDER_ID + " INTEGER, "
            + C_OrderDBAdapter.COLUMN_C_DOCTYPE_ID + " INTEGER, "
            + C_OrderDBAdapter.COLUMN_DOCTYPETARGET_ID + " INTEGER, "
            + C_OrderDBAdapter.COLUMN_SALESREP_ID + " INTEGER, "
            + C_OrderDBAdapter.COLUMN_DATEORDERED + " INTEGER, "
            + C_OrderDBAdapter.COLUMN_C_BPARTNER_ID + " INTEGER, "
            + C_OrderDBAdapter.COLUMN_CUSTOMERNAME + " TEXT, "
            + C_OrderDBAdapter.COLUMN_C_BPARTNER_LOCATION_ID + " INTEGER, "
            + C_OrderDBAdapter.COLUMN_C_BILL_BPARTNER_ID + " INTEGER, "
            + C_OrderDBAdapter.COLUMN_C_BILL_LOCATION_ID + " INTEGER, "
            + C_OrderDBAdapter.COLUMN_TOTALLINES + " INTEGER, "
            + C_OrderDBAdapter.COLUMN_GRANDTOTAL + " INTEGER, "
            + C_OrderDBAdapter.COLUMN_M_WAREHOUSE_ID + " INTEGER, "
            + C_OrderDBAdapter.COLUMN_M_PRICELIST_ID + " INTEGER, "
            + C_OrderDBAdapter.COLUMN_PAYMENTRULE + " TEXT, "
            + C_OrderDBAdapter.COLUMN_C_PAYMENTTERM_ID + " INTEGER, "
            + C_OrderDBAdapter.COLUMN_ISCASH + " INTEGER, "
            + C_OrderDBAdapter.COLUMN_PROCESSORDER_RETVAL + " TEXT, "
            + C_OrderDBAdapter.COLUMN_SYNCDATE + " INTEGER, "
            + C_OrderDBAdapter.COLUMN_SYNCDATE_PROCESSORDER + " INTEGER, "
            + C_OrderDBAdapter.COLUMN_M_INOUT_ID+ " TEXT, "
            + C_OrderDBAdapter.COLUMN_C_INVOICE_ID + " TEXT, "
            + C_OrderDBAdapter.COLUMN_INTERNAL_STATUS + " INTEGER, "
            + C_OrderDBAdapter.COLUMN_DOCUMENTID + " INTEGER " + ");";

    private static final String CREATE_TABLE_C_ORDERLINE = "CREATE TABLE " +
            C_OrderLineDBAdapter.DATABASE_TABLE + "("
            + C_OrderLineDBAdapter.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_OrderLineDBAdapter.COLUMN_ORDERID + " INTEGER, "
            + C_OrderLineDBAdapter.COLUMN_C_ORDER_ID + " INTEGER, "
            + C_OrderLineDBAdapter.COLUMN_C_ORDERLINE_ID + " INTEGER, "
            + C_OrderLineDBAdapter.COLUMN_LINENO + " INTEGER, "
            + C_OrderLineDBAdapter.COLUMN_C_BPARTNER_ID + " INTEGER, "
            + C_OrderLineDBAdapter.COLUMN_C_BPARTNER_LOCATION_ID + " INTEGER, "
            + C_OrderLineDBAdapter.COLUMN_DATEORDERED + " INTEGER, "
            + C_OrderLineDBAdapter.COLUMN_M_PRODUCT_ID + " INTEGER, "
            + C_OrderLineDBAdapter.COLUMN_PRODUCTNAME + " TEXT, "
            + C_OrderLineDBAdapter.COLUMN_M_WAREHOUSE_ID + " INTEGER, "
            + C_OrderLineDBAdapter.COLUMN_M_LOCATOR_ID + " INTEGER, "
            + C_OrderLineDBAdapter.COLUMN_C_UOM_ID + " INTEGER, "
            + C_OrderLineDBAdapter.COLUMN_QTYORDERED + " INTEGER, "
            + C_OrderLineDBAdapter.COLUMN_M_PRICELIST_ID + " INTEGER, "
            + C_OrderLineDBAdapter.COLUMN_PRICELIST + " INTEGER, "
            + C_OrderLineDBAdapter.COLUMN_PRICEACTUAL + " INTEGER, "
            + C_OrderLineDBAdapter.COLUMN_PRICELIMIT + " INTEGER, "
            + C_OrderLineDBAdapter.COLUMN_LINENETAMT + " INTEGER, "
            + C_OrderLineDBAdapter.COLUMN_DISCOUNT + " INTEGER, "
            + C_OrderLineDBAdapter.COLUMN_C_TAX_ID + " INTEGER, "
            + C_OrderLineDBAdapter.COLUMN_SYNCDATE + " INTEGER " + ");";

    protected static Context mContext;
    protected static DatabaseHelper mDbHelper;
    protected static SQLiteDatabase mDb;

    /**
     * Constructor
     * @param ctx
     */
    public DBAdapter(Context ctx)
    {
        this.mContext = ctx.getApplicationContext();
    }

    public DBAdapter open() throws SQLException {
        if (mDbHelper == null)
            mDbHelper = new DatabaseHelper(mContext);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDb.isOpen())
            mDbHelper.close();
    }

    protected static class DatabaseHelper extends SQLiteOpenHelper
    {
        public DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(CREATE_TABLE_C_BP_GROUP);
            db.execSQL(CREATE_TABLE_C_BPARTNER_LOCATION);
            db.execSQL(CREATE_TABLE_C_BPARTNER);
            db.execSQL(CREATE_TABLE_C_LOCATION);
            db.execSQL(CREATE_TABLE_C_TAX);
            db.execSQL(CREATE_TABLE_M_PRODUCT_CATEGORY);
            db.execSQL(CREATE_TABLE_M_PRODUCT);
            db.execSQL(CREATE_TABLE_M_PRODUCTPRICE);
            db.execSQL(CREATE_TABLE_M_PRICELIST_VERSION);
            db.execSQL(CREATE_TABLE_M_LOCATOR);
            db.execSQL(CREATE_TABLE_C_ORDER);
            db.execSQL(CREATE_TABLE_C_ORDERLINE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + C_BP_GroupDBAdapter.DATABASE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + C_BPartner_LocationDBAdapter.DATABASE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + C_BPartnerDBAdapter.DATABASE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + C_LocationDBAdapter.DATABASE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + C_TaxDBAdapter.DATABASE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + M_Product_CategoryDBAdapter.DATABASE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + M_ProductDBAdapter.DATABASE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + M_ProductPriceDBAdapter.DATABASE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + M_Pricelist_VersionDBAdapter.DATABASE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + M_LocatorDBAdapter.DATABASE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + C_OrderDBAdapter.DATABASE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + C_OrderLineDBAdapter.DATABASE_TABLE);
            onCreate(db);
        }
    }
}
