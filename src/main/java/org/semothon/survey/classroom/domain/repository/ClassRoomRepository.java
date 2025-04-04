package org.semothon.survey.classroom.domain.repository;

import org.semothon.survey.classroom.domain.entity.Building;
import org.semothon.survey.classroom.domain.entity.ClassRoom;
import org.semothon.survey.core.enumerate.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
    List<ClassRoom> findAllByClassroomBuilding(Building classroomBuilding);

    @Query("SELECT c FROM ClassRoom c " +
            "WHERE c.classroomId NOT IN (" +
            "    SELECT a.classroomId FROM Availability a " +
            "    WHERE a.availabilityDate = :date " +
            "      AND a.reservationStatus IN :statuses " +
            "      AND a.availabilityStart < :endTime " +
            "      AND :startTime < a.availabilityEnd " +
            ")")
    List<ClassRoom> findAvailableClassrooms(@Param("date") LocalDate date,
                                            @Param("startTime") LocalTime startTime,
                                            @Param("endTime") LocalTime endTime,
                                            @Param("statuses") List<ApplicationStatus> statuses);
}
