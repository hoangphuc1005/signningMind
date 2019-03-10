package com.MentalHealth.mental.infonew.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IntroductModel {

    @SerializedName("info")
    @Expose
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}