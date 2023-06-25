package com.healthsync.service;

import com.healthsync.dao.PatientDao;
import com.healthsync.entities.Patient;

public class RegistrationService extends UserService {

    private final PatientDao patientDao = new PatientDao();

    public Patient registerPatient(String firstName, String lastName, String password,
                                   java.util.Date birthday, String contactInformation,
                                   String insuranceInformation, String pharmacyInformation) {

        // Generate unique user ID
        String userId = generateUserId(firstName, lastName);

        // Hash the password
        String hashedPassword = hashPassword(password);

        // Create Patient object
        Patient patient = new Patient(
                userId,
                firstName,
                lastName,
                hashedPassword,
                birthday,
                contactInformation,
                insuranceInformation,
                pharmacyInformation
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
