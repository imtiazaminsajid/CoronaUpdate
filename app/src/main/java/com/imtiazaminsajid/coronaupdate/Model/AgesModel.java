package com.imtiazaminsajid.coronaupdate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AgesModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("onetoten")
    @Expose
    private String onetoten;
    @SerializedName("tentotwenty")
    @Expose
    private String tentotwenty;
    @SerializedName("twentytothirty")
    @Expose
    private String twentytothirty;
    @SerializedName("thirtytoforty")
    @Expose
    private String thirtytoforty;
    @SerializedName("fortytofifty")
    @Expose
    private String fortytofifty;
    @SerializedName("fiftytosixty")
    @Expose
    private String fiftytosixty;
    @SerializedName("sixtyplus")
    @Expose
    private String sixtyplus;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOnetoten() {
        return onetoten;
    }

    public void setOnetoten(String onetoten) {
        this.onetoten = onetoten;
    }

    public String getTentotwenty() {
        return tentotwenty;
    }

    public void setTentotwenty(String tentotwenty) {
        this.tentotwenty = tentotwenty;
    }

    public String getTwentytothirty() {
        return twentytothirty;
    }

    public void setTwentytothirty(String twentytothirty) {
        this.twentytothirty = twentytothirty;
    }

    public String getThirtytoforty() {
        return thirtytoforty;
    }

    public void setThirtytoforty(String thirtytoforty) {
        this.thirtytoforty = thirtytoforty;
    }

    public String getFortytofifty() {
        return fortytofifty;
    }

    public void setFortytofifty(String fortytofifty) {
        this.fortytofifty = fortytofifty;
    }

    public String getFiftytosixty() {
        return fiftytosixty;
    }

    public void setFiftytosixty(String fiftytosixty) {
        this.fiftytosixty = fiftytosixty;
    }

    public String getSixtyplus() {
        return sixtyplus;
    }

    public void setSixtyplus(String sixtyplus) {
        this.sixtyplus = sixtyplus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
