package com.healthsync.entities;

public class Vitals_Results {
    private int vitals_results_id; // primary key
    private String height;
    private String weight;
    private String systolic_bp;
    private String diastolic_bp;
    private String resting_pulse;
    private String temperature;

    private String patient_id; // foreign key

    // Constructors

    public Vitals_Results(int vitals_results_id, String height, String weight, String systolic_bp, String diastolic_bp, String resting_pulse, String temperature, String patient_id) {
        this.vitals_results_id = vitals_results_id;
        this.height = height;
        this.weight = weight;
        this.systolic_bp = systolic_bp;
        this.diastolic_bp = diastolic_bp;
        this.resting_pulse = resting_pulse;
        this.temperature = temperature;
        this.patient_id = patient_id;
    }

    public int getVitals_results_id() {
        return vitals_results_id;
    }

    public void setVitals_results_id(int vitals_results_id) {
        this.vitals_results_id = vitals_results_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSystolic_bp() {
        return systolic_bp;
    }

    public void setSystolic_bp(String systolic_bp) {
        this.systolic_bp = systolic_bp;
    }

    public String getDiastolic_bp() {
        return diastolic_bp;
    }

    public void setDiastolic_bp(String diastolic_bp) {
        this.diastolic_bp = diastolic_bp;
    }

    public String getResting_pulse() {
        return resting_pulse;
    }

    public void setResting_pulse(String resting_pulse) {
        this.resting_pulse = resting_pulse;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    // toString

    @Override
    public String toString() {
        return "Vitals_Results{" +
                "vitals_results_id='" + vitals_results_id + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", systolic_bp='" + systolic_bp + '\'' +
                ", diastolic_bp='" + diastolic_bp + '\'' +
                ", resting_pulse='" + resting_pulse + '\'' +
                ", temperature='" + temperature + '\'' +
                '}';
    }
}
