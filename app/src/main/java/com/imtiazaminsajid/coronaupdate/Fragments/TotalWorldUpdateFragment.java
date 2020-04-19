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
import com.imtiazaminsajid.coronaupdate.R;
import com.imtiazaminsajid.coronaupdate.api_interface.WorldCountryDataInterface;
import com.imtiazaminsajid.coronaupdate.databinding.FragmentTotalWorldUpdateBinding;
import com.imtiazaminsajid.coronaupdate.utils.ApiClintForWorldCountry;

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

        fragmentTotalWorldUpdateBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                filterData(query,worldAllCountryModel.getData());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterData(newText,worldAllCountryModel.getData());
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
                    setAdapter();
                    worldCountryCoronaAdapter.notifyDataSetChanged();
                    worldCountryCoronaAdapter.setWorldDatumModels(worldAllCountryModel.getData());
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
        worldCountryCoronaAdapter = new WorldCountryCoronaAdapter(getContext(), worldAllCountryModel.getData());
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

    private void filterData(String query, List<WorldDatumModel> models) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<WorldDatumModel> filteredModelList = new ArrayList<>();
        for (WorldDatumModel model : models) {
            final String text = model.getCountryName().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        worldCountryCoronaAdapter.setWorldDatumModels(filteredModelList);
    }
}
