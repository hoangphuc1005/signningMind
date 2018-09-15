package com.MentalHealth.mental.monthinfo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataAlldayModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("wasRead")
    @Expose
    private Boolean wasRead;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Boolean getWasRead() {
        return wasRead;
    }

    public void setWasRead(Boolean wasRead) {
        this.wasRead = wasRead;
    }
}
