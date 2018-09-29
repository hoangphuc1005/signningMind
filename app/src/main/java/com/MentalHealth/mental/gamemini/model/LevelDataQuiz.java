package com.MentalHealth.mental.gamemini.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LevelDataQuiz {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("noti_at")
    @Expose
    private String notiAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    private int imgBgChoice;

    public int getImgBgChoice() {
        return imgBgChoice;
    }
    private Boolean check;

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public void setImgBgChoice(int imgBgChoice) {
        this.imgBgChoice = imgBgChoice;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getNotiAt() {
        return notiAt;
    }

    public void setNotiAt(String notiAt) {
        this.notiAt = notiAt;
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

