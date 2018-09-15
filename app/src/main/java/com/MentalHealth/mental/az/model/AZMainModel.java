package com.MentalHealth.mental.az.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AZMainModel {
    private List<AZModelDetail> sponsors = null;

    public List<AZModelDetail> getSponsors() {
        return sponsors;
    }

    public void setSponsors(List<AZModelDetail> sponsors) {
        this.sponsors = sponsors;
    }
}
