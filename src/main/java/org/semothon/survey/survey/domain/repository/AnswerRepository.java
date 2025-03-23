package org.semothon.survey.survey.domain.repository;

import org.semothon.survey.survey.domain.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByResponseId(Long responseId);
    Optional<Answer> findByResponseIdAndQuestionId(Long responseId, Long questionId);
}
