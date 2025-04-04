package org.semothon.survey.user.presentation.response;

import org.semothon.survey.user.domain.entity.UserRole;

public record UserInfoResponse (

        String userId,

        String userName,

        UserRole userRole,

        String userNumber
){
    public static UserInfoResponse of(String userId, String userName, UserRole userRole, String userNumber) {
        return new UserInfoResponse(userId, userName, userRole, userNumber);
    }
}
