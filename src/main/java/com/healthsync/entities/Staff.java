package com.healthsync.entities;

public class Staff extends User {
    private String email;

    public Staff(String userID, String firstName, String lastName, String password, String email, String role) {
        super(userID, firstName, lastName, password, role);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "userID='" + getUserId() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", role='" + getRole() + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
