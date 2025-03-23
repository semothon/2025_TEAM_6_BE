package org.semothon.survey.survey.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_question")
@Data
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;

    // Survey 엔티티 대신 id 참조
    private Long surveyId;

    private String questionText;
    private String questionType;

    @Column(columnDefinition = "TEXT")
    private String questionOptions;
}
