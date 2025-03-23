package org.semothon.survey.survey.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_answer",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"responseId", "questionId"})
        })
@Data
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    private Long responseId;
    private Long questionId;

    private String answerText;
}

