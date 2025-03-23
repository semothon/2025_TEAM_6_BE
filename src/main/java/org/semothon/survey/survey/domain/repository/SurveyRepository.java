package org.semothon.survey.survey.domain.repository;

import org.semothon.survey.survey.domain.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    List<Survey> findBySurveyActiveTrue();
}
