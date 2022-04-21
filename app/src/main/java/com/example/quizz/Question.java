package com.example.quizz;

public class Question {
    private String questions;
    private  BonneReponse bonneReponse;

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public BonneReponse getBonneReponse() {
        return bonneReponse;
    }

    public void setBonneReponse(BonneReponse bonneReponse) {
        this.bonneReponse = bonneReponse;
    }

    public Question(String questions, BonneReponse bonneReponse) {
        this.questions = questions;
        this.bonneReponse = bonneReponse;
    }

    public Question() {
    }
}
