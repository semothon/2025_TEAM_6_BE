package org.semothon.survey.survey.domain.repository;

import org.semothon.survey.survey.domain.entity.Responder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponderRepository extends JpaRepository<Responder, Long> {
    List<Responder> findBySurveyId(Long surveyId);
}