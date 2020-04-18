package com.imtiazaminsajid.coronaupdate.Fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imtiazaminsajid.coronaupdate.R;
import com.imtiazaminsajid.coronaupdate.databinding.FragmentPreventCoronavirusBinding;

public class PreventCoronavirusFragment extends Fragment {

    static PreventCoronavirusFragment instance;

    FragmentPreventCoronavirusBinding fragmentPreventCoronavirusBinding;

    View view;

    public PreventCoronavirusFragment() {
        // Required empty public constructor
    }

    public static PreventCoronavirusFragment getInstance() {
        if (instance == null)
            instance = new PreventCoronavirusFragment();
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentPreventCoronavirusBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_prevent_coronavirus, container, false);
        view = fragmentPreventCoronavirusBinding.getRoot();

        return view;
    }
}
