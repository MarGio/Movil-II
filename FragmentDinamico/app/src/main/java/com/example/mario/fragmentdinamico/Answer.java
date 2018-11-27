package com.example.mario.fragmentdinamico;

import java.io.Serializable;

public class Answer implements Serializable {
    private long idAnswer;
    private String answer;
    private float evaluation;
    private Question question;
    private AnswerSheet answerSheet;

    public Answer(long identificador) {
        this.idAnswer = identificador;
    }

    public Answer(){

    }

    public long getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(long idAnswer) {
        this.idAnswer = idAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public float getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(float evaluation) {
        this.evaluation = evaluation;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public AnswerSheet getAnswerSheet() {
        return answerSheet;
    }

    public void setAnswerSheet(AnswerSheet answerSheet) {
        this.answerSheet = answerSheet;
    }
}
