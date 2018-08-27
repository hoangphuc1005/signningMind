package com.MentalHealth.mental.infonew.model;

public class InfoNewModel {
    private String titleInfo;
    private int imgInfo;

    public InfoNewModel(String titleInfo, int imgInfo) {
        this.titleInfo = titleInfo;
        this.imgInfo = imgInfo;
    }

    public String getTitleInfo() {
        return titleInfo;
    }

    public void setTitleInfo(String titleInfo) {
        this.titleInfo = titleInfo;
    }

    public int getImgInfo() {
        return imgInfo;
    }

    public void setImgInfo(int imgInfo) {
        this.imgInfo = imgInfo;
    }
}
