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
import com.imtiazaminsajid.coronaupdate.Fragments.TotalWorldUpdateFragment;
import com.imtiazaminsajid.coronaupdate.Model.BangladeshAllDataModel;
import com.imtiazaminsajid.coronaupdate.api_interface.ApiDataInterface;
import com.imtiazaminsajid.coronaupdate.databinding.ActivityMainBinding;
import com.imtiazaminsajid.coronaupdate.utils.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    FragmentManager fm;

    private Retrofit retrofit;

    BangladeshAllDataModel bangladeshAllDataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        intializeToolbar();
        setAdapter();

        getDataFromApi();
    }

    public void setAdapter(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        viewPagerAdapter.addFragment(BangladeshDetailFragment.getInstance(), "Bangladesh");
        viewPagerAdapter.addFragment(TotalWorldUpdateFragment.getInstance(), "Worldwide");

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
}
