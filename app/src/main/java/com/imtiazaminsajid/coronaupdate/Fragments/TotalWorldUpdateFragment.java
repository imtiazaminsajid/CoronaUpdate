package com.imtiazaminsajid.coronaupdate.Fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imtiazaminsajid.coronaupdate.Adapter.DistrictDetailsAdapter;
import com.imtiazaminsajid.coronaupdate.Adapter.WorldCountryCoronaAdapter;
import com.imtiazaminsajid.coronaupdate.Model.BangladeshAllDataModel;
import com.imtiazaminsajid.coronaupdate.Model.WorldAllCountryModel;
import com.imtiazaminsajid.coronaupdate.R;
import com.imtiazaminsajid.coronaupdate.api_interface.ApiDataInterface;
import com.imtiazaminsajid.coronaupdate.api_interface.WorldCountryDataInterface;
import com.imtiazaminsajid.coronaupdate.databinding.FragmentTotalWorldUpdateBinding;
import com.imtiazaminsajid.coronaupdate.utils.ApiClient;
import com.imtiazaminsajid.coronaupdate.utils.ApiClintForWorldCountry;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TotalWorldUpdateFragment extends Fragment {

    static TotalWorldUpdateFragment instance;

    private Retrofit retrofit;

    WorldAllCountryModel worldAllCountryModel;

    RecyclerView.LayoutManager layoutManager;

    WorldCountryCoronaAdapter worldCountryCoronaAdapter;

    FragmentTotalWorldUpdateBinding fragmentTotalWorldUpdateBinding;

    View view;

    public TotalWorldUpdateFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentTotalWorldUpdateBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_total_world_update, container, false);
        view = fragmentTotalWorldUpdateBinding.getRoot();
        getWorldCountryDataFromApi();

        return view;
    }

    public static TotalWorldUpdateFragment getInstance() {
        if (instance == null)
            instance = new TotalWorldUpdateFragment();
        return instance;
    }


    private void getWorldCountryDataFromApi() {

        retrofit = ApiClintForWorldCountry.getRetrofitInstance();
        WorldCountryDataInterface worldCountryDataInterface = retrofit.create(WorldCountryDataInterface.class);
        Call call = worldCountryDataInterface.getAllWorldCountryCoronaData();
        call.enqueue(new Callback<WorldAllCountryModel>() {
            @Override
            public void onResponse(Call<WorldAllCountryModel> call, Response<WorldAllCountryModel> response) {
                Log.d("api", "code for world " + response.code());
                if (response.isSuccessful()) {
                    fragmentTotalWorldUpdateBinding.worldCountryProgressBar.setVisibility(View.GONE);
                    worldAllCountryModel = new WorldAllCountryModel();
                    worldAllCountryModel = response.body();
                    setAdapter();
                }
            }

            @Override
            public void onFailure(Call<WorldAllCountryModel> call, Throwable t) {
                fragmentTotalWorldUpdateBinding.worldCountryProgressBar.setVisibility(View.VISIBLE);
            }
        });

    }

    public void setAdapter() {
        layoutManager = new LinearLayoutManager(getContext());
        worldCountryCoronaAdapter = new WorldCountryCoronaAdapter(getContext(), worldAllCountryModel);
        fragmentTotalWorldUpdateBinding.worldCountryRecycleView.setLayoutManager(layoutManager);
        fragmentTotalWorldUpdateBinding.worldCountryRecycleView.setAdapter(worldCountryCoronaAdapter);
    }
}
