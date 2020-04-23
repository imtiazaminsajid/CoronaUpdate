package com.imtiazaminsajid.coronaupdate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodayModel {
    @SerializedName("todayConfirmed")
    @Expose
    private Integer todayConfirmed;
    @SerializedName("todayRecovered")
    @Expose
    private Integer todayRecovered;
    @SerializedName("todayDeaths")
    @Expose
    private Integer todayDeaths;
    @SerializedName("todayTested")
    @Expose
    private Integer todayTested;

    public Integer getTodayTested() {
        return todayTested;
    }

    public void setTodayTested(Integer todayTested) {
        this.todayTested = todayTested;
    }

    public Integer getTodayConfirmed() {
        return todayConfirmed;
    }

    public void setTodayConfirmed(Integer todayConfirmed) {
        this.todayConfirmed = todayConfirmed;
    }

    public Integer getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(Integer todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public Integer getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(Integer todayDeaths) {
        this.todayDeaths = todayDeaths;
    }
}
