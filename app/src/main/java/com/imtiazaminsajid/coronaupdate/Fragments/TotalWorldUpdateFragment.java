package com.imtiazaminsajid.coronaupdate.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imtiazaminsajid.coronaupdate.R;

public class TotalWorldUpdateFragment extends Fragment {

    static TotalWorldUpdateFragment instance;

    public TotalWorldUpdateFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_total_world_update, container, false);
        return view;
    }

    public static TotalWorldUpdateFragment getInstance() {
        if (instance == null)
            instance = new TotalWorldUpdateFragment();
        return instance;
    }
}
