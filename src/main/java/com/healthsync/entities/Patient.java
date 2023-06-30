package com.healthsync.entities;

import java.util.Date;

public class Patient extends User {
    private Date birthday;
    private String contactInformation;
    private String insuranceInformation;
    private String pharmacyInformation;

    public Patient(String userID, String firstName, String lastName, String password,
                   Date birthday, String contactInformation, String insuranceInformation,
                   String pharmacyInformation) {
        super(userID, firstName, lastName, password, "Patient");
        this.birthday = birthday;
        this.contactInformation = contactInformation;
        this.insuranceInformation = insuranceInformation;
        this.pharmacyInformation = pharmacyInformation;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getInsuranceInformation() {
        return insuranceInformation;
    }

    public void setInsuranceInformation(String insuranceInformation) {
        this.insuranceInformation = insuranceInformation;
    }

    public String getPharmacyInformation() {
        return pharmacyInformation;
    }

    public void setPharmacyInformation(String pharmacyInformation) {
        this.pharmacyInformation = pharmacyInformation;
    }

    public String getPhoneNumber(){
        String phone= "";
        int i =0;
        while(contactInformation.charAt(i) != ','){
            phone+=contactInformation.charAt(i);
            i++;

        }

        return phone;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "userID='" + getUserId() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", role='" + getRole() + '\'' +
                ", birthday=" + birthday +
                ", contactInformation='" + contactInformation + '\'' +
                ", insuranceInformation='" + insuranceInformation + '\'' +
                ", pharmacyInformation='" + pharmacyInformation + '\'' +
                '}';
    }
}
