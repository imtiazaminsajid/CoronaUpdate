package com.imtiazaminsajid.coronaupdate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Last7DaysModel {
    @SerializedName("confirmed")
    @Expose
    private ConfirmedModel confirmed;
    @SerializedName("recovered")
    @Expose
    private RecoveredModel recovered;
    @SerializedName("deaths")
    @Expose
    private DeathsModel deaths;

    public ConfirmedModel getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(ConfirmedModel confirmed) {
        this.confirmed = confirmed;
    }

    public RecoveredModel getRecovered() {
        return recovered;
    }

    public void setRecovered(RecoveredModel recovered) {
        this.recovered = recovered;
    }

    public DeathsModel getDeaths() {
        return deaths;
    }

    public void setDeaths(DeathsModel deaths) {
        this.deaths = deaths;
    }
}
