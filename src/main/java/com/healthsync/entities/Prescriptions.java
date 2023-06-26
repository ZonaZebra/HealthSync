package com.healthsync.entities;

public class Prescriptions {
    private int prescription_id; // primary key
    private String product;
    private int dosage_in_mg;
    private int frequency;
    private String instructions;
    private int pharmacy_id; // references pharmacy_info(pharmacy_id)
    private String prescribed_by; // references staff(userid)

    // Constructors

    public Prescriptions(int prescription_id, String product, int dosage_in_mg, int frequency, String instructions, int pharmacy_id, String prescribed_by) {
        this.prescription_id = prescription_id;
        this.product = product;
        this.dosage_in_mg = dosage_in_mg;
        this.frequency = frequency;
        this.instructions = instructions;
        this.pharmacy_id = pharmacy_id;
        this.prescribed_by = prescribed_by;
    }

    // Getters

    public int getPrescription_id() {
        return prescription_id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getDosage_in_mg() {
        return dosage_in_mg;
    }

    public void setDosage_in_mg(int dosage_in_mg) {
        this.dosage_in_mg = dosage_in_mg;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    // Setters

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getPharmacy_id() {
        return pharmacy_id;
    }

    public void setPharmacy_id(int pharmacy_id) {
        this.pharmacy_id = pharmacy_id;
    }

    public String getPrescribed_by() {
        return prescribed_by;
    }

    public void setPrescribed_by(String prescribed_by) {
        this.prescribed_by = prescribed_by;
    }

    // toString

    @Override
    public String toString() {
        return "Prescriptions{" +
                "prescription_id='" + prescription_id + '\'' +
                ", product='" + product + '\'' +
                ", dosage_in_mg='" + dosage_in_mg + '\'' +
                ", frequency='" + frequency + '\'' +
                ", instructions='" + instructions + '\'' +
                ", pharmacy_id='" + pharmacy_id + '\'' +
                ", prescribed_by='" + prescribed_by + '\'' +
                '}';
    }
}
