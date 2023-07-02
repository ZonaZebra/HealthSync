package com.healthsync.service;

import com.healthsync.dao.PhysicalTestFindingsDao;
import com.healthsync.dao.PrescriptionsDao;
import com.healthsync.dao.QuestionnaireResultsDao;
import com.healthsync.dao.VitalsResultsDao;
import com.healthsync.entities.Physical_Test_Findings;
import com.healthsync.entities.Prescriptions;
import com.healthsync.entities.Questionnaire_Results;
import com.healthsync.entities.Vitals_Results;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class PatientHistoryService {
    private static final PhysicalTestFindingsDao testFindingsDao = new PhysicalTestFindingsDao();
    private static final QuestionnaireResultsDao questionnaireResultsDao = new QuestionnaireResultsDao();
    private static final PrescriptionsDao prescriptionsDao = new PrescriptionsDao();
    private static final VitalsResultsDao vitalsResultsDao = new VitalsResultsDao();

    public VBox getPatientHistory(String patient_ID){
        VBox test = new VBox();
        List<Physical_Test_Findings> testFindingsList = testFindingsDao.getPhysicalTestFindings(patient_ID);
        List<Questionnaire_Results> questionnaireResultsList = questionnaireResultsDao.getQuestionnaireResultsByPatientId(patient_ID);
        List<Prescriptions> prescriptionsList = prescriptionsDao.getPrescriptions(patient_ID);
        List<Vitals_Results> vitalsResultsList = vitalsResultsDao.getAllVitalsResults(patient_ID);

        // need to go through all the lists and create the box itself

        // list could have something or nothing
//        test.getChildren().add(new Text(testFindingsList.get(0).toString()));
//        test.getChildren().add(new Text("- questionnaire results"));
//        test.getChildren().add(new Text(prescriptionsList.get(0).toString()));
//        test.getChildren().add(new Text("- vitals"));

        // Add Test findings First
        if(testFindingsList.size()!= 0){test.getChildren().add(new Text("Physical Test Findings:"));}
        for (Physical_Test_Findings physical_test_findings : testFindingsList) {

            test.getChildren().add(new Text("   -"+ physical_test_findings.getPhysical_test_id()));
        }
        if(testFindingsList.size()!= 0){test.getChildren().add(new Text()); } // Add some space between sections

        // Then add questionnaire results
        if(questionnaireResultsList.size()!= 0){test.getChildren().add(new Text("Prescriptions:"));}
        for (Questionnaire_Results questionnaire_results : questionnaireResultsList) {
            test.getChildren().add(new Text("   -" + questionnaire_results.getQuestionnaire_id()));
        }
        if(questionnaireResultsList.size()!= 0){test.getChildren().add(new Text()); } // Add some space between sections

        // Then add Vitals results
        if(vitalsResultsList.size()!= 0){test.getChildren().add(new Text("Questionnaire Results:"));}
        for (Vitals_Results vitalsResults : vitalsResultsList) {
            test.getChildren().add(new Text("   -" + vitalsResults.getVitals_results_id()));
        }
        if(vitalsResultsList.size()!= 0){test.getChildren().add(new Text()); }// Add some space between sections

        // Then add Prescriptions
        if(prescriptionsList.size()!= 0){test.getChildren().add(new Text("Questionnaire Results:"));}
        for (Prescriptions prescription : prescriptionsList) {
            test.getChildren().add(new Text("   -" + prescription.getPrescription_id()));
        }
        if(prescriptionsList.size()!= 0){test.getChildren().add(new Text()); }// Add some space between sections
        
        return test;
    }

}
