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
        Text testFindingsLabel = new Text("Physical Test Findings:");
        testFindingsLabel.setStyle("-fx-font-weight: bold;");
        if(testFindingsList.size()!= 0){test.getChildren().add(testFindingsLabel);}
        for (Physical_Test_Findings physical_test_findings : testFindingsList) {
            test.getChildren().add(new Text("   - Test Finding ID: "+ physical_test_findings.getPhysical_test_id()));
            test.getChildren().add(new Text("         - Reported Issues: "+ physical_test_findings.getIssues()));
            test.getChildren().add(new Text("         - Additional Notes: "+ physical_test_findings.getNotes()));
        }
        if(testFindingsList.size()!= 0){test.getChildren().add(new Text()); } // Add some space between sections

        // Then add questionnaire results
        Text questionnaireLabel = new Text("Questionnaire Results:");
        questionnaireLabel.setStyle("-fx-font-weight: bold;");
        if(questionnaireResultsList.size()!= 0){test.getChildren().add(questionnaireLabel);}
        for (Questionnaire_Results questionnaire_results : questionnaireResultsList) {
            test.getChildren().add(new Text("   -" + questionnaire_results.getQuestionnaire_id()));
        }
        if(questionnaireResultsList.size()!= 0){test.getChildren().add(new Text()); } // Add some space between sections

        // Then add Vitals results
        Text vitalsResultsLabel = new Text("Vitals Results:");
        vitalsResultsLabel.setStyle("-fx-font-weight: bold;");
        if(vitalsResultsList.size()!= 0){test.getChildren().add(vitalsResultsLabel);}
        for (Vitals_Results vitalsResults : vitalsResultsList) {
            test.getChildren().add(new Text("   - Vitals ID: " + vitalsResults.getVitals_results_id()));
            test.getChildren().add(new Text("         - Height: " + vitalsResults.getHeight()));
            test.getChildren().add(new Text("         - Weight: " + vitalsResults.getWeight()));
            test.getChildren().add(new Text("         - Systolic Blood Pressure: " + vitalsResults.getSystolic_bp()));
            test.getChildren().add(new Text("         - Diastolic Blood Pressure: " + vitalsResults.getDiastolic_bp()));
            test.getChildren().add(new Text("         - Resting Pulse Rate: " + vitalsResults.getResting_pulse()));
            test.getChildren().add(new Text("         - Body Temperature: " + vitalsResults.getTemperature()));
        }
        if(vitalsResultsList.size()!= 0){test.getChildren().add(new Text()); }// Add some space between sections

        // Then add Prescriptions
        Text prescriptionsLabel = new Text("Prescriptions:");
        prescriptionsLabel.setStyle("-fx-font-weight: bold;");
        if(prescriptionsList.size()!= 0){test.getChildren().add(prescriptionsLabel);}
        for (Prescriptions prescription : prescriptionsList) {
            test.getChildren().add(new Text("   - Prescription ID: " + prescription.getPrescription_id()));
            test.getChildren().add(new Text("         - Prescription Name: "+ prescription.getProduct()));
            test.getChildren().add(new Text("         - Dosage (mg): "+ prescription.getDosage_in_mg()));
            test.getChildren().add(new Text("         - Frequency (hours): "+ prescription.getFrequency()));
            test.getChildren().add(new Text("         - Additional Instructions: "+ prescription.getInstructions()));
        }
        if(prescriptionsList.size()!= 0){test.getChildren().add(new Text()); }// Add some space between sections

        return test;
    }

}
