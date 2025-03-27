package org.semothon.survey.user.domain.service;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.user.domain.entity.User;
import org.semothon.survey.user.domain.repository.UserRepository;
import org.semothon.survey.user.exception.UserErrorType;
import org.semothon.survey.user.exception.UserException;
import org.semothon.survey.user.presentation.response.UserInfoResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserInfoService {

    private final UserRepository userRepository;

    public UserInfoResponse execute(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserException(UserErrorType.NOT_EXIST_AVAILABLE_USER));

        return UserInfoResponse.of(user.getUserId(), user.getUserName(), user.getUserRole());
    }
}
