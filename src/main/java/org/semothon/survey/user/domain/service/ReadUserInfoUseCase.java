package org.semothon.survey.user.domain.service;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.user.domain.entity.User;
import org.semothon.survey.user.domain.entity.UserRole;
import org.semothon.survey.user.domain.repository.UserRepository;
import org.semothon.survey.user.exception.UserErrorType;
import org.semothon.survey.user.exception.UserException;
import org.semothon.survey.user.presentation.response.UserInfoResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadUserInfoUseCase {

    private final UserRepository userRepository;

    public UserInfoResponse execute(String userId, UserRole userRole) {
        User user = userRepository.findByUserIdAndUserRole(userId, userRole)
                .orElseThrow(()-> new UserException(UserErrorType.NOT_EXIST_AVAILABLE_USER));

        return UserInfoResponse.of(user.getUserId(), user.getUserName(), user.getUserRole(), user.getUserNumber());
    }
}
