package com.imtiazaminsajid.coronaupdate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorldDatumModel {
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("total_cases")
    @Expose
    private String totalCases;
    @SerializedName("new_cases")
    @Expose
    private String newCases;
    @SerializedName("total_death")
    @Expose
    private String totalDeath;
    @SerializedName("new_death")
    @Expose
    private String newDeath;
    @SerializedName("total_recovered")
    @Expose
    private String totalRecovered;
    @SerializedName("active_cases")
    @Expose
    private String activeCases;
    @SerializedName("critical_cases")
    @Expose
    private String criticalCases;
    @SerializedName("total_cases_per_million")
    @Expose
    private String totalCasesPerMillion;
    @SerializedName("total_death_per_million")
    @Expose
    private String totalDeathPerMillion;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }

    public String getNewCases() {
        return newCases;
    }

    public void setNewCases(String newCases) {
        this.newCases = newCases;
    }

    public String getTotalDeath() {
        return totalDeath;
    }

    public void setTotalDeath(String totalDeath) {
        this.totalDeath = totalDeath;
    }

    public String getNewDeath() {
        return newDeath;
    }

    public void setNewDeath(String newDeath) {
        this.newDeath = newDeath;
    }

    public String getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(String totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public String getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(String activeCases) {
        this.activeCases = activeCases;
    }

    public String getCriticalCases() {
        return criticalCases;
    }

    public void setCriticalCases(String criticalCases) {
        this.criticalCases = criticalCases;
    }

    public String getTotalCasesPerMillion() {
        return totalCasesPerMillion;
    }

    public void setTotalCasesPerMillion(String totalCasesPerMillion) {
        this.totalCasesPerMillion = totalCasesPerMillion;
    }

    public String getTotalDeathPerMillion() {
        return totalDeathPerMillion;
    }

    public void setTotalDeathPerMillion(String totalDeathPerMillion) {
        this.totalDeathPerMillion = totalDeathPerMillion;
    }
}
