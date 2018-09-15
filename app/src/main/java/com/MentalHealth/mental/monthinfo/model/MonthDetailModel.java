package com.MentalHealth.mental.monthinfo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MonthDetailModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("keyword")
    @Expose
    private String keyword;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("noti_at")
    @Expose
    private Object notiAt;
    @SerializedName("active")
    @Expose
    private String active;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Object getNotiAt() {
        return notiAt;
    }

    public void setNotiAt(Object notiAt) {
        this.notiAt = notiAt;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
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
