package com.keredwell.fieldsales.ui.order;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.keredwell.fieldsales.R;
import com.keredwell.fieldsales.data.C_Tax;
import com.keredwell.fieldsales.dbhelper.C_TaxDBAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class TaxSpinnerFragment extends Fragment {
    private static final String TAG = makeLogTag(TaxSpinnerFragment.class);

    private List<String> taxes = new ArrayList<>();
    private ArrayList<C_Tax> taxs = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.spinner_tax, container, false);

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
        Spinner spinnerTax = (Spinner) getView().getRootView().findViewById(R.id.spinnerTax);

        spinnerTax.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                ((OrderListActivity)getActivity()).setTax(taxs.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    private void loadSpinnerData() {
        C_TaxDBAdapter tdb = new C_TaxDBAdapter(getActivity());
        taxes = tdb.getAllTaxesName();
        taxs = tdb.getAllTaxes();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.simple_spinner_item, taxes);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerTax = (Spinner) getView().getRootView().findViewById(R.id.spinnerTax);

        spinnerTax.setAdapter(dataAdapter);
    }
}
