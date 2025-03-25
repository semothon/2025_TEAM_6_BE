package org.semothon.survey.user.domain.repository;

import org.semothon.survey.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    // 추가 메서드가 필요하면 여기에 작성
}