package org.semothon.survey.survey.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_response")
@Data
@NoArgsConstructor
public class Responder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long responderId;

    private Long surveyId;

    private String responderNickname;
    private String responderNumber;
    private Boolean isResponderRewarded;
    private String responderSubmittedAt;
}

