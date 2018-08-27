package com.MentalHealth.mental.gamemini.model;

public class AnswerModel {
    private String question;
    private int bgQuestion;
    private int choiceAnswer;

    public int getChoiceAnswer() {
        return choiceAnswer;
    }

    public void setChoiceAnswer(int choiceAnswer) {
        this.choiceAnswer = choiceAnswer;
    }

    public AnswerModel(String question, int bgQuestion) {
        this.question = question;
        this.bgQuestion = bgQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getBgQuestion() {
        return bgQuestion;
    }

    public void setBgQuestion(int bgQuestion) {
        this.bgQuestion = bgQuestion;
    }
}
