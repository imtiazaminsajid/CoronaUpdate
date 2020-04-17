package com.imtiazaminsajid.coronaupdate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecoveredModel {
    @SerializedName("today")
    @Expose
    private Integer today;
    @SerializedName("yesterday")
    @Expose
    private Integer yesterday;
    @SerializedName("2daysago")
    @Expose
    private Integer _2daysago;
    @SerializedName("3daysago")
    @Expose
    private Integer _3daysago;
    @SerializedName("4daysago")
    @Expose
    private Integer _4daysago;
    @SerializedName("5daysago")
    @Expose
    private Integer _5daysago;
    @SerializedName("6daysago")
    @Expose
    private Integer _6daysago;

    public Integer getToday() {
        return today;
    }

    public void setToday(Integer today) {
        this.today = today;
    }

    public Integer getYesterday() {
        return yesterday;
    }

    public void setYesterday(Integer yesterday) {
        this.yesterday = yesterday;
    }

    public Integer get_2daysago() {
        return _2daysago;
    }

    public void set_2daysago(Integer _2daysago) {
        this._2daysago = _2daysago;
    }

    public Integer get_3daysago() {
        return _3daysago;
    }

    public void set_3daysago(Integer _3daysago) {
        this._3daysago = _3daysago;
    }

    public Integer get_4daysago() {
        return _4daysago;
    }

    public void set_4daysago(Integer _4daysago) {
        this._4daysago = _4daysago;
    }

    public Integer get_5daysago() {
        return _5daysago;
    }

    public void set_5daysago(Integer _5daysago) {
        this._5daysago = _5daysago;
    }

    public Integer get_6daysago() {
        return _6daysago;
    }

    public void set_6daysago(Integer _6daysago) {
        this._6daysago = _6daysago;
    }
}
