package com.imtiazaminsajid.coronaupdate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BangladeshAllDataModel {

    @SerializedName("total")
    @Expose
    private TotalModel total;
    @SerializedName("today")
    @Expose
    private TodayModel today;
    @SerializedName("new")
    @Expose
    private NewModel _new;
    @SerializedName("lastUpdate")
    @Expose
    private String lastUpdate;
    @SerializedName("details")
    @Expose
    private List<DetailModel> details;
    @SerializedName("genders")
    @Expose
    private GendersModel genders;
    @SerializedName("ages")
    @Expose
    private AgesModel ages;
    @SerializedName("districts")
    @Expose
    private List<DistrictModel> districts;

    public TotalModel getTotal() {
        return total;
    }

    public void setTotal(TotalModel total) {
        this.total = total;
    }

    public NewModel get_new() {
        return _new;
    }

    public void set_new(NewModel _new) {
        this._new = _new;
    }

    public TodayModel getToday() {
        return today;
    }

    public void setToday(TodayModel today) {
        this.today = today;
    }

    public List<DetailModel> getDetails() {
        return details;
    }

    public void setDetails(List<DetailModel> details) {
        this.details = details;
    }

    public GendersModel getGenders() {
        return genders;
    }

    public void setGenders(GendersModel genders) {
        this.genders = genders;
    }

    public AgesModel getAges() {
        return ages;
    }

    public void setAges(AgesModel ages) {
        this.ages = ages;
    }

    public List<DistrictModel> getDistricts() {
        return districts;
    }

    public void setDistricts(List<DistrictModel> districts) {
        this.districts = districts;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
