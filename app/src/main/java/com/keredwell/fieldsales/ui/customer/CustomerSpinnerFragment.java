package com.keredwell.fieldsales.ui.customer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.keredwell.fieldsales.R;

import java.util.ArrayList;
import java.util.List;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 24/8/2017.
 */

public class CustomerSpinnerFragment extends Fragment {
    private static final String TAG = makeLogTag(CustomerSpinnerFragment.class);

    private List<String> customergroups = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.spinner_customer_groups, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addListenerOnSpinnerItemSelection();
        loadSpinnerData();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void addListenerOnSpinnerItemSelection() {
        // Spinner element
        Spinner spinnerCustomerGroup = (Spinner) getView().getRootView().findViewById(R.id.spinnerCustomerGroup);

        spinnerCustomerGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                loadList();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerData() {
        customergroups = ((CustomerListActivity)getActivity()).getListCustomerGroups();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, customergroups);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Spinner element
        Spinner spinnerCustomerGroup = (Spinner) getView().getRootView().findViewById(R.id.spinnerCustomerGroup);

        // attaching data adapter to spinner
        spinnerCustomerGroup.setAdapter(dataAdapter);
    }

    private void loadList(){
        Spinner spinnerCustomerGroup = (Spinner) getView().getRootView().findViewById(R.id.spinnerCustomerGroup);

        ((CustomerListActivity)getActivity()).loadList(spinnerCustomerGroup.getSelectedItem().toString(), 1);
    }
}