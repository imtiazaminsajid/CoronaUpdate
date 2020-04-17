package com.imtiazaminsajid.coronaupdate.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imtiazaminsajid.coronaupdate.R;

public class BangladeshDetailFragment extends Fragment {

    static BangladeshDetailFragment instance;

    public BangladeshDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_bangladesh_detail, container, false);

        return view;
    }

    public static BangladeshDetailFragment getInstance() {
        if (instance == null)
            instance = new BangladeshDetailFragment();
        return instance;
    }
}
