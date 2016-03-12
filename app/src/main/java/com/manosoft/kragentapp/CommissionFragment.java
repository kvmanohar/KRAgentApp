package com.manosoft.kragentapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommissionFragment extends Fragment {

    private static TextView tv;

    public CommissionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_commission, container, false);

        tv = (TextView) v.findViewById(R.id.tv_commission);

        Calendar c= Calendar.getInstance();
        int seconds = c.get(Calendar.SECOND);

        tv.setText("Commission Frag : " + seconds);

        return v;
    }

}
