package org.semothon.survey.classroom.domain.repository;

import org.semothon.survey.classroom.domain.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
}
