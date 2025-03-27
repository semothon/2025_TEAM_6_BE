package org.semothon.survey.user.domain.repository;

import org.semothon.survey.user.domain.entity.User;
import org.semothon.survey.user.domain.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserIdAndUserRole(String userId, UserRole userRole);
}