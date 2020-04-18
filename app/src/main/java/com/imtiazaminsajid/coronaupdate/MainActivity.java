package com.imtiazaminsajid.coronaupdate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.tabs.TabLayout;
import com.imtiazaminsajid.coronaupdate.Adapter.ViewPagerAdapter;
import com.imtiazaminsajid.coronaupdate.Fragments.BangladeshDetailFragment;
import com.imtiazaminsajid.coronaupdate.Fragments.CoronaHotLineFragment;
import com.imtiazaminsajid.coronaupdate.Fragments.LastSevenDaysFragment;
import com.imtiazaminsajid.coronaupdate.Fragments.PreventCoronavirusFragment;
import com.imtiazaminsajid.coronaupdate.Fragments.TotalWorldUpdateFragment;
import com.imtiazaminsajid.coronaupdate.Model.BangladeshAllDataModel;
import com.imtiazaminsajid.coronaupdate.Model.WorldAllCountryModel;
import com.imtiazaminsajid.coronaupdate.api_interface.ApiDataInterface;
import com.imtiazaminsajid.coronaupdate.api_interface.WorldCountryDataInterface;
import com.imtiazaminsajid.coronaupdate.databinding.ActivityMainBinding;
import com.imtiazaminsajid.coronaupdate.utils.ApiClient;
import com.imtiazaminsajid.coronaupdate.utils.ApiClintForWorldCountry;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    FragmentManager fm;

    private Retrofit retrofit;

    BangladeshAllDataModel bangladeshAllDataModel;

    WorldAllCountryModel worldAllCountryModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        intializeToolbar();
        setAdapter();

//        getWorldCountryDataFromApi();
    }

    public void setAdapter(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        viewPagerAdapter.addFragment(BangladeshDetailFragment.getInstance(), "Bangladesh");
        viewPagerAdapter.addFragment(LastSevenDaysFragment.getInstance(), "BD Statistics");
        viewPagerAdapter.addFragment(TotalWorldUpdateFragment.getInstance(), "Worldwide");
        viewPagerAdapter.addFragment(PreventCoronavirusFragment.getInstance(), "Prevent");
        viewPagerAdapter.addFragment(CoronaHotLineFragment.getInstance(), "Hotline");

        activityMainBinding.viewpager.setSaveFromParentEnabled(false);
        activityMainBinding.viewpager.setAdapter(viewPagerAdapter);
    }

    public void intializeToolbar() {

        ((TabLayout) activityMainBinding.toolbar.findViewById(R.id.tab_layout)).setupWithViewPager(activityMainBinding.viewpager);
        ((TabLayout) activityMainBinding.toolbar.findViewById(R.id.tab_layout)).addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                activityMainBinding.viewpager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void getDataFromApi(){

        retrofit =  ApiClient.getRetrofitInstance();
        ApiDataInterface apiDataInterface = retrofit.create(ApiDataInterface.class);
        Call call = apiDataInterface.getAllBangladeshCoronaData();
        call.enqueue(new Callback<BangladeshAllDataModel>() {
            @Override
            public void onResponse(Call<BangladeshAllDataModel> call, Response<BangladeshAllDataModel> response) {

                Log.d("api", "code "+response.code());

                if (response.isSuccessful()) {
                    bangladeshAllDataModel = new BangladeshAllDataModel();
                    bangladeshAllDataModel = response.body();
                }
            }

            @Override
            public void onFailure(Call<BangladeshAllDataModel> call, Throwable t) {

            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void getWorldCountryDataFromApi(){

        retrofit =  ApiClintForWorldCountry.getRetrofitInstance();
        WorldCountryDataInterface worldCountryDataInterface = retrofit.create(WorldCountryDataInterface.class);
        Call call = worldCountryDataInterface.getAllWorldCountryCoronaData();
        call.enqueue(new Callback<WorldAllCountryModel>() {
            @Override
            public void onResponse(Call<WorldAllCountryModel> call, Response<WorldAllCountryModel> response) {

                Log.d("apiworld", "code for world "+response.code());

                if (response.isSuccessful()) {
                    worldAllCountryModel = new WorldAllCountryModel();
                    worldAllCountryModel = response.body();
//                    setAdapter();
//                    setOtherData();
                }
            }

            @Override
            public void onFailure(Call<WorldAllCountryModel> call, Throwable t) {

                Log.d("apiworld", "code for world failed "+t.getMessage());

            }
        });

    }
}
