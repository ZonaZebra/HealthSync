package com.healthsync.service;

import com.healthsync.dao.PatientDao;
import com.healthsync.dao.PhysicalTestFindingsDao;
import com.healthsync.dao.PrescriptionsDao;
import com.healthsync.entities.Physical_Test_Findings;
import com.healthsync.entities.Prescriptions;

public class DoctorService {
    private final PatientDao patientDao = new PatientDao();
    private final PhysicalTestFindingsDao testFindingsDao = new PhysicalTestFindingsDao();
    private final PrescriptionsDao prescriptionsDao = new PrescriptionsDao();

    public Prescriptions createPrescription(String product, int dosage_in_mg, int frequency, String instructions, int pharmacy_id, String patient_id, String prescribed_by) {
        Prescriptions prescription = new Prescriptions(
                -1,
                product,
                dosage_in_mg,
                frequency,
                instructions,
                pharmacy_id,
                patient_id,
                prescribed_by
        );

        int prescription_id = prescriptionsDao.createPrescription(prescription);
        if (prescription_id != -1) {
            prescription.setPrescription_id(prescription_id);
            System.out.println(prescription);
            return prescription;
        } else {
            return null;
        }
    }

    public Physical_Test_Findings createPhysicalTestFindingsEntry(String issues, String notes, String patient_id, String administered_by) {
        Physical_Test_Findings testFindings = new Physical_Test_Findings(
                -1,
                issues,
                notes,
                patient_id,
                administered_by
        );

        int physical_test_id = testFindingsDao.createPhysicalTestFinding(testFindings);
        if (physical_test_id != -1) {
            testFindings.setPhysical_test_id(physical_test_id);
            System.out.println(testFindings);
            return testFindings;
        } else {
            return null;
        }
    }
}
