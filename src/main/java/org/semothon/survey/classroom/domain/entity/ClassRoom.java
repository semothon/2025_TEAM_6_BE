package org.semothon.survey.classroom.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.semothon.survey.core.converter.StringListConverter;

import java.util.ArrayList;
import java.util.List;

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

    private String classroomCautions; // 사용 시 주의 사항

    // 이미지 URL 리스트를 단일 문자열로 저장, 예: "url1,url2,url3"
    @Column(length = 1000)
    @Convert(converter = StringListConverter.class)
    private List<String> classroomImages = new ArrayList<>();
}
