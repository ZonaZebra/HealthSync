package com.healthsync.service;

import com.healthsync.dao.PrescriptionsDao;
import com.healthsync.entities.Prescriptions;

public class PrescriptionService {

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
            return prescription;
        } else {
            return null;
        }
    }
}
