package com.MentalHealth.mental.home.model;



public class MentalHelpModel {
    private String desc;
    private String detail;

    public MentalHelpModel(String desc, String detail) {
        this.desc = desc;
        this.detail = detail;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
