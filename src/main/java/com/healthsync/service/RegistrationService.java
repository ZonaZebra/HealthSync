package com.healthsync.service;

import com.healthsync.dao.PatientDao;
import com.healthsync.entities.Patient;
import com.healthsync.entities.Pharmacy;

import java.util.*;
import java.util.regex.Pattern;

public class RegistrationService extends UserService {

    private final PatientDao patientDao = new PatientDao();

    // Initialize a Map with insurance companies and their policies
    private final Map<String, List<String>> insuranceCompanies = new HashMap<>() {{
        put("Allianz", Arrays.asList("Comprehensive Health Insurance", "International Health Insurance", "Budget Health Insurance"));
        put("AXA", Arrays.asList("Direct Health Insurance", "Global Health Insurance", "Basic Health Insurance"));
        put("Prudential", Arrays.asList("Premium Health Insurance", "Travel Health Insurance", "Basic Health Insurance"));
        put("Aviva", Arrays.asList("Family Health Insurance", "International Health Insurance", "Budget Health Insurance"));
    }};

    // Initialize a list with pharmacy information

    private final List<Pharmacy> pharmacies = Arrays.asList(
            new Pharmacy(111, "Walgreens", "Camelback Rd & N 32nd St"),
            new Pharmacy(222, "CVS Pharmacy", "Indian School Rd & N 7th St"),
            new Pharmacy(333, "Fry's Pharmacy", "W Bell Rd & N 7th Ave"),
            new Pharmacy(444, "Osco Drug", "E Broadway Blvd & S Country Club Rd")
    );

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

    // Get insurance companies
    public Map<String, List<String>> getInsuranceCompanies() {
        return insuranceCompanies;
    }

    // Get pharmacies
    public List<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public int getPharmacyId(String pharmacyName) {
        for (Pharmacy pharmacy : pharmacies) {
            if (pharmacy.getName().equals(pharmacyName)) {
                return pharmacy.getId();
            }
        }
        return -1;
    }

    public static boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        return email != null && pat.matcher(email).matches();
    }

    private static final String PHONE_REGEX = "^[0-9]{10}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    public static boolean isPhoneNumberValid(String phoneNumber) {
        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }

}
