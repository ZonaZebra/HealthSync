package com.healthsync.service;

import com.healthsync.dao.AppointmentsDao;
import com.healthsync.entities.Appointments;

import java.util.Date;

public class PatientService {
    private final AppointmentsDao appointmentsDao = new AppointmentsDao();

    public Appointments createAppointment(String patient_id, String doctor_id, Date date, int questionnaire_id, int vitals_results_id, int physical_test_id) {
        Appointments appointment = new Appointments(
                -1,
                patient_id,
                doctor_id,
                date,
                questionnaire_id,
                vitals_results_id,
                physical_test_id
        );

        int appointment_id = appointmentsDao.createAppointment(appointment);
        if (appointment_id == -1) {
            return null;
        }
        appointment.setAppointment_id(appointment_id);
        System.out.println(appointment);
        return appointment;
    }
}
