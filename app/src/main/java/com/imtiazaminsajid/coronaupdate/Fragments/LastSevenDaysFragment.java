package com.imtiazaminsajid.coronaupdate.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imtiazaminsajid.coronaupdate.Model.BangladeshAllDataModel;
import com.imtiazaminsajid.coronaupdate.R;
import com.imtiazaminsajid.coronaupdate.api_interface.ApiDataInterface;
import com.imtiazaminsajid.coronaupdate.databinding.FragmentLastSevenDaysBinding;
import com.imtiazaminsajid.coronaupdate.utils.ApiClient;
import com.imtiazaminsajid.coronaupdate.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LastSevenDaysFragment extends Fragment {

    static LastSevenDaysFragment instance;

    FragmentLastSevenDaysBinding fragmentLastSevenDaysBinding;

    BangladeshAllDataModel bangladeshAllDataModel;

    View view;

    private Retrofit retrofit;

    public LastSevenDaysFragment() {
    }

    public static LastSevenDaysFragment getInstance() {
        if (instance == null)
            instance = new LastSevenDaysFragment();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentLastSevenDaysBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_last_seven_days, container, false);
        view = fragmentLastSevenDaysBinding.getRoot();

        getDataFromApi();

        return view;
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
//                        fragmentBangladeshDetailBinding.destrictDetailsProgressBar.setVisibility(View.GONE);
                        bangladeshAllDataModel = new BangladeshAllDataModel();
                        bangladeshAllDataModel = response.body();

                        setAllData();
                    }
                }

                @Override
                public void onFailure(Call<BangladeshAllDataModel> call, Throwable t) {
//                    fragmentBangladeshDetailBinding.destrictDetailsProgressBar.setVisibility(View.VISIBLE);
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

    private void setAllData(){
        fragmentLastSevenDaysBinding.confirmedTodayConfirmed.setText(""+bangladeshAllDataModel.getLast7Days().getConfirmed().getToday());
        fragmentLastSevenDaysBinding.confirmedYesterday.setText(""+bangladeshAllDataModel.getLast7Days().getConfirmed().getYesterday());
        fragmentLastSevenDaysBinding.confirmed2daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getConfirmed().get_2daysago());
        fragmentLastSevenDaysBinding.confirmed3daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getConfirmed().get_3daysago());
        fragmentLastSevenDaysBinding.confirmed4daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getConfirmed().get_4daysago());
        fragmentLastSevenDaysBinding.confirmed5daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getConfirmed().get_5daysago());
        fragmentLastSevenDaysBinding.confirmed6daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getConfirmed().get_6daysago());


        fragmentLastSevenDaysBinding.recoveredTodayConfirmed.setText(""+bangladeshAllDataModel.getLast7Days().getRecovered().getToday());
        fragmentLastSevenDaysBinding.recoveredYesterday.setText(""+bangladeshAllDataModel.getLast7Days().getRecovered().getYesterday());
        fragmentLastSevenDaysBinding.recovered2daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getRecovered().get_2daysago());
        fragmentLastSevenDaysBinding.recovered3daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getRecovered().get_3daysago());
        fragmentLastSevenDaysBinding.recovered4daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getRecovered().get_4daysago());
        fragmentLastSevenDaysBinding.recovered5daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getRecovered().get_5daysago());
        fragmentLastSevenDaysBinding.recovered6daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getRecovered().get_6daysago());

        fragmentLastSevenDaysBinding.deathsTodayConfirmed.setText(""+bangladeshAllDataModel.getLast7Days().getDeaths().getToday());
        fragmentLastSevenDaysBinding.deathsYesterday.setText(""+bangladeshAllDataModel.getLast7Days().getDeaths().getYesterday());
        fragmentLastSevenDaysBinding.deaths2daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getDeaths().get_2daysago());
        fragmentLastSevenDaysBinding.deaths3daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getDeaths().get_3daysago());
        fragmentLastSevenDaysBinding.deaths4daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getDeaths().get_4daysago());
        fragmentLastSevenDaysBinding.deaths5daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getDeaths().get_5daysago());
        fragmentLastSevenDaysBinding.deaths6daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getDeaths().get_6daysago());

        fragmentLastSevenDaysBinding.onetoten.setText(""+bangladeshAllDataModel.getAges().getOnetoten());
        fragmentLastSevenDaysBinding.tentotwenty.setText(""+bangladeshAllDataModel.getAges().getTentotwenty());
        fragmentLastSevenDaysBinding.twentytothirty.setText(""+bangladeshAllDataModel.getAges().getTwentytothirty());
        fragmentLastSevenDaysBinding.thirtytoforty.setText(""+bangladeshAllDataModel.getAges().getThirtytoforty());
        fragmentLastSevenDaysBinding.fortytofifty.setText(""+bangladeshAllDataModel.getAges().getFortytofifty());
        fragmentLastSevenDaysBinding.fiftytosixty.setText(""+bangladeshAllDataModel.getAges().getFiftytosixty());
        fragmentLastSevenDaysBinding.sixtyplus.setText(""+bangladeshAllDataModel.getAges().getSixtyplus());
    }
}
