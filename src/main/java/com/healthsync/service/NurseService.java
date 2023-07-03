package com.healthsync.service;

import com.healthsync.dao.QuestionnaireResultsDao;
import com.healthsync.dao.VitalsResultsDao;
import com.healthsync.entities.Questionnaire_Results;
import com.healthsync.entities.Vitals_Results;

public class NurseService {

    private final QuestionnaireResultsDao questionnaireResultsDao = new QuestionnaireResultsDao();
    private final VitalsResultsDao vitalsResultsDao = new VitalsResultsDao();

    public Questionnaire_Results createQuestionnaireResultEntry(String name, String date, char sex, String administered_by, String patient_ID) {
        Questionnaire_Results questionnaire_results = new Questionnaire_Results(
                -1,
                name,
                date,
                sex,
                administered_by,
                patient_ID
        );

        int questionnaire_id = questionnaireResultsDao.createQuestionnaireResult(questionnaire_results);
        if (questionnaire_id == -1) {
            return null;
        }
        questionnaire_results.setQuestionnaire_id(questionnaire_id);
        System.out.println(questionnaire_results);
        return questionnaire_results;
    }

    public Vitals_Results createVitalsResults(String height, String weight, String systolic_bp, String diastolic_bp,
                                              String resting_pulse, String temperature, String patient_id) {
        Vitals_Results vitals_results = new Vitals_Results(
                -1,
                height,
                weight,
                systolic_bp,
                diastolic_bp,
                resting_pulse,
                temperature,
                patient_id
        );

        int vitals_results_id = vitalsResultsDao.createVitalsResults(vitals_results);
        if (vitals_results_id == -1) {
            return null;
        }
        vitals_results.setVitals_results_id(vitals_results_id);
        System.out.println(vitals_results);
        return vitals_results;
    }
}
