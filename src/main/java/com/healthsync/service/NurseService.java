package com.healthsync.service;

import com.healthsync.dao.QuestionnaireResultsDao;
import com.healthsync.entities.Questionnaire_Results;

import java.util.Date;

public class NurseService {

    private final QuestionnaireResultsDao questionnaireResultsDao = new QuestionnaireResultsDao();

    public Questionnaire_Results createQuestionnaireResultEntry(String name, Date date, char sex, String administered_by) {
        Questionnaire_Results questionnaire_results = new Questionnaire_Results(
                -1,
                name,
                date,
                sex,
                administered_by
        );

        int questionnaire_id = questionnaireResultsDao.createQuestionnaireResult(questionnaire_results);
        if (questionnaire_id == -1) {
            return null;
        }
        questionnaire_results.setQuestionnaire_id(questionnaire_id);
        System.out.println(questionnaire_results);
        return questionnaire_results;
    }
    
}
