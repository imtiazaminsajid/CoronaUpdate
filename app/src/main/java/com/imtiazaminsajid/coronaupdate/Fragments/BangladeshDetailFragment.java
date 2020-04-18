package com.imtiazaminsajid.coronaupdate.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.imtiazaminsajid.coronaupdate.Adapter.DistrictDetailsAdapter;
import com.imtiazaminsajid.coronaupdate.Model.BangladeshAllDataModel;
import com.imtiazaminsajid.coronaupdate.R;
import com.imtiazaminsajid.coronaupdate.api_interface.ApiDataInterface;
import com.imtiazaminsajid.coronaupdate.databinding.FragmentBangladeshDetailBinding;
import com.imtiazaminsajid.coronaupdate.utils.ApiClient;
import com.imtiazaminsajid.coronaupdate.utils.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BangladeshDetailFragment extends Fragment {

    static BangladeshDetailFragment instance;

    FragmentBangladeshDetailBinding fragmentBangladeshDetailBinding;

    RecyclerView.LayoutManager layoutManager;
    DistrictDetailsAdapter districtDetailsAdapter;

    private Retrofit retrofit;

    BangladeshAllDataModel bangladeshAllDataModel;

    View view;

    public BangladeshDetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentBangladeshDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bangladesh_detail, container, false);
        view = fragmentBangladeshDetailBinding.getRoot();


        getDataFromApi();

        return view;
    }

    public static BangladeshDetailFragment getInstance() {
        if (instance == null)
            instance = new BangladeshDetailFragment();
        return instance;
    }

    public void setAdapter() {
        layoutManager = new LinearLayoutManager(getContext());
        districtDetailsAdapter = new DistrictDetailsAdapter(getContext(), bangladeshAllDataModel);
        fragmentBangladeshDetailBinding.destrictDetailsRecycleView.setLayoutManager(layoutManager);
        fragmentBangladeshDetailBinding.destrictDetailsRecycleView.setAdapter(districtDetailsAdapter);
    }

    public void setOtherData() {
        fragmentBangladeshDetailBinding.totalConfirmed.setText("" + bangladeshAllDataModel.getTotal().getConfirmed());
        fragmentBangladeshDetailBinding.totalRecovered.setText("" + bangladeshAllDataModel.getTotal().getRecovered());
        fragmentBangladeshDetailBinding.totalDeaths.setText("" + bangladeshAllDataModel.getTotal().getDeaths());

        fragmentBangladeshDetailBinding.todayConfirmed.setText("" + bangladeshAllDataModel.getToday().getTodayConfirmed());
        fragmentBangladeshDetailBinding.todayDeaths.setText("" + bangladeshAllDataModel.getToday().getTodayDeaths());
        fragmentBangladeshDetailBinding.todayRecovered.setText("" + bangladeshAllDataModel.getToday().getTodayRecovered());


//        DateFormat format = new SimpleDateFormat("MMMM d, yyyy h:mm a");
//        Date date = new Date();
//        try {
//            date = format.parse(bangladeshAllDataModel.getLastUpdate());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        fragmentBangladeshDetailBinding.lastUpdatedData.setText("Last updated data: " + bangladeshAllDataModel.getLastUpdate());


//        try {
//            fragmentBangladeshDetailBinding.lastUpdatedData.setText("Last updated data: "+format.parse(bangladeshAllDataModel.getLastUpdate()));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


    }

    private void getDataFromApi() {

        if (Utils.checkInternetConnection(getContext())) {


            retrofit = ApiClient.getRetrofitInstance();
            ApiDataInterface apiDataInterface = retrofit.create(ApiDataInterface.class);
            Call call = apiDataInterface.getAllBangladeshCoronaData();
            call.enqueue(new Callback<BangladeshAllDataModel>() {
                @Override
                public void onResponse(Call<BangladeshAllDataModel> call, Response<BangladeshAllDataModel> response) {

                    Log.d("api", "code " + response.code());

                    if (response.isSuccessful()) {
                        fragmentBangladeshDetailBinding.destrictDetailsProgressBar.setVisibility(View.GONE);
                        bangladeshAllDataModel = new BangladeshAllDataModel();
                        bangladeshAllDataModel = response.body();
                        setAdapter();
                        setOtherData();
                    }
                }

                @Override
                public void onFailure(Call<BangladeshAllDataModel> call, Throwable t) {
                    fragmentBangladeshDetailBinding.destrictDetailsProgressBar.setVisibility(View.VISIBLE);
                }
            });

        } else {
            alaerDialog();
        }

    }

    public void alaerDialog() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getContext());
        builder.setMessage("R.string.dialog_messag").setTitle("R.string.dialog_title");

        //Setting message manually and performing action on button click
        builder.setMessage("Turn on the Internet to get live data!")
                .setCancelable(false)
                .setPositiveButton("Reload Data", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getDataFromApi();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle("Check Internet Connection");
        alert.show();
    }
}

