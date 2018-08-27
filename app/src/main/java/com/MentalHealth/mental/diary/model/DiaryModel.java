package com.MentalHealth.mental.diary.model;

import java.io.Serializable;


public class DiaryModel implements Serializable {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String dateOfDiary;
    private String monthOfDiary;
    private String titleOfDiary;

    public String getMonthOfDiary() {
        return monthOfDiary;
    }

    public void setMonthOfDiary(String monthOfDiary) {
        this.monthOfDiary = monthOfDiary;
    }

    private String contentOfDiary;

    public String getDateOfDiary() {
        return dateOfDiary;
    }

    public void setDateOfDiary(String dateOfDiary) {
        this.dateOfDiary = dateOfDiary;
    }

    public String getTitleOfDiary() {
        return titleOfDiary;
    }

    public void setTitleOfDiary(String titleOfDiary) {
        this.titleOfDiary = titleOfDiary;
    }

    public String getContentOfDiary() {
        return contentOfDiary;
    }

    public void setContentOfDiary(String contentOfDiary) {
        this.contentOfDiary = contentOfDiary;
    }
}
