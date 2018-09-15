package com.MentalHealth.mental.gamemini.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizDataDetail {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("level_id")
    @Expose
    private String levelId;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("choose1")
    @Expose
    private String choose1;
    @SerializedName("choose2")
    @Expose
    private String choose2;
    @SerializedName("choose3")
    @Expose
    private String choose3;
    @SerializedName("choose4")
    @Expose
    private String choose4;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("about_answer")
    @Expose
    private String aboutAnswer;
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

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChoose1() {
        return choose1;
    }

    public void setChoose1(String choose1) {
        this.choose1 = choose1;
    }

    public String getChoose2() {
        return choose2;
    }

    public void setChoose2(String choose2) {
        this.choose2 = choose2;
    }

    public String getChoose3() {
        return choose3;
    }

    public void setChoose3(String choose3) {
        this.choose3 = choose3;
    }

    public String getChoose4() {
        return choose4;
    }

    public void setChoose4(String choose4) {
        this.choose4 = choose4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAboutAnswer() {
        return aboutAnswer;
    }

    public void setAboutAnswer(String aboutAnswer) {
        this.aboutAnswer = aboutAnswer;
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
