package com.keredwell.fieldsales.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.widget.EditText;
import android.widget.Toast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keredwell.fieldsales.R;
import com.keredwell.fieldsales.ui.base.BaseFragment;
import com.keredwell.fieldsales.util.PropUtil;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class LoginSettingFragment extends BaseFragment {

    private static final String TAG = makeLogTag(LoginSettingFragment.class);

    private EditText serverUriEdit;
    private EditText nameSpaceEdit;
    private EditText langEdit;
    private EditText clientIdEdit;
    private EditText roleIdEdit;
    private EditText orgIdEdit;
    private EditText warehouseIdEdit;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.include_setting_view, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        if(view != null) {
            PropUtil prop = new PropUtil();

            serverUriEdit = (EditText) view.findViewById(R.id.serverUri);
            serverUriEdit.setText(prop.getProperty("serverUri"), EditText.BufferType.EDITABLE);

            nameSpaceEdit = (EditText) view.findViewById(R.id.nameSpace);
            nameSpaceEdit.setText(prop.getProperty("nameSpace"), EditText.BufferType.EDITABLE);

            langEdit = (EditText) view.findViewById(R.id.lang);
            langEdit.setText(prop.getProperty("lang"), EditText.BufferType.EDITABLE);

            clientIdEdit = (EditText) view.findViewById(R.id.clientId);
            clientIdEdit.setText(prop.getProperty("clientId"), EditText.BufferType.EDITABLE);

            roleIdEdit = (EditText) view.findViewById(R.id.roleId);
            roleIdEdit.setText(prop.getProperty("roleId"), EditText.BufferType.EDITABLE);

            orgIdEdit = (EditText) view.findViewById(R.id.orgId);
            orgIdEdit.setText(prop.getProperty("orgId"), EditText.BufferType.EDITABLE);

            warehouseIdEdit = (EditText) view.findViewById(R.id.warehouseId);
            warehouseIdEdit.setText(prop.getProperty("warehouseId"), EditText.BufferType.EDITABLE);

            userServiceTypeEdit = (EditText) view.findViewById(R.id.userServiceType);
            userServiceTypeEdit.setText(prop.getProperty("userServiceType"), EditText.BufferType.EDITABLE);

            bpartnerServiceTypeEdit = (EditText) view.findViewById(R.id.bpartnerServiceType);
            bpartnerServiceTypeEdit.setText(prop.getProperty("bpartnerServiceType"), EditText.BufferType.EDITABLE);

            bpartnerLocationServiceTypeEdit = (EditText) view.findViewById(R.id.bpartnerLocationServiceType);
            bpartnerLocationServiceTypeEdit.setText(prop.getProperty("bpartnerLocationServiceType"), EditText.BufferType.EDITABLE);

            locationServiceTypeEdit = (EditText) view.findViewById(R.id.locationServiceType);
            locationServiceTypeEdit.setText(prop.getProperty("locationServiceType"), EditText.BufferType.EDITABLE);

            bpGroupServiceTypeEdit = (EditText) view.findViewById(R.id.bpGroupServiceType);
            bpGroupServiceTypeEdit.setText(prop.getProperty("bpGroupServiceType"), EditText.BufferType.EDITABLE);

            productCategoryServiceTypeEdit = (EditText) view.findViewById(R.id.productCategoryServiceType);
            productCategoryServiceTypeEdit.setText(prop.getProperty("productCategoryServiceType"), EditText.BufferType.EDITABLE);

            productServiceTypeEdit = (EditText) view.findViewById(R.id.productServiceType);
            productServiceTypeEdit.setText(prop.getProperty("productServiceType"), EditText.BufferType.EDITABLE);

            productPriceServiceTypeEdit = (EditText) view.findViewById(R.id.productPriceServiceType);
            productPriceServiceTypeEdit.setText(prop.getProperty("productPriceServiceType"), EditText.BufferType.EDITABLE);

            pricelistVersionServiceTypeEdit = (EditText) view.findViewById(R.id.pricelistVersionServiceType);
            pricelistVersionServiceTypeEdit.setText(prop.getProperty("productPriceServiceType"), EditText.BufferType.EDITABLE);

            orderServiceTypeEdit = (EditText) view.findViewById(R.id.orderServiceType);
            orderServiceTypeEdit.setText(prop.getProperty("orderServiceType"), EditText.BufferType.EDITABLE);

            orderlineServiceTypeEdit = (EditText) view.findViewById(R.id.orderlineServiceType);
            orderlineServiceTypeEdit.setText(prop.getProperty("orderlineServiceType"), EditText.BufferType.EDITABLE);

            processOrderServiceTypeEdit = (EditText) view.findViewById(R.id.processOrderServiceType);
            processOrderServiceTypeEdit.setText(prop.getProperty("processOrderServiceType"), EditText.BufferType.EDITABLE);

            orderReceiveServiceTypeEdit = (EditText) view.findViewById(R.id.orderReceiveServiceType);
            orderReceiveServiceTypeEdit.setText(prop.getProperty("orderReceiveServiceType"), EditText.BufferType.EDITABLE);

            orderlineReceiveServiceTypeEdit = (EditText) view.findViewById(R.id.orderlineReceiveServiceType);
            orderlineReceiveServiceTypeEdit.setText(prop.getProperty("orderlineReceiveServiceType"), EditText.BufferType.EDITABLE);

            orderDocIDServiceTypeEdit = (EditText) view.findViewById(R.id.orderDocIDServiceType);
            orderDocIDServiceTypeEdit.setText(prop.getProperty("orderDocIDServiceType"), EditText.BufferType.EDITABLE);

            c_paymentterm_id_cashEdit = (EditText) view.findViewById(R.id.c_paymentterm_id_cash);
            c_paymentterm_id_cashEdit.setText(prop.getProperty("c_paymentterm_id_cash"), EditText.BufferType.EDITABLE);

            c_paymentterm_id_creditEdit = (EditText) view.findViewById(R.id.c_paymentterm_id_credit);
            c_paymentterm_id_creditEdit.setText(prop.getProperty("c_paymentterm_id_credit"), EditText.BufferType.EDITABLE);

            c_doctypetarget_id_cashEdit = (EditText) view.findViewById(R.id.c_doctypetarget_id_cash);
            c_doctypetarget_id_cashEdit.setText(prop.getProperty("c_doctypetarget_id_cash"), EditText.BufferType.EDITABLE);

            c_doctypetarget_id_creditEdit = (EditText) view.findViewById(R.id.c_doctypetarget_id_credit);
            c_doctypetarget_id_creditEdit.setText(prop.getProperty("c_doctypetarget_id_credit"), EditText.BufferType.EDITABLE);

            c_currency_idEdit = (EditText) view.findViewById(R.id.c_currency_id);
            c_currency_idEdit.setText(prop.getProperty("c_currency_id"), EditText.BufferType.EDITABLE);

            print_widthEdit = (EditText) view.findViewById(R.id.print_width);
            print_widthEdit.setText(prop.getProperty("print_width"), EditText.BufferType.EDITABLE);

            print_heightEdit = (EditText) view.findViewById(R.id.print_height);
            print_heightEdit.setText(prop.getProperty("print_height"), EditText.BufferType.EDITABLE);

            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
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

                    Toast.makeText(getActivity(),
                            "Properties saved.",
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
