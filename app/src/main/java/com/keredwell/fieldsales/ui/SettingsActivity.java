package com.keredwell.fieldsales.ui;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.keredwell.fieldsales.R;
import com.keredwell.fieldsales.ui.base.BaseActivity;
import com.keredwell.fieldsales.util.PropUtil;
import android.widget.EditText;
import android.widget.Toast;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * This Activity provides several settings. Activity contains {@link PreferenceFragment} as inner class.
 *
 * Created by Andreas Schrade on 14.12.2015.
 */
public class SettingsActivity extends BaseActivity {
    private static final String TAG = makeLogTag(SettingsActivity.class);

    private EditText serverUriEdit;
    private EditText nameSpaceEdit;
    private EditText langEdit;
    private EditText clientIdEdit;
    private EditText roleIdEdit;
    private EditText orgIdEdit;
    private EditText warehouseIdEdit;
    private EditText adUserServiceTypeEdit;
    private EditText adUserRolesServiceTypeEdit;
    private EditText userServiceTypeEdit;
    private EditText bpartnerServiceTypeEdit;
    private EditText bpartnerLocationServiceTypeEdit;
    private EditText locationServiceTypeEdit;
    private EditText bpGroupServiceTypeEdit;
    private EditText productCategoryServiceTypeEdit;
    private EditText productServiceTypeEdit;
    private EditText pricelistVersionServiceTypeEdit;
    private EditText productPriceServiceTypeEdit;
    private EditText orderServiceTypeEdit;
    private EditText orderlineServiceTypeEdit;
    private EditText processOrderServiceTypeEdit;
    private EditText orderReceiveServiceTypeEdit;
    private EditText orderlineReceiveServiceTypeEdit;
    private EditText orderDocIDServiceTypeEdit;
    private EditText c_paymentterm_id_cashEdit;
    private EditText c_paymentterm_id_creditEdit;
    private EditText c_doctypetarget_id_cashEdit;
    private EditText c_doctypetarget_id_creditEdit;
    private EditText print_widthEdit;
    private EditText print_heightEdit;
    private EditText c_currency_idEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setupToolbar();

        PropUtil prop = new PropUtil();

        serverUriEdit = (EditText) findViewById(R.id.serverUri);
        serverUriEdit.setText(prop.getProperty("serverUri"), EditText.BufferType.EDITABLE);

        nameSpaceEdit = (EditText) findViewById(R.id.nameSpace);
        nameSpaceEdit.setText(prop.getProperty("nameSpace"), EditText.BufferType.EDITABLE);

        langEdit = (EditText) findViewById(R.id.lang);
        langEdit.setText(prop.getProperty("lang"), EditText.BufferType.EDITABLE);

        clientIdEdit = (EditText) findViewById(R.id.clientId);
        clientIdEdit.setText(prop.getProperty("clientId"), EditText.BufferType.EDITABLE);

        roleIdEdit = (EditText) findViewById(R.id.roleId);
        roleIdEdit.setText(prop.getProperty("roleId"), EditText.BufferType.EDITABLE);

        orgIdEdit = (EditText) findViewById(R.id.orgId);
        orgIdEdit.setText(prop.getProperty("orgId"), EditText.BufferType.EDITABLE);

        warehouseIdEdit = (EditText) findViewById(R.id.warehouseId);
        warehouseIdEdit.setText(prop.getProperty("warehouseId"), EditText.BufferType.EDITABLE);

        adUserServiceTypeEdit = (EditText) findViewById(R.id.adUserServiceType);
        adUserServiceTypeEdit.setText(prop.getProperty("adUserServiceType"), EditText.BufferType.EDITABLE);

        adUserRolesServiceTypeEdit = (EditText) findViewById(R.id.adUserRolesServiceType);
        adUserRolesServiceTypeEdit.setText(prop.getProperty("adUserRolesServiceType"), EditText.BufferType.EDITABLE);

        userServiceTypeEdit = (EditText) findViewById(R.id.userServiceType);
        userServiceTypeEdit.setText(prop.getProperty("userServiceType"), EditText.BufferType.EDITABLE);

        bpartnerServiceTypeEdit = (EditText) findViewById(R.id.bpartnerServiceType);
        bpartnerServiceTypeEdit.setText(prop.getProperty("bpartnerServiceType"), EditText.BufferType.EDITABLE);

        bpartnerLocationServiceTypeEdit = (EditText) findViewById(R.id.bpartnerLocationServiceType);
        bpartnerLocationServiceTypeEdit.setText(prop.getProperty("bpartnerLocationServiceType"), EditText.BufferType.EDITABLE);

        locationServiceTypeEdit = (EditText) findViewById(R.id.locationServiceType);
        locationServiceTypeEdit.setText(prop.getProperty("locationServiceType"), EditText.BufferType.EDITABLE);

        bpGroupServiceTypeEdit = (EditText) findViewById(R.id.bpGroupServiceType);
        bpGroupServiceTypeEdit.setText(prop.getProperty("bpGroupServiceType"), EditText.BufferType.EDITABLE);

        productCategoryServiceTypeEdit = (EditText) findViewById(R.id.productCategoryServiceType);
        productCategoryServiceTypeEdit.setText(prop.getProperty("productCategoryServiceType"), EditText.BufferType.EDITABLE);

        productServiceTypeEdit = (EditText) findViewById(R.id.productServiceType);
        productServiceTypeEdit.setText(prop.getProperty("productServiceType"), EditText.BufferType.EDITABLE);

        productPriceServiceTypeEdit = (EditText) findViewById(R.id.productPriceServiceType);
        productPriceServiceTypeEdit.setText(prop.getProperty("productPriceServiceType"), EditText.BufferType.EDITABLE);

        pricelistVersionServiceTypeEdit = (EditText) findViewById(R.id.pricelistVersionServiceType);
        pricelistVersionServiceTypeEdit.setText(prop.getProperty("productPriceServiceType"), EditText.BufferType.EDITABLE);

        orderServiceTypeEdit = (EditText) findViewById(R.id.orderServiceType);
        orderServiceTypeEdit.setText(prop.getProperty("orderServiceType"), EditText.BufferType.EDITABLE);

        orderlineServiceTypeEdit = (EditText) findViewById(R.id.orderlineServiceType);
        orderlineServiceTypeEdit.setText(prop.getProperty("orderlineServiceType"), EditText.BufferType.EDITABLE);

        processOrderServiceTypeEdit = (EditText) findViewById(R.id.processOrderServiceType);
        processOrderServiceTypeEdit.setText(prop.getProperty("processOrderServiceType"), EditText.BufferType.EDITABLE);

        orderReceiveServiceTypeEdit = (EditText) findViewById(R.id.orderReceiveServiceType);
        orderReceiveServiceTypeEdit.setText(prop.getProperty("orderReceiveServiceType"), EditText.BufferType.EDITABLE);

        orderlineReceiveServiceTypeEdit = (EditText) findViewById(R.id.orderlineReceiveServiceType);
        orderlineReceiveServiceTypeEdit.setText(prop.getProperty("orderlineReceiveServiceType"), EditText.BufferType.EDITABLE);

        orderDocIDServiceTypeEdit = (EditText) findViewById(R.id.orderDocIDServiceType);
        orderDocIDServiceTypeEdit.setText(prop.getProperty("orderDocIDServiceType"), EditText.BufferType.EDITABLE);

        c_paymentterm_id_cashEdit = (EditText) findViewById(R.id.c_paymentterm_id_cash);
        c_paymentterm_id_cashEdit.setText(prop.getProperty("c_paymentterm_id_cash"), EditText.BufferType.EDITABLE);

        c_paymentterm_id_creditEdit = (EditText) findViewById(R.id.c_paymentterm_id_credit);
        c_paymentterm_id_creditEdit.setText(prop.getProperty("c_paymentterm_id_credit"), EditText.BufferType.EDITABLE);

        c_doctypetarget_id_cashEdit = (EditText) findViewById(R.id.c_doctypetarget_id_cash);
        c_doctypetarget_id_cashEdit.setText(prop.getProperty("c_doctypetarget_id_cash"), EditText.BufferType.EDITABLE);

        c_doctypetarget_id_creditEdit = (EditText) findViewById(R.id.c_doctypetarget_id_credit);
        c_doctypetarget_id_creditEdit.setText(prop.getProperty("c_doctypetarget_id_credit"), EditText.BufferType.EDITABLE);

        c_currency_idEdit = (EditText) findViewById(R.id.c_currency_id);
        c_currency_idEdit.setText(prop.getProperty("c_currency_id"), EditText.BufferType.EDITABLE);

        print_widthEdit = (EditText) findViewById(R.id.print_width);
        print_widthEdit.setText(prop.getProperty("print_width"), EditText.BufferType.EDITABLE);

        print_heightEdit = (EditText) findViewById(R.id.print_height);
        print_heightEdit.setText(prop.getProperty("print_height"), EditText.BufferType.EDITABLE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serverUri = serverUriEdit.getText().toString();
                if (serverUri == "")
                    return;

                String nameSpace = nameSpaceEdit.getText().toString();
                if (nameSpace == "")
                    return;

                String lang = langEdit.getText().toString();
                if (lang == "")
                    return;

                String clientId = clientIdEdit.getText().toString();
                if (clientId == "")
                    return;

                String roleId = roleIdEdit.getText().toString();
                if (roleId == "")
                    return;

                String orgId = orgIdEdit.getText().toString();
                if (orgId == "")
                    return;

                String warehouseId = warehouseIdEdit.getText().toString();
                if (warehouseId == "")
                    return;

                String adUserServiceType = adUserServiceTypeEdit.getText().toString();
                if (adUserServiceType == "")
                    return;

                String adUserRolesServiceType = adUserRolesServiceTypeEdit.getText().toString();
                if (adUserRolesServiceType == "")
                    return;

                String userServiceType = userServiceTypeEdit.getText().toString();
                if (userServiceType == "")
                    return;

                String bpartnerServiceType = bpartnerServiceTypeEdit.getText().toString();
                if (bpartnerServiceType == "")
                    return;

                String bpartnerLocationServiceType = bpartnerLocationServiceTypeEdit.getText().toString();
                if (bpartnerLocationServiceType == "")
                    return;

                String locationServiceType = locationServiceTypeEdit.getText().toString();
                if (locationServiceType == "")
                    return;

                String bpGroupServiceType = bpGroupServiceTypeEdit.getText().toString();
                if (bpGroupServiceType == "")
                    return;

                String productCategoryServiceType = productCategoryServiceTypeEdit.getText().toString();
                if (productCategoryServiceType == "")
                    return;

                String productServiceType = productServiceTypeEdit.getText().toString();
                if (productServiceType == "")
                    return;

                String productPriceServiceType = productPriceServiceTypeEdit.getText().toString();
                if (productPriceServiceType == "")
                    return;

                String pricelistVersionServiceType = pricelistVersionServiceTypeEdit.getText().toString();
                if (pricelistVersionServiceType == "")
                    return;

                String orderServiceType = orderServiceTypeEdit.getText().toString();
                if (orderServiceType == "")
                    return;

                String orderlineServiceType = orderlineServiceTypeEdit.getText().toString();
                if (orderlineServiceType == "")
                    return;

                String processOrderServiceType = processOrderServiceTypeEdit.getText().toString();
                if (processOrderServiceType == "")
                    return;

                String orderReceiveServiceType = orderReceiveServiceTypeEdit.getText().toString();
                if (orderReceiveServiceType == "")
                    return;

                String orderlineReceiveServiceType = orderlineReceiveServiceTypeEdit.getText().toString();
                if (orderlineReceiveServiceType == "")
                    return;

                String orderDocIDServiceType = orderDocIDServiceTypeEdit.getText().toString();
                if (orderDocIDServiceType == "")
                    return;

                String c_paymentterm_id_cash = c_paymentterm_id_cashEdit.getText().toString();
                if (c_paymentterm_id_cash == "")
                    return;

                String c_paymentterm_id_credit = c_paymentterm_id_creditEdit.getText().toString();
                if (c_paymentterm_id_credit == "")
                    return;

                String c_doctypetarget_id_cash = c_doctypetarget_id_cashEdit.getText().toString();
                if (c_doctypetarget_id_cash == "")
                    return;

                String c_doctypetarget_id_credit = c_doctypetarget_id_creditEdit.getText().toString();
                if (c_doctypetarget_id_credit == "")
                    return;

                String c_currency_id = c_currency_idEdit.getText().toString();
                if (c_currency_id == "")
                    return;

                String print_width = print_widthEdit.getText().toString();
                if (print_width == "")
                    return;

                String print_height = print_heightEdit.getText().toString();
                if (print_height == "")
                    return;

                PropUtil prop = new PropUtil();

                prop.setProperty("serverUri", serverUri);
                prop.setProperty("nameSpace", nameSpace);
                prop.setProperty("lang", lang);
                prop.setProperty("clientId", clientId);
                prop.setProperty("roleId", roleId);
                prop.setProperty("orgId", orgId);
                prop.setProperty("warehouseId", warehouseId);
                prop.setProperty("adUserServiceType", adUserServiceType);
                prop.setProperty("adUserRolesServiceType", adUserRolesServiceType);
                prop.setProperty("userServiceType", userServiceType);
                prop.setProperty("bpartnerServiceType", bpartnerServiceType);
                prop.setProperty("bpartnerLocationServiceType", bpartnerLocationServiceType);
                prop.setProperty("locationServiceType", locationServiceType);
                prop.setProperty("bpGroupServiceType", bpGroupServiceType);
                prop.setProperty("productCategoryServiceType", productCategoryServiceType);
                prop.setProperty("productServiceType", productServiceType);
                prop.setProperty("productPriceServiceType", productPriceServiceType);
                prop.setProperty("pricelistVersionServiceType", pricelistVersionServiceType);
                prop.setProperty("orderServiceType", orderServiceType);
                prop.setProperty("orderlineServiceType", orderlineServiceType);
                prop.setProperty("processOrderServiceType", processOrderServiceType);
                prop.setProperty("orderReceiveServiceType", orderReceiveServiceType);
                prop.setProperty("orderlineReceiveServiceType", orderlineReceiveServiceType);
                prop.setProperty("orderDocIDServiceType", orderDocIDServiceType);
                prop.setProperty("c_paymentterm_id_cash", c_paymentterm_id_cash);
                prop.setProperty("c_paymentterm_id_credit", c_paymentterm_id_credit);
                prop.setProperty("c_doctypetarget_id_cash", c_doctypetarget_id_cash);
                prop.setProperty("c_doctypetarget_id_credit", c_doctypetarget_id_credit);
                prop.setProperty("c_currency_id", c_currency_id);
                prop.setProperty("print_width", print_width);
                prop.setProperty("print_height", print_height);

                Toast.makeText(SettingsActivity.this,
                        "Properties saved.",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sync_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                openDrawer();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getSelfNavDrawerItem() {
        return R.id.nav_settings;
    }

    @Override
    public boolean providesActivityToolbar() {
        return true;
    }
}
