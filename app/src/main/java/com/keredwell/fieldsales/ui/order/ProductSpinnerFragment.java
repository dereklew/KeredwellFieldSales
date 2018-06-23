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

import java.util.ArrayList;
import java.util.List;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class ProductSpinnerFragment extends Fragment {
    private static final String TAG = makeLogTag(ProductSpinnerFragment.class);

    private List<String> productcateories = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.spinner_product_categories, container, false);

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
        Spinner spinnerProductCategory = (Spinner) getView().getRootView().findViewById(R.id.spinnerProductCategory);

        spinnerProductCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                loadList();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    private void loadSpinnerData() {
        productcateories = ((ProductListActivity)getActivity()).getListProductCategories();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, productcateories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerProductCategory = (Spinner) getView().getRootView().findViewById(R.id.spinnerProductCategory);

        spinnerProductCategory.setAdapter(dataAdapter);
    }

    private void loadList(){
        Spinner spinnerProductCategory = (Spinner) getView().getRootView().findViewById(R.id.spinnerProductCategory);

        ((ProductListActivity)getActivity()).loadList(spinnerProductCategory.getSelectedItem().toString(), 1);
    }
}
