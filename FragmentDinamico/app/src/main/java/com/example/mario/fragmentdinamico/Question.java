package com.example.mario.fragmentdinamico;

import java.io.Serializable;

public class Question implements Serializable {

    private long idQuestion;
    private QuestionData data;



    public Question(QuestionData questionData) {
        this.data = questionData;
    }

    public Question() {
        this(new QuestionData());
    }

    public long getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(long idQuestion) {
        this.idQuestion = idQuestion;
    }

    public QuestionData getData() {
        return data;
    }

    public void setData(QuestionData data) {
        this.data = data;
    }

    public QuestionData.QuestionType getType() {
        return this.getData().getType();
    }

    public void setType(QuestionData.QuestionType type) {
        this.getData().setType(type);
    }
}
