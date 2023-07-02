package com.healthsync.service;

import com.healthsync.dao.PatientDao;
import com.healthsync.dao.PhysicalTestFindingsDao;
import com.healthsync.dao.PrescriptionsDao;

public class DoctorService {
    private final PatientDao patientDao = new PatientDao();
    private final PhysicalTestFindingsDao testFindingsDao= new PhysicalTestFindingsDao();
    private final PrescriptionsDao prescriptionsDao = new PrescriptionsDao();



}
