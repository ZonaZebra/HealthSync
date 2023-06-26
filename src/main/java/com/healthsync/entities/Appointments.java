package com.healthsync.entities;

import java.util.Date;

public class Appointments {
    private int appointment_id; // primary key
    private String patient_id; // this will reference userID from User table
    private String doctor_id; // this will reference userID from User table
    private Date appointment_date; // this is the current date
    private int questionnaire_id; // this will reference questionnaire_id from Questionnaire table
    private int vitals_results_id; // this will reference vitals_results_id from Vitals_Results table
    private int physical_test_id; // this will reference physical_test_id from Physical_Test_Findings table

    // Constructors
    public Appointments(int appointment_id, String patient_id, String doctor_id, Date appointment_date, int questionnaire_id, int vitals_results_id, int physical_test_id) {
        this.appointment_id = appointment_id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.appointment_date = appointment_date;
        this.questionnaire_id = questionnaire_id;
        this.vitals_results_id = vitals_results_id;
        this.physical_test_id = physical_test_id;
    }

    // Getters

    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Date getAppointment_date() {
        return appointment_date;
    }

    // Setters

    public void setAppointment_date(Date appointment_date) {
        this.appointment_date = appointment_date;
    }

    public int getQuestionnaire_id() {
        return questionnaire_id;
    }

    public void setQuestionnaire_id(int questionnaire_id) {
        this.questionnaire_id = questionnaire_id;
    }

    public int getVitals_results_id() {
        return vitals_results_id;
    }

    public void setVitals_results_id(int vitals_results_id) {
        this.vitals_results_id = vitals_results_id;
    }

    public int getPhysical_test_id() {
        return physical_test_id;
    }

    public void setPhysical_test_id(int physical_test_id) {
        this.physical_test_id = physical_test_id;
    }

    // toString

    @Override
    public String toString() {
        return "Appointments{" +
                "appointment_id=" + appointment_id +
                ", patient_id='" + patient_id + '\'' +
                ", doctor_id='" + doctor_id + '\'' +
                ", appointment_date=" + appointment_date +
                ", questionnaire_id=" + questionnaire_id +
                ", vitals_results_id=" + vitals_results_id +
                ", physical_test_id=" + physical_test_id +
                '}';
    }

}
