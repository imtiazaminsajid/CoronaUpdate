package com.imtiazaminsajid.coronaupdate.api_interface;

import com.imtiazaminsajid.coronaupdate.Model.BangladeshAllDataModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiDataInterface {
    @GET("api")
    Call<BangladeshAllDataModel> getAllBangladeshCoronaData();
}
