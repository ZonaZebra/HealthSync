package com.healthsync.entities;

import java.util.Date;

public class Questionnaire_Results {
    private final int questionnaire_id; // primary key
    private String name;
    private Date date;
    private char sex;
    private String administered_by; // this will reference userID from User table

    // Constructors

    public Questionnaire_Results(int questionnaire_id, String name, Date date, char sex, String administered_by) {
        this.questionnaire_id = questionnaire_id;
        this.name = name;
        this.date = date;
        this.sex = sex;
        this.administered_by = administered_by;
    }

    public int getQuestionnaire_id() {
        return questionnaire_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getAdministered_by() {
        return administered_by;
    }

    public void setAdministered_by(String administered_by) {
        this.administered_by = administered_by;
    }

    // toString

    @Override
    public String toString() {
        return "Questionnaire_Results{" +
                "questionnaire_id=" + questionnaire_id +
                ", name='" + name + '\'' +
                ", date=" + date + '\'' +
                ", sex=" + sex +
                ", administered_by=" + administered_by +
                '}';
    }
}
