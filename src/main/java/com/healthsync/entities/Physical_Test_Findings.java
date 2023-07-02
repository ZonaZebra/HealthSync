package com.healthsync.entities;

public class Physical_Test_Findings {
    private final int physical_test_id; // primary key
    private String issues;
    private String notes;
    private String patient_id; // references userID from User table
    private String administered_by; // references userID from User table

    // Constructors

    public Physical_Test_Findings(int physical_test_id, String issues, String notes, String patient_id, String administered_by) {
        this.physical_test_id = physical_test_id;
        this.issues = issues;
        this.notes = notes;
        this.patient_id = patient_id;
        this.administered_by = administered_by;
    }

    public int getPhysical_test_id() {
        return physical_test_id;
    }

    public String getIssues() {
        return issues;
    }

    public void setIssues(String issues) {
        this.issues = issues;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPatientID() {
        return patient_id;
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
        return "Physical_Test_Findings{" +
                "physical_test_id='" + physical_test_id + '\'' +
                ", issues='" + issues + '\'' +
                ", notes='" + notes + '\'' +
                ", administered_by='" + administered_by + '\'' +
                '}';
    }
}
