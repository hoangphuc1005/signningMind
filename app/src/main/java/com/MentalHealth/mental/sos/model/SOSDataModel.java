package com.MentalHealth.mental.sos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SOSDataModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
