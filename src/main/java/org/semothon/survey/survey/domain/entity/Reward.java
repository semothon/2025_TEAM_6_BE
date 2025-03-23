package org.semothon.survey.survey.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_reward",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"surveyId", "responseId"})
        })
@Data
@NoArgsConstructor
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rewardId;

    private Long surveyId;
    private Long responseId;

    private String rewardGiftUrl;
    private String rewardStatus;
    private String rewardSentAt;
}

