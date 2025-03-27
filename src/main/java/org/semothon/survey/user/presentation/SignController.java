package org.semothon.survey.user.presentation;

import jakarta.validation.Valid;
import org.semothon.survey.core.support.response.ApiResponse;
import org.semothon.survey.user.presentation.request.SignInRequest;
import org.springframework.web.bind.annotation.RequestBody;

public class SignController {

    public ApiResponse<?> signIn(@Valid @RequestBody SignInRequest signInRequest) {

        return ApiResponse.success();
    }
}
