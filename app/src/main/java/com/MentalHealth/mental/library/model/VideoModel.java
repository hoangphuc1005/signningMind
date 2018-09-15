package com.MentalHealth.mental.library.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoModel {

    @SerializedName("per_page")
    @Expose
    private Integer perPage;
    @SerializedName("current_page")
    @Expose
    private Integer currentPage;
    @SerializedName("next_page_url")
    @Expose
    private Object nextPageUrl;
    @SerializedName("prev_page_url")
    @Expose
    private Object prevPageUrl;
    @SerializedName("from")
    @Expose
    private Integer from;
    @SerializedName("to")
    @Expose
    private Integer to;
    @SerializedName("data")
    @Expose
    private List<DataVideoModel> data = null;

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Object getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(Object nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public Object getPrevPageUrl() {
        return prevPageUrl;
    }

    public void setPrevPageUrl(Object prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public List<DataVideoModel> getData() {
        return data;
    }

    public void setData(List<DataVideoModel> data) {
        this.data = data;
    }

}