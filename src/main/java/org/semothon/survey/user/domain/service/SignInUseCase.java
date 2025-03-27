package org.semothon.survey.user.domain.service;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.user.domain.entity.User;
import org.semothon.survey.user.domain.repository.UserRepository;
import org.semothon.survey.user.exception.UserErrorType;
import org.semothon.survey.user.exception.UserException;
import org.semothon.survey.user.presentation.request.SignInRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SignInUseCase {

    private final UserRepository userRepository;

    public void execute(SignInRequest signInRequest) {
        User user = userRepository.findByUserIdAndUserRole(signInRequest.userId(), signInRequest.userRole())
                .orElseThrow(()-> new UserException(UserErrorType.USER_NOT_FOUND));

        // 비밀번호 검증
        if (!user.getUserPassword().equals(signInRequest.userPassword())) {
            throw new UserException(UserErrorType.INVALID_PASSWORD);
        }
    }
}

