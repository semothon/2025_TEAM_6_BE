package org.semothon.survey.survey.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_survey")
@Data
@NoArgsConstructor
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyId;

    private Long userId; // User 엔티티가 아닌 ID 참조

    private String surveyTitle;
    private String surveyDescription;
    private String surveyReward;
    private String surveyUrl;

    private LocalDateTime surveyStartAt;
    private LocalDateTime surveyEndAt;
    private Boolean isSurveyActive;
    private LocalDateTime surveyCreatedAt;
}
