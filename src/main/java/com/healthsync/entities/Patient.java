package com.healthsync.entities;

import java.util.Date;

public class Patient extends User {
    private Date birthday;
    private String phoneNumber;
    private String email;
    private String insuranceProvider;
    private String insurancePolicyNumber;
    private String pharmacyName;
    private String pharmacyLocation;

    // Constructors, getters and setters
    public Patient(String userID, String firstName, String lastName, String password, String role) {
        super(userID, firstName, lastName, password, role);
    }

}
