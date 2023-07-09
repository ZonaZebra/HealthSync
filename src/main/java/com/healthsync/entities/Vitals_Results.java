package com.healthsync.entities;

public class Vitals_Results {
    private int vitals_results_id; // primary key
    private int height;
    private int weight;
    private int systolic_bp;
    private int diastolic_bp;
    private int resting_pulse;
    private int temperature;

    private String patient_id; // foreign key

    // Constructors

    public Vitals_Results(int vitals_results_id, int height, int weight, int systolic_bp, int diastolic_bp, int resting_pulse, int temperature, String patient_id) {
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSystolic_bp() {
        return systolic_bp;
    }

    public void setSystolic_bp(int systolic_bp) {
        this.systolic_bp = systolic_bp;
    }

    public int getDiastolic_bp() {
        return diastolic_bp;
    }

    public void setDiastolic_bp(int diastolic_bp) {
        this.diastolic_bp = diastolic_bp;
    }

    public int getResting_pulse() {
        return resting_pulse;
    }

    public void setResting_pulse(int resting_pulse) {
        this.resting_pulse = resting_pulse;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
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
