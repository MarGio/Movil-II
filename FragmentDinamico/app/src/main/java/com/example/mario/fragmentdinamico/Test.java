package com.example.mario.fragmentdinamico;

import android.util.ArraySet;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

public class Test {

    private long idTest;
    private Set<Question> questions;
    private String desciption;
    private Date startDate;
    private Date endDate;
    private Time totalTime;
    private String teacherName;
    private String groupCode;

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Time totalTime) {
        this.totalTime = totalTime;
    }

    public Test(long idTest) {
        this.idTest = idTest;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Test(){
        this.questions = new ArraySet<Question>();

    }

    public long getIdTest() {
        return idTest;
    }

    public void setIdTest(long idTest) {
        this.idTest = idTest;
    }


    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questionSet) {
        this.questions = questionSet;
    }
}
