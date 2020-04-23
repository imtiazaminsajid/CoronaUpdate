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

                        if (bangladeshAllDataModel!=null) {
                            setAllData();

                            confirmedPieChart();
                            recoveredPieChart();
                            deathsPieChart();
                            agesPieChart();
                            gendersPieChart();
                        }
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

        fragmentLastSevenDaysBinding.confirmedTodayConfirmed.setText(""+bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-1).getConfirmed());
        fragmentLastSevenDaysBinding.confirmedYesterday.setText(""+bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-2).getConfirmed());
        fragmentLastSevenDaysBinding.confirmed2daysAgo.setText(""+bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-3).getConfirmed());
        fragmentLastSevenDaysBinding.confirmed3daysAgo.setText(""+bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-4).getConfirmed());
        fragmentLastSevenDaysBinding.confirmed4daysAgo.setText(""+bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-5).getConfirmed());
        fragmentLastSevenDaysBinding.confirmed5daysAgo.setText(""+bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-6).getConfirmed());
        fragmentLastSevenDaysBinding.confirmed6daysAgo.setText(""+bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-7).getConfirmed());

        fragmentLastSevenDaysBinding.deathsTodayConfirmed.setText(""+Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-1).getDeaths()));
        fragmentLastSevenDaysBinding.deathsYesterday.setText(""+Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-2).getDeaths()));
        fragmentLastSevenDaysBinding.deaths2daysAgo.setText(""+Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-3).getDeaths()));
        fragmentLastSevenDaysBinding.deaths3daysAgo.setText(""+Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-4).getDeaths()));
        fragmentLastSevenDaysBinding.deaths4daysAgo.setText(""+Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-5).getDeaths()));
        fragmentLastSevenDaysBinding.deaths5daysAgo.setText(""+Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-6).getDeaths()));
        fragmentLastSevenDaysBinding.deaths6daysAgo.setText(""+Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-7).getDeaths()));

        fragmentLastSevenDaysBinding.recoveredTodayConfirmed.setText(""+Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-1).getRecovered()));
        fragmentLastSevenDaysBinding.recoveredYesterday.setText(""+Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-2).getRecovered()));
        fragmentLastSevenDaysBinding.recovered2daysAgo.setText(""+Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-3).getRecovered()));
        fragmentLastSevenDaysBinding.recovered3daysAgo.setText(""+Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-4).getRecovered()));
        fragmentLastSevenDaysBinding.recovered4daysAgo.setText(""+Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-5).getRecovered()));
        fragmentLastSevenDaysBinding.recovered5daysAgo.setText(""+Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-6).getRecovered()));
        fragmentLastSevenDaysBinding.recovered6daysAgo.setText(""+Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-7).getRecovered()));

        fragmentLastSevenDaysBinding.onetoten.setText(""+bangladeshAllDataModel.getAges().getOnetoten().getConfirmed());
        fragmentLastSevenDaysBinding.tentotwenty.setText(""+bangladeshAllDataModel.getAges().getEleventotwenty().getConfirmed());
        fragmentLastSevenDaysBinding.twentytothirty.setText(""+bangladeshAllDataModel.getAges().getTwentyonetothirty().getConfirmed());
        fragmentLastSevenDaysBinding.thirtytoforty.setText(""+bangladeshAllDataModel.getAges().getThirtyonetofourty().getConfirmed());
        fragmentLastSevenDaysBinding.fortytofifty.setText(""+bangladeshAllDataModel.getAges().getFourtyonetofifty().getConfirmed());
        fragmentLastSevenDaysBinding.fiftytosixty.setText(""+bangladeshAllDataModel.getAges().getFiftyonetosixty().getConfirmed());
        fragmentLastSevenDaysBinding.sixtyplus.setText(""+bangladeshAllDataModel.getAges().getSixtyplus().getConfirmed());


        fragmentLastSevenDaysBinding.maleConfirmed.setText(""+(Integer.valueOf(bangladeshAllDataModel.getGenders().getMale().getConfirmed())*bangladeshAllDataModel.getTotal().getConfirmed())/100);
        fragmentLastSevenDaysBinding.femaleConfirmed.setText(""+(Integer.valueOf(bangladeshAllDataModel.getGenders().getFemale().getConfirmed())*bangladeshAllDataModel.getTotal().getConfirmed())/100);






//        fragmentLastSevenDaysBinding.recoveredTodayConfirmed.setText(""+bangladeshAllDataModel.getLast7Days().getRecovered().getToday());
//        fragmentLastSevenDaysBinding.recoveredYesterday.setText(""+bangladeshAllDataModel.getLast7Days().getRecovered().getYesterday());
//        fragmentLastSevenDaysBinding.recovered2daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getRecovered().get_2daysago());
//        fragmentLastSevenDaysBinding.recovered3daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getRecovered().get_3daysago());
//        fragmentLastSevenDaysBinding.recovered4daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getRecovered().get_4daysago());
//        fragmentLastSevenDaysBinding.recovered5daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getRecovered().get_5daysago());
//        fragmentLastSevenDaysBinding.recovered6daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getRecovered().get_6daysago());

//        fragmentLastSevenDaysBinding.deathsTodayConfirmed.setText(""+bangladeshAllDataModel.getLast7Days().getDeaths().getToday());
//        fragmentLastSevenDaysBinding.deathsYesterday.setText(""+bangladeshAllDataModel.getLast7Days().getDeaths().getYesterday());
//        fragmentLastSevenDaysBinding.deaths2daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getDeaths().get_2daysago());
//        fragmentLastSevenDaysBinding.deaths3daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getDeaths().get_3daysago());
//        fragmentLastSevenDaysBinding.deaths4daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getDeaths().get_4daysago());
//        fragmentLastSevenDaysBinding.deaths5daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getDeaths().get_5daysago());
//        fragmentLastSevenDaysBinding.deaths6daysAgo.setText(""+bangladeshAllDataModel.getLast7Days().getDeaths().get_6daysago());




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
        data.add(new ValueDataEntry("1-10", Integer.valueOf(bangladeshAllDataModel.getAges().getOnetoten().getConfirmed())));
        data.add(new ValueDataEntry("11-20", Integer.valueOf(bangladeshAllDataModel.getAges().getEleventotwenty().getConfirmed())));
        data.add(new ValueDataEntry("21-30", Integer.valueOf(bangladeshAllDataModel.getAges().getTwentyonetothirty().getConfirmed())));
        data.add(new ValueDataEntry("31-40", Integer.valueOf(bangladeshAllDataModel.getAges().getThirtyonetofourty().getConfirmed())));
        data.add(new ValueDataEntry("41-50", Integer.valueOf(bangladeshAllDataModel.getAges().getFourtyonetofifty().getConfirmed())));
        data.add(new ValueDataEntry("51-60", Integer.valueOf(bangladeshAllDataModel.getAges().getFiftyonetosixty().getConfirmed())));
        data.add(new ValueDataEntry("60+", Integer.valueOf(bangladeshAllDataModel.getAges().getSixtyplus().getConfirmed())));

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
        confirmedData.add(new ValueDataEntry("Today", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-1).getConfirmed())));
        confirmedData.add(new ValueDataEntry("Yesterday", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-2).getConfirmed())));
        confirmedData.add(new ValueDataEntry("2 Days Ago", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-3).getConfirmed())));
        confirmedData.add(new ValueDataEntry("3 Days Ago", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-4).getConfirmed())));
        confirmedData.add(new ValueDataEntry("4 Days Ago", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-5).getConfirmed())));
        confirmedData.add(new ValueDataEntry("5 Days Ago", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-6).getConfirmed())));
        confirmedData.add(new ValueDataEntry("6 Days Ago", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-7).getConfirmed())));

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

        deathsData.add(new ValueDataEntry("Today", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-1).getDeaths())));
        deathsData.add(new ValueDataEntry("Yesterday", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-2).getDeaths())));
        deathsData.add(new ValueDataEntry("2 Days Ago", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-3).getDeaths())));
        deathsData.add(new ValueDataEntry("3 Days Ago", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-4).getDeaths())));
        deathsData.add(new ValueDataEntry("4 Days Ago", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-5).getDeaths())));
        deathsData.add(new ValueDataEntry("5 Days Ago", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-6).getDeaths())));
        deathsData.add(new ValueDataEntry("6 Days Ago", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-7).getDeaths())));


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

        recoveredData.add(new ValueDataEntry("Today", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-1).getRecovered())));
        recoveredData.add(new ValueDataEntry("Yesterday", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-2).getRecovered())));
        recoveredData.add(new ValueDataEntry("2 Days Ago", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-3).getRecovered())));
        recoveredData.add(new ValueDataEntry("3 Days Ago", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-4).getRecovered())));
        recoveredData.add(new ValueDataEntry("4 Days Ago", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-5).getRecovered())));
        recoveredData.add(new ValueDataEntry("5 Days Ago", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-6).getRecovered())));
        recoveredData.add(new ValueDataEntry("6 Days Ago", Integer.valueOf(bangladeshAllDataModel.getDetails().get(bangladeshAllDataModel.getDetails().size()-7).getRecovered())));

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

    public void gendersPieChart(){
        fragmentLastSevenDaysBinding.gendersAnyChartView.setProgressBar(fragmentLastSevenDaysBinding.gendersAnyChartViewProgressBar);

        APIlib.getInstance().setActiveAnyChartView(fragmentLastSevenDaysBinding.gendersAnyChartView);

        Pie gendersPie = AnyChart.pie();

//        recoveredPie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
//            @Override
//            public void onClick(Event event) {
//                Toast.makeText(getContext(), event.getData().get("x") + " Recovered " + event.getData().get("value"), Toast.LENGTH_SHORT).show();
//            }
//        });

        List<DataEntry> gendersData = new ArrayList<>();

        gendersData.add(new ValueDataEntry("Male", Integer.valueOf(bangladeshAllDataModel.getGenders().getMale().getConfirmed())));
        gendersData.add(new ValueDataEntry("Female", Integer.valueOf(bangladeshAllDataModel.getGenders().getFemale().getConfirmed())));

        gendersPie.data(gendersData);

        gendersPie.labels().position("outside");

        gendersPie.legend().title().enabled(true);
        gendersPie.legend().title()
                .text("Gender")
                .padding(0d, 0d, 10d, 0d);

        gendersPie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        fragmentLastSevenDaysBinding.gendersAnyChartView.setChart(gendersPie);
    }
}
