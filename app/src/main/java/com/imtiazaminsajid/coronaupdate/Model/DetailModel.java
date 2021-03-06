package com.imtiazaminsajid.coronaupdate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailModel {
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
    @SerializedName("created_at")
    @Expose
    private String createdAt;

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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
