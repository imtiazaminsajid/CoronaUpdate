package com.imtiazaminsajid.coronaupdate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WorldOthersModel {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("endpoints")
    @Expose
    private List<WorldEndpointModel> endpoints = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<WorldEndpointModel> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<WorldEndpointModel> endpoints) {
        this.endpoints = endpoints;
    }
}
