package org.semothon.survey.document.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.semothon.survey.document.domain.enumerate.DocumentType;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_document")
@Data
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;

    private Long reportId;       // FK nullable(Report.reportId)
    private Long applicationId;  // FK nullable(Application.applicationId)

    @Enumerated(EnumType.STRING)
    private DocumentType documentType; // APPLICATION_FORM, REPORT_FORM
    private String documentPath;
    private LocalDateTime documentUploadedAt;
}
