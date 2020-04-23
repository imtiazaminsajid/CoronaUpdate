package com.imtiazaminsajid.coronaupdate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AgesModel {
    @SerializedName("onetoten")
    @Expose
    private OnetotenModel onetoten;
    @SerializedName("eleventotwenty")
    @Expose
    private EleventotwentyModel eleventotwenty;
    @SerializedName("twentyonetothirty")
    @Expose
    private TwentyonetothirtyModel twentyonetothirty;
    @SerializedName("thirtyonetofourty")
    @Expose
    private ThirtyonetofourtyModel thirtyonetofourty;
    @SerializedName("fourtyonetofifty")
    @Expose
    private FourtyonetofiftyModel fourtyonetofifty;
    @SerializedName("fiftyonetosixty")
    @Expose
    private FiftyonetosixtyModel fiftyonetosixty;
    @SerializedName("sixtyplus")
    @Expose
    private SixtyplusModel sixtyplus;

    public OnetotenModel getOnetoten() {
        return onetoten;
    }

    public void setOnetoten(OnetotenModel onetoten) {
        this.onetoten = onetoten;
    }

    public EleventotwentyModel getEleventotwenty() {
        return eleventotwenty;
    }

    public void setEleventotwenty(EleventotwentyModel eleventotwenty) {
        this.eleventotwenty = eleventotwenty;
    }

    public TwentyonetothirtyModel getTwentyonetothirty() {
        return twentyonetothirty;
    }

    public void setTwentyonetothirty(TwentyonetothirtyModel twentyonetothirty) {
        this.twentyonetothirty = twentyonetothirty;
    }

    public ThirtyonetofourtyModel getThirtyonetofourty() {
        return thirtyonetofourty;
    }

    public void setThirtyonetofourty(ThirtyonetofourtyModel thirtyonetofourty) {
        this.thirtyonetofourty = thirtyonetofourty;
    }

    public FourtyonetofiftyModel getFourtyonetofifty() {
        return fourtyonetofifty;
    }

    public void setFourtyonetofifty(FourtyonetofiftyModel fourtyonetofifty) {
        this.fourtyonetofifty = fourtyonetofifty;
    }

    public FiftyonetosixtyModel getFiftyonetosixty() {
        return fiftyonetosixty;
    }

    public void setFiftyonetosixty(FiftyonetosixtyModel fiftyonetosixty) {
        this.fiftyonetosixty = fiftyonetosixty;
    }

    public SixtyplusModel getSixtyplus() {
        return sixtyplus;
    }

    public void setSixtyplus(SixtyplusModel sixtyplus) {
        this.sixtyplus = sixtyplus;
    }
}
