package com.healthsync.entities;

public class Prescriptions {
    private int prescription_id; // primary key
    private String product;
    private int dosage_in_mg;
    private int frequency;
    private String instructions;
    private String pharmacy_id; // references pharmacy_info(pharmacy_id)
    private String patient_id; // references userID from User table
    private String prescribed_by; // references staff(userid)

    // Constructors

    public Prescriptions(int prescription_id, String product, int dosage_in_mg, int frequency, String instructions, String pharmacy_id, String patient_id, String prescribed_by) {
        this.prescription_id = prescription_id;
        this.product = product;
        this.dosage_in_mg = dosage_in_mg;
        this.frequency = frequency;
        this.instructions = instructions;
        this.pharmacy_id = pharmacy_id;
        this.patient_id = patient_id;
        this.prescribed_by = prescribed_by;
    }

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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPharmacy_id() {
        return pharmacy_id;
    }

    public void setPharmacy_id(String pharmacy_id) {
        this.pharmacy_id = pharmacy_id;
    }

    public String getPrescribed_by() {
        return prescribed_by;
    }

    public void setPrescribed_by(String prescribed_by) {
        this.prescribed_by = prescribed_by;
    }

    public String getPatient_id() {
        return patient_id;
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
