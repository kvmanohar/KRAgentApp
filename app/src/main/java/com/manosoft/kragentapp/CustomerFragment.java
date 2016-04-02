package com.manosoft.kragentapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerFragment extends Fragment {

    public View view;
    public DBHelper dbHelper;
    public Cursor cursor;
    public ListView lvCustomer;

    public CustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /* Inflate the fragment view */
        view = inflater.inflate(R.layout.fragment_customer, container, false);

        /* Get Main activity class to access dbHelper */
        MainActivity activity = (MainActivity) getActivity();
        dbHelper = activity.returndbHelper();

        /* Get all customer records to the cursor */
        cursor = dbHelper.getAllRecords(dbHelper.CUSTOMER_TABLE);

        /* Populate Customer ListView */
        lvCustomer = (ListView) view.findViewById(R.id.lv_customer);
        populateCustomerListView(cursor);

        return view;
    }

    public void populateCustomerListView(Cursor cursor) {

        /* Desired columns to display */
        String[] columns = new String[]{
                dbHelper.CuT_customerName, dbHelper.CuT_hMobile, dbHelper.CuT_hAddress, dbHelper.CuT_hCity};

        /*XML defined view to which the data will be bound to*/
        int to[] = new int[]{
                R.id.tv_customerName,
                R.id.tv_customerMobile,
                R.id.tv_customerAddress,
                R.id.tv_customerCity};

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(view.getContext(), R.layout.customer_record_row, cursor, columns, to, 0);
        lvCustomer.setAdapter(cursorAdapter);
    }

}
