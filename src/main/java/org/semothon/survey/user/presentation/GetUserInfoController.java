package org.semothon.survey.user.presentation;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.core.support.response.ApiResponse;
import org.semothon.survey.user.domain.entity.UserRole;
import org.semothon.survey.user.domain.service.GetUserInfoService;
import org.semothon.survey.user.presentation.response.UserInfoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetUserInfoController {

    private final GetUserInfoService getUserInfoService;

    @GetMapping("api/user")
    public ApiResponse<UserInfoResponse> getUserInfo(@RequestParam String userId, @RequestParam UserRole userRole) {
        UserInfoResponse userInfoResponse = getUserInfoService.execute(userId, userRole);
        return ApiResponse.success(userInfoResponse);
    }
}
