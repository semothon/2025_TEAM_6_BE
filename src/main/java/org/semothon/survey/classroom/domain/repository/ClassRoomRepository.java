package org.semothon.survey.classroom.domain.repository;

import org.semothon.survey.classroom.domain.entity.Building;
import org.semothon.survey.classroom.domain.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
    List<ClassRoom> findAllByClassroomBuilding(Building classroomBuilding);
}
