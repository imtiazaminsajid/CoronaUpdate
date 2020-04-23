package com.imtiazaminsajid.coronaupdate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DistrictModel {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("bnname")
    @Expose
    private String bnname;
    @SerializedName("tested")
    @Expose
    private String tested;
    @SerializedName("confirmed")
    @Expose
    private String confirmed;
    @SerializedName("recovered")
    @Expose
    private String recovered;
    @SerializedName("deaths")
    @Expose
    private String deaths;

    public String getBnname() {
        return bnname;
    }

    public void setBnname(String bnname) {
        this.bnname = bnname;
    }

    public String getTested() {
        return tested;
    }

    public void setTested(String tested) {
        this.tested = tested;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }
}
