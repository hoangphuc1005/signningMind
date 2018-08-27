package com.MentalHealth.mental.home.model;

/**
 * Created by hoangphuc on 27/02/2018.
 */

public class SlidingMenuModel {

    private int imgIcons;
    private String tvItems;
    private int state;


    public SlidingMenuModel(int imgIcons, String tvItems) {
        this.imgIcons = imgIcons;
        this.tvItems = tvItems;

    }

    public String getTvItems() {
        return tvItems;
    }

    public void setTvItems(String tvItems) {
        this.tvItems = tvItems;
    }

    public int getImgIcons() {
        return imgIcons;
    }

    public void setImgIcons(int imgIcons) {
        this.imgIcons = imgIcons;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}