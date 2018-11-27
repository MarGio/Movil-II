package com.example.mario.fragmentdinamico;

public class Student  {

    private String idStudent;
    private String user;
    private String pass;
    private String email;
    private String facebookAccount;

    public Student(String idStudent, String user, String pass, String email, String facebookAccount) {
        this.idStudent = idStudent;
        this.user = user;
        this.pass = pass;
        this.email = email;
        this.facebookAccount = facebookAccount;
    }

    public Student(){

    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebookAccount() {
        return facebookAccount;
    }

    public void setFacebookAccount(String facebookAccount) {
        this.facebookAccount = facebookAccount;
    }
}
