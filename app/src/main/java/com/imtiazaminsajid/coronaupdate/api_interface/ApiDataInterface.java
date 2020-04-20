package com.imtiazaminsajid.coronaupdate.api_interface;

import com.imtiazaminsajid.coronaupdate.Model.BangladeshAllDataModel;
import com.imtiazaminsajid.coronaupdate.NewModel.AllCountryData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiDataInterface {
    @GET("api")
    Call<BangladeshAllDataModel> getAllBangladeshCoronaData();

    @GET("countries/")
    Call<List<AllCountryData>> getAllCountryData();
}
