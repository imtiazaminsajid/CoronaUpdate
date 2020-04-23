package com.imtiazaminsajid.coronaupdate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GendersModel {
    @SerializedName("male")
    @Expose
    private MaleModel male;
    @SerializedName("female")
    @Expose
    private FemaleModel female;

    public MaleModel getMale() {
        return male;
    }

    public void setMale(MaleModel male) {
        this.male = male;
    }

    public FemaleModel getFemale() {
        return female;
    }

    public void setFemale(FemaleModel female) {
        this.female = female;
    }
}
