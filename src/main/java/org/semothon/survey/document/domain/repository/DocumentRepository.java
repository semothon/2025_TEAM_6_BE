package org.semothon.survey.document.domain.repository;

import org.semothon.survey.document.domain.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByApplicationId(Long applicationId);
    List<Document> findByReportId(Long reportId);
}
