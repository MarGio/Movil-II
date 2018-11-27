package com.example.mario.fragmentdinamico;

import java.util.Set;

public class AnswerSheet extends Question{

    private Set<Answer> tanswer;
    private Student student;
    private Test test;

    public AnswerSheet( Test test) {
        this.test = test;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Set<Answer> getTanswer() {
        return tanswer;
    }

    public void setTanswer(Set<Answer> tanswer) {
        this.tanswer = tanswer;
    }
}
