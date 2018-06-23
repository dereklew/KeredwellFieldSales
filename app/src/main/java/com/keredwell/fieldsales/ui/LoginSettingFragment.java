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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.include_setting_login_view, null);
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

                    PropUtil prop = new PropUtil();

                    prop.setProperty("serverUri", serverUri);
                    prop.setProperty("nameSpace", nameSpace);
                    prop.setProperty("lang", lang);
                    prop.setProperty("clientId", clientId);
                    prop.setProperty("roleId", roleId);
                    prop.setProperty("orgId", orgId);
                    prop.setProperty("warehouseId", warehouseId);

                    Toast.makeText(getActivity(),
                            "Properties saved.",
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
