package com.healthsync.entities;

public class Vitals_Results {
    private int vitals_results_id; // primary key
    private double height;
    private double weight;
    private int systolic_bp;
    private int diastolic_bp;
    private double resting_pulse;
    private double temperature;

    private String patient_id; // foreign key

    // Constructors

    public Vitals_Results(int vitals_results_id, double height, double weight, int systolic_bp, int diastolic_bp, double resting_pulse, double temperature, String patient_id) {
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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
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

    public double getResting_pulse() {
        return resting_pulse;
    }

    public void setResting_pulse(double resting_pulse) {
        this.resting_pulse = resting_pulse;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
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
