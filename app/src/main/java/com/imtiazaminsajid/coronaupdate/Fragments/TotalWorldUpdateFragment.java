package com.imtiazaminsajid.coronaupdate.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imtiazaminsajid.coronaupdate.Adapter.WorldCountryCoronaAdapter;
import com.imtiazaminsajid.coronaupdate.Model.WorldAllCountryModel;
import com.imtiazaminsajid.coronaupdate.Model.WorldDatumModel;
import com.imtiazaminsajid.coronaupdate.NewModel.AllCountryData;
import com.imtiazaminsajid.coronaupdate.R;
import com.imtiazaminsajid.coronaupdate.api_interface.ApiDataInterface;
import com.imtiazaminsajid.coronaupdate.api_interface.WorldCountryDataInterface;
import com.imtiazaminsajid.coronaupdate.databinding.FragmentTotalWorldUpdateBinding;
import com.imtiazaminsajid.coronaupdate.utils.ApiClientForAllCountry;
import com.imtiazaminsajid.coronaupdate.utils.ApiClintForWorldCountry;
import com.imtiazaminsajid.coronaupdate.utils.Utils;

import java.util.ArrayList;
import java.util.List;

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

    List<WorldDatumModel> worldDatumModels = new ArrayList<>();

    FragmentTotalWorldUpdateBinding fragmentTotalWorldUpdateBinding;

    View view;

    List<AllCountryData> allCountryData = new ArrayList<>();

    public TotalWorldUpdateFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentTotalWorldUpdateBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_total_world_update, container, false);
        view = fragmentTotalWorldUpdateBinding.getRoot();

        setAdapter();
//        getWorldCountryDataFromApi();

        getAllCountryDataFromApi();

        fragmentTotalWorldUpdateBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (allCountryData != null) {
                    filterData(query, allCountryData);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (allCountryData != null) {
                    filterData(newText, allCountryData);
                }
                return false;
            }
        });


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

//                    worldCountryCoronaAdapter.notifyDataSetChanged();
                    if (worldAllCountryModel != null) {
//                        worldCountryCoronaAdapter.notifyDataSetChanged();
                        worldCountryCoronaAdapter.setWorldDatumModels(worldAllCountryModel.getData());
                    }
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
        worldCountryCoronaAdapter = new WorldCountryCoronaAdapter(getContext(), allCountryData);
        fragmentTotalWorldUpdateBinding.worldCountryRecycleView.setLayoutManager(layoutManager);
        fragmentTotalWorldUpdateBinding.worldCountryRecycleView.setAdapter(worldCountryCoronaAdapter);
    }

    private static List<WorldDatumModel> filter(List<WorldDatumModel> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<WorldDatumModel> filteredModelList = new ArrayList<>();
        for (WorldDatumModel model : models) {
            final String text = model.getCountryName().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    private void filterData(String query, List<AllCountryData> models) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<AllCountryData> filteredModelList = new ArrayList<>();
        for (AllCountryData model : models) {
            final String text = model.getCountry().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        worldCountryCoronaAdapter.setAllCountryData(filteredModelList);
    }


    private void getAllCountryDataFromApi() {

        if (Utils.checkInternetConnection(getContext())) {
            retrofit = ApiClientForAllCountry.getRetrofitInstance();
            ApiDataInterface apiDataInterface = retrofit.create(ApiDataInterface.class);
            Call call = apiDataInterface.getAllCountryData();
            call.enqueue(new Callback<List<AllCountryData>>() {
                @Override
                public void onResponse(Call<List<AllCountryData>> call, Response<List<AllCountryData>> response) {

                    if (response.isSuccessful()) {
                        Log.d("allCountryData", "allCountryData " + response.code());
                        fragmentTotalWorldUpdateBinding.worldCountryProgressBar.setVisibility(View.GONE);
                        allCountryData = response.body();

                        if (allCountryData != null) {
                            worldCountryCoronaAdapter.setAllCountryData(allCountryData);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<AllCountryData>> call, Throwable t) {
                    fragmentTotalWorldUpdateBinding.worldCountryProgressBar.setVisibility(View.VISIBLE);
                }
            });

        } else {
        }

    }
}
