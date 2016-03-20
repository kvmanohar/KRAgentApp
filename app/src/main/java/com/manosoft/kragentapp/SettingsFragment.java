package com.manosoft.kragentapp;


import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    public DBHelper dbHelper;
    public Cursor cursor;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        MainActivity activity = (MainActivity) getActivity();

        dbHelper= activity.returndbHelper();
        cursor = dbHelper.getAllRecords(dbHelper.CUSTOMER_TABLE);

        Log.v("Table row", DatabaseUtils.dumpCursorToString(cursor));
        return inflater.inflate(R.layout.fragment_settings, container, false);

    }

}
