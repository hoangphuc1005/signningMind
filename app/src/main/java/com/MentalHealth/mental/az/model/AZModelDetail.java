package com.MentalHealth.mental.az.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AZModelDetail {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("subject_id")
    @Expose
    private String subjectId;
    @SerializedName("definition")
    @Expose
    private String definition;
    @SerializedName("symptom")
    @Expose
    private String symptom;
    @SerializedName("treatments")
    @Expose
    private String treatments;
    @SerializedName("quote")
    @Expose
    private String quote;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("help")
    @Expose
    private String help;
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

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getTreatments() {
        return treatments;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
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
