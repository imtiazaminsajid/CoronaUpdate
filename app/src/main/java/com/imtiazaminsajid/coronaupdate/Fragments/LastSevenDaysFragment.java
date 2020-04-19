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
import android.widget.Toast;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.imtiazaminsajid.coronaupdate.Model.BangladeshAllDataModel;
import com.imtiazaminsajid.coronaupdate.R;
import com.imtiazaminsajid.coronaupdate.api_interface.ApiDataInterface;
import com.imtiazaminsajid.coronaupdate.databinding.FragmentLastSevenDaysBinding;
import com.imtiazaminsajid.coronaupdate.utils.ApiClient;
import com.imtiazaminsajid.coronaupdate.utils.Utils;

import java.util.ArrayList;
import java.util.List;

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

                    Log.d("api", "code last 7 days" + response.code());

                    if (response.isSuccessful()) {
//                        fragmentBangladeshDetailBinding.destrictDetailsProgressBar.setVisibility(View.GONE);
                        bangladeshAllDataModel = new BangladeshAllDataModel();
                        bangladeshAllDataModel = response.body();

                        agesPieChart();
                        confirmedPieChart();
                        recoveredPieChart();
                        deathsPieChart();

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


    public void agesPieChart(){
        fragmentLastSevenDaysBinding.ageAnyChartView.setProgressBar(fragmentLastSevenDaysBinding.agesAnyChartViewProgressBar);
        APIlib.getInstance().setActiveAnyChartView(fragmentLastSevenDaysBinding.ageAnyChartView);
        Pie agesPie = AnyChart.pie();


//        agesPie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
//            @Override
//            public void onClick(Event event) {
//                Toast.makeText(getContext(), "Age "+event.getData().get("x") + " : Effected " + event.getData().get("value"), Toast.LENGTH_SHORT).show();
//            }
//        });

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("1-10", Integer.valueOf(bangladeshAllDataModel.getAges().getOnetoten())));
        data.add(new ValueDataEntry("11-20", Integer.valueOf(bangladeshAllDataModel.getAges().getTentotwenty())));
        data.add(new ValueDataEntry("21-30", Integer.valueOf(bangladeshAllDataModel.getAges().getTwentytothirty())));
        data.add(new ValueDataEntry("31-40", Integer.valueOf(bangladeshAllDataModel.getAges().getThirtytoforty())));
        data.add(new ValueDataEntry("41-50", Integer.valueOf(bangladeshAllDataModel.getAges().getFortytofifty())));
        data.add(new ValueDataEntry("51-60", Integer.valueOf(bangladeshAllDataModel.getAges().getFiftytosixty())));
        data.add(new ValueDataEntry("60+", Integer.valueOf(bangladeshAllDataModel.getAges().getSixtyplus())));

        agesPie.data(data);

        agesPie.labels().position("outside");

        agesPie.legend().title().enabled(true);
        agesPie.legend().title()
                .text("Ages")
                .padding(0d, 0d, 10d, 0d);

        agesPie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        fragmentLastSevenDaysBinding.ageAnyChartView.setChart(agesPie);
    }

    public void confirmedPieChart(){
        fragmentLastSevenDaysBinding.confirmedAnyChartView.setProgressBar(fragmentLastSevenDaysBinding.confirmedAnyChartViewProgressBar);

        APIlib.getInstance().setActiveAnyChartView(fragmentLastSevenDaysBinding.confirmedAnyChartView);

        Pie confirmedPie = AnyChart.pie();

//        confirmedPie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
//            @Override
//            public void onClick(Event event) {
//                Toast.makeText(getContext(), event.getData().get("x") + " Confirmed " + event.getData().get("value"), Toast.LENGTH_SHORT).show();
//            }
//        });

        List<DataEntry> confirmedData = new ArrayList<>();
        confirmedData.add(new ValueDataEntry("Today", bangladeshAllDataModel.getLast7Days().getConfirmed().getToday()));
        confirmedData.add(new ValueDataEntry("Yesterday", bangladeshAllDataModel.getLast7Days().getConfirmed().getYesterday()));
        confirmedData.add(new ValueDataEntry("2 Days Ago", bangladeshAllDataModel.getLast7Days().getConfirmed().get_2daysago()));
        confirmedData.add(new ValueDataEntry("3 Days Ago", bangladeshAllDataModel.getLast7Days().getConfirmed().get_3daysago()));
        confirmedData.add(new ValueDataEntry("4 Days Ago", bangladeshAllDataModel.getLast7Days().getConfirmed().get_4daysago()));
        confirmedData.add(new ValueDataEntry("5 Days Ago", bangladeshAllDataModel.getLast7Days().getConfirmed().get_5daysago()));
        confirmedData.add(new ValueDataEntry("6 Days Ago", bangladeshAllDataModel.getLast7Days().getConfirmed().get_6daysago()));

        confirmedPie.data(confirmedData);

        confirmedPie.labels().position("outside");

        confirmedPie.legend().title().enabled(true);
        confirmedPie.legend().title()
                .text("Days")
                .padding(0d, 0d, 10d, 0d);

        confirmedPie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        fragmentLastSevenDaysBinding.confirmedAnyChartView.setChart(confirmedPie);
    }

    public void deathsPieChart(){
        fragmentLastSevenDaysBinding.deathsAnyChartView.setProgressBar(fragmentLastSevenDaysBinding.deathsAnyChartViewProgressBar);

        APIlib.getInstance().setActiveAnyChartView(fragmentLastSevenDaysBinding.deathsAnyChartView);

        Pie deathsPie = AnyChart.pie();

//        deathsPie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
//            @Override
//            public void onClick(Event event) {
//                Toast.makeText(getContext(), event.getData().get("x") + " Deaths " + event.getData().get("value"), Toast.LENGTH_SHORT).show();
//            }
//        });

        List<DataEntry> deathsData = new ArrayList<>();
        deathsData.add(new ValueDataEntry("Today", bangladeshAllDataModel.getLast7Days().getDeaths().getToday()));
        deathsData.add(new ValueDataEntry("Yesterday", bangladeshAllDataModel.getLast7Days().getDeaths().getYesterday()));
        deathsData.add(new ValueDataEntry("2 Days Ago", bangladeshAllDataModel.getLast7Days().getDeaths().get_2daysago()));
        deathsData.add(new ValueDataEntry("3 Days Ago", bangladeshAllDataModel.getLast7Days().getDeaths().get_3daysago()));
        deathsData.add(new ValueDataEntry("4 Days Ago", bangladeshAllDataModel.getLast7Days().getDeaths().get_4daysago()));
        deathsData.add(new ValueDataEntry("5 Days Ago", bangladeshAllDataModel.getLast7Days().getDeaths().get_5daysago()));
        deathsData.add(new ValueDataEntry("6 Days Ago", bangladeshAllDataModel.getLast7Days().getDeaths().get_6daysago()));

        deathsPie.data(deathsData);

        deathsPie.labels().position("outside");

        deathsPie.legend().title().enabled(true);
        deathsPie.legend().title()
                .text("Days")
                .padding(0d, 0d, 10d, 0d);

        deathsPie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        fragmentLastSevenDaysBinding.deathsAnyChartView.setChart(deathsPie);
    }

    public void recoveredPieChart(){
        fragmentLastSevenDaysBinding.recoveredAnyChartView.setProgressBar(fragmentLastSevenDaysBinding.recoveredAnyChartViewProgressBar);

        APIlib.getInstance().setActiveAnyChartView(fragmentLastSevenDaysBinding.recoveredAnyChartView);

        Pie recoveredPie = AnyChart.pie();

//        recoveredPie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
//            @Override
//            public void onClick(Event event) {
//                Toast.makeText(getContext(), event.getData().get("x") + " Recovered " + event.getData().get("value"), Toast.LENGTH_SHORT).show();
//            }
//        });

        List<DataEntry> recoveredData = new ArrayList<>();
        recoveredData.add(new ValueDataEntry("Today", bangladeshAllDataModel.getLast7Days().getRecovered().getToday()));
        recoveredData.add(new ValueDataEntry("Yesterday", bangladeshAllDataModel.getLast7Days().getRecovered().getYesterday()));
        recoveredData.add(new ValueDataEntry("2 Days Ago", bangladeshAllDataModel.getLast7Days().getRecovered().get_2daysago()));
        recoveredData.add(new ValueDataEntry("3 Days Ago", bangladeshAllDataModel.getLast7Days().getRecovered().get_3daysago()));
        recoveredData.add(new ValueDataEntry("4 Days Ago", bangladeshAllDataModel.getLast7Days().getRecovered().get_4daysago()));
        recoveredData.add(new ValueDataEntry("5 Days Ago", bangladeshAllDataModel.getLast7Days().getRecovered().get_5daysago()));
        recoveredData.add(new ValueDataEntry("6 Days Ago", bangladeshAllDataModel.getLast7Days().getRecovered().get_6daysago()));

        recoveredPie.data(recoveredData);

        recoveredPie.labels().position("outside");

        recoveredPie.legend().title().enabled(true);
        recoveredPie.legend().title()
                .text("Days")
                .padding(0d, 0d, 10d, 0d);

        recoveredPie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        fragmentLastSevenDaysBinding.recoveredAnyChartView.setChart(recoveredPie);
    }
}
