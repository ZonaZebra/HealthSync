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

    public VBox getPatientHistory(String patient_ID) {
        VBox test = new VBox();
        List<Physical_Test_Findings> testFindingsList = testFindingsDao.getPhysicalTestFindings(patient_ID);
        List<Questionnaire_Results> questionnaireResultsList = questionnaireResultsDao.getQuestionnaireResultsByPatientId(patient_ID);
        List<Prescriptions> prescriptionsList = prescriptionsDao.getPrescriptions(patient_ID);
        List<Vitals_Results> vitalsResultsList = vitalsResultsDao.getAllVitalsResults(patient_ID);

        test.setStyle("-fx-font-size: 15;");

        // Add Test findings First
        Text testFindingsLabel = new Text("Physical Test Findings:");
        testFindingsLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        if (testFindingsList.size() != 0) {
            test.getChildren().add(testFindingsLabel);
        }
        for (Physical_Test_Findings physical_test_findings : testFindingsList) {
            test.getChildren().add(new Text("   - Test Finding ID: " + physical_test_findings.getPhysical_test_id()));
            test.getChildren().add(new Text("         - Reported Issues: " + physical_test_findings.getIssues()));
            test.getChildren().add(new Text("         - Additional Notes: " + physical_test_findings.getNotes()));
            test.getChildren().add(new Text(""));
        }
        if (testFindingsList.size() != 0) {
            test.getChildren().add(new Text());
        } // Add some space between sections

        // Then add questionnaire results
        Text questionnaireLabel = new Text("Questionnaire Results:");
        questionnaireLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        if (questionnaireResultsList.size() != 0) {
            test.getChildren().add(questionnaireLabel);
        }
        for (Questionnaire_Results questionnaire_results : questionnaireResultsList) {
            test.getChildren().add(new Text("   - Questionnaire ID: " + questionnaire_results.getQuestionnaire_id()));
            test.getChildren().add(new Text("         - Sex: " + questionnaire_results.getSex()));
            test.getChildren().add(new Text("         - Height: " + questionnaire_results.getSex()));
            test.getChildren().add(new Text("         - Reported Issues: " + questionnaire_results.getIssues()));
            test.getChildren().add(new Text(""));
        }
        if (questionnaireResultsList.size() != 0) {
            test.getChildren().add(new Text());
        } // Add some space between sections

        // Then add Vitals results
        Text vitalsResultsLabel = new Text("Vitals Results:");
        vitalsResultsLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        if (vitalsResultsList.size() != 0) {
            test.getChildren().add(vitalsResultsLabel);
        }
        for (Vitals_Results vitalsResults : vitalsResultsList) {
            test.getChildren().add(new Text("   - Vitals ID: " + vitalsResults.getVitals_results_id()));
            test.getChildren().add(new Text("         - Height: " + vitalsResults.getHeight()+ " in"));
            test.getChildren().add(new Text("         - Weight: " + vitalsResults.getWeight() + " lbs"));
            test.getChildren().add(new Text("         - Blood Pressure: " + vitalsResults.getSystolic_bp()+"/"+vitalsResults.getDiastolic_bp() + " mmHg"));
            test.getChildren().add(new Text("         - Resting Pulse Rate: " + vitalsResults.getResting_pulse()+" bpm"));
            test.getChildren().add(new Text("         - Body Temperature: " + vitalsResults.getTemperature() + "° F"));
            test.getChildren().add(new Text(""));
        }
        if (vitalsResultsList.size() != 0) {
            test.getChildren().add(new Text());
        }// Add some space between sections

        // Then add Prescriptions
        Text prescriptionsLabel = new Text("Prescriptions:");
        prescriptionsLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        if (prescriptionsList.size() != 0) {
            test.getChildren().add(prescriptionsLabel);
        }
        for (Prescriptions prescription : prescriptionsList) {
            test.getChildren().add(new Text("   - Prescription ID: " + prescription.getPrescription_id()));
            test.getChildren().add(new Text("         - Prescription Name: " + prescription.getProduct()));
            test.getChildren().add(new Text("         - Dosage (mg): " + prescription.getDosage_in_mg()));
            test.getChildren().add(new Text("         - Frequency (hours): " + prescription.getFrequency()));
            test.getChildren().add(new Text("         - Additional Instructions: " + prescription.getInstructions()));
            test.getChildren().add(new Text(""));
        }
        if (prescriptionsList.size() != 0) {
            test.getChildren().add(new Text());
        }// Add some space between sections

        return test;
    }

}
