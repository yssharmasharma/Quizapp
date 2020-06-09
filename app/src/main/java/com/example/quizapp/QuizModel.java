package com.example.quizapp;

public class QuizModel {
    private int mquestion;
    private boolean manswer;

    public QuizModel(int question, boolean answer) {
        this.mquestion = question;
        this.manswer = answer;
    }

    public int getQuestion() {
        return mquestion;
    }

    public void setQuestion(int question) {
        this.mquestion = question;
    }

    public boolean isAnswer() {
        return manswer;
    }

    public void setAnswer(boolean answer) {
        this.manswer = answer;
    }
}
