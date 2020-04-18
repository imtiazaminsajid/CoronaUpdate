package com.imtiazaminsajid.coronaupdate.api_interface;

import com.imtiazaminsajid.coronaupdate.Model.BangladeshAllDataModel;
import com.imtiazaminsajid.coronaupdate.Model.WorldAllCountryModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WorldCountryDataInterface {
    @GET("?fbclid=IwAR3jmOqIdvCAmAEJw1rCk-uUNQ4d0B9XpENjZXSKxXE-fmZciQwKXkcGNrg")
    Call<WorldAllCountryModel> getAllWorldCountryCoronaData();
}
