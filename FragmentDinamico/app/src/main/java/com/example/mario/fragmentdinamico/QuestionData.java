package com.example.mario.fragmentdinamico;

import java.util.Objects;

public class QuestionData {

    public enum  QuestionType{OPEN,MULTIPLE_CHOICE,SINGLE_CHOICE, TRUE_FALSE,RELATIONAL};
    private String IdQuestion;
    private String statement;
    private String answers;
    private String correct;
    private QuestionType type;

    public QuestionData() {
    }


    public QuestionData(String statement, String answers, String correct, QuestionType type) {
        this.statement = statement;
        this.answers = answers;
        this.correct = correct;
        this.type = type;
    }

    public String getstatement() {
        return statement;
    }

    public String getIdQuestion() {
        return IdQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        IdQuestion = idQuestion;
    }

    public void setstatement(String statement) {
        this.statement = statement;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionData that = (QuestionData) o;
        return Objects.equals(statement, that.statement) &&
                Objects.equals(answers, that.answers) &&
                Objects.equals(correct, that.correct) &&
                type == that.type;
    }

    @Override
    public int hashCode() {

        return Objects.hash(statement, answers, correct, type);
    }

    @Override
    public String toString() {
        return "QuestionData{" +
                "idQuestion ='" + this.getIdQuestion()+ '\'' +
                "statement='" + statement + '\'' +
                ", answers='" + answers + '\'' +
                ", correct='" + correct + '\'' +
                ", type=" + type +
                '}';
    }
}
