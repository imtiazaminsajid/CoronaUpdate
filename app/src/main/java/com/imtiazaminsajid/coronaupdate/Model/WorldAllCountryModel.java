package com.imtiazaminsajid.coronaupdate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WorldAllCountryModel {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<WorldDatumModel> data;
    @SerializedName("others")
    @Expose
    private WorldOthersModel others;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<WorldDatumModel> getData() {
        return data;
    }

    public void setData(List<WorldDatumModel> data) {
        this.data = data;
    }

    public WorldOthersModel getOthers() {
        return others;
    }

    public void setOthers(WorldOthersModel others) {
        this.others = others;
    }
}
