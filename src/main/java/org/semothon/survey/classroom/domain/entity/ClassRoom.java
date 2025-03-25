package org.semothon.survey.classroom.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_classroom")
@Data
@NoArgsConstructor
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classroomId;

    private String classroomBuilding;
    private Integer classroomNumber;
    private Integer classroomCapacity;
}
