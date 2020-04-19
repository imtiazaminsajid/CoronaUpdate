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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SearchView;
import android.widget.Toast;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.NameValueDataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.charts.Venn;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.imtiazaminsajid.coronaupdate.Adapter.DistrictDetailsAdapter;
import com.imtiazaminsajid.coronaupdate.Model.BangladeshAllDataModel;
import com.imtiazaminsajid.coronaupdate.Model.DistrictModel;
import com.imtiazaminsajid.coronaupdate.R;
import com.imtiazaminsajid.coronaupdate.api_interface.ApiDataInterface;
import com.imtiazaminsajid.coronaupdate.databinding.FragmentBangladeshDetailBinding;
import com.imtiazaminsajid.coronaupdate.utils.ApiClient;
import com.imtiazaminsajid.coronaupdate.utils.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

        fragmentBangladeshDetailBinding.searchViewDistrict.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterData(query,bangladeshAllDataModel.getDistricts());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterData(newText,bangladeshAllDataModel.getDistricts());
                return false;
            }
        });

        return view;
    }

    public static BangladeshDetailFragment getInstance() {
        if (instance == null)
            instance = new BangladeshDetailFragment();
        return instance;
    }

    public void setAdapter() {
        layoutManager = new LinearLayoutManager(getContext());
        districtDetailsAdapter = new DistrictDetailsAdapter(getContext(), bangladeshAllDataModel.getDistricts());
        fragmentBangladeshDetailBinding.destrictDetailsRecycleView.setLayoutManager(layoutManager);
        fragmentBangladeshDetailBinding.destrictDetailsRecycleView.setAdapter(districtDetailsAdapter);
        fragmentBangladeshDetailBinding.destrictDetailsRecycleView.setNestedScrollingEnabled(false);
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

        Animation animation  = AnimationUtils.loadAnimation(getContext(), R.anim.blink_anim);
        fragmentBangladeshDetailBinding.lastUpdatedData.startAnimation(animation);

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
//                        fragmentBangladeshDetailBinding.destrictDetailsProgressBar.setVisibility(View.GONE);
                        bangladeshAllDataModel = new BangladeshAllDataModel();
                        bangladeshAllDataModel = response.body();
                        setAdapter();
                        agesPieChart();
//                        agesVennChart();
                        setOtherData();
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

    public void agesPieChart(){
        fragmentBangladeshDetailBinding.totalBdAnyChartView.setProgressBar(fragmentBangladeshDetailBinding.totalBdAnyChartViewProgressBar);
        APIlib.getInstance().setActiveAnyChartView(fragmentBangladeshDetailBinding.totalBdAnyChartView);
        Pie agesPie = AnyChart.pie();


//        agesPie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
//            @Override
//            public void onClick(Event event) {
//                Toast.makeText(getContext(), "Age "+event.getData().get("x") + " : Effected " + event.getData().get("value"), Toast.LENGTH_SHORT).show();
//            }
//        });

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Total Confirmed", bangladeshAllDataModel.getTotal().getConfirmed()));
        data.add(new ValueDataEntry("Total Deaths", bangladeshAllDataModel.getTotal().getDeaths()));
        data.add(new ValueDataEntry("Total Recovered",  bangladeshAllDataModel.getTotal().getRecovered()));

        agesPie.data(data);

        agesPie.labels().position("outside");

        agesPie.legend().title().enabled(true);
        agesPie.legend().title()
                .text("Total Pie Chart")
                .padding(0d, 0d, 10d, 0d);

        agesPie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        fragmentBangladeshDetailBinding.totalBdAnyChartView.setChart(agesPie);
    }


    public void agesVennChart(){
        fragmentBangladeshDetailBinding.totalBdAnyChartView.setProgressBar(fragmentBangladeshDetailBinding.totalBdAnyChartViewProgressBar);
        APIlib.getInstance().setActiveAnyChartView(fragmentBangladeshDetailBinding.totalBdAnyChartView);

        Venn venn = AnyChart.venn();

        List<DataEntry> data = new ArrayList<>();
        data.add(new NameValueDataEntry("A", "Total Confirmed", bangladeshAllDataModel.getTotal().getConfirmed()));
        data.add(new NameValueDataEntry("B", "Recovered",  bangladeshAllDataModel.getTotal().getRecovered()));
        data.add(new NameValueDataEntry("C", "Deaths",  bangladeshAllDataModel.getTotal().getRecovered()));
        data.add(new NameValueDataEntry("A&B", "Total Recovered",  bangladeshAllDataModel.getTotal().getRecovered()));
        data.add(new NameValueDataEntry("A&B&C", "Total Deaths", bangladeshAllDataModel.getTotal().getDeaths()));

        venn.data(data);

        venn.stroke("2 #FFFFFF");

        venn.labels().format("{%Name}");

        venn.intersections().hovered().fill("black", 0.25d);

        venn.intersections().labels().fontWeight("bold");
        venn.intersections().labels().format("{%Name}");

        venn.tooltip().titleFormat("{%Name}");


        fragmentBangladeshDetailBinding.totalBdAnyChartView.setChart(venn);
    }


    private void filterData(String query, List<DistrictModel> models) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<DistrictModel> filteredModelList = new ArrayList<>();
        for (DistrictModel model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        districtDetailsAdapter.setDistrictModels(filteredModelList);
    }
}

