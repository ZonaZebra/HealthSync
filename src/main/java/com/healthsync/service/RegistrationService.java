package com.healthsync.service;

import com.healthsync.dao.PatientDao;
import com.healthsync.entities.Patient;
import com.healthsync.util.UserUtils;

public class RegistrationService {

    private final PatientDao patientDao = new PatientDao();

    public Patient registerPatient(String firstName, String lastName, String password,
                                   java.util.Date birthday, String contactInformation,
                                   String insuranceInformation, String pharmacyInformation) {

        // Generate unique user ID
        String userId = UserUtils.generateUserId(firstName, lastName);

        // Hash the password
        String hashedPassword = UserUtils.hashPassword(password);

        // Create Patient object
        Patient patient = new Patient(
                userId,
                firstName,
                lastName,
                hashedPassword,
                birthday,
                contactInformation,
                insuranceInformation,
                pharmacyInformation,
                null // Assuming that no prescriptions are added during registration
        );

        // Call the createPatient method from PatientDao
        boolean isCreated = patientDao.createPatient(patient);
        if (isCreated) {
            return patient;
        } else {
            return null;
        }
    }
}
