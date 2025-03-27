package org.semothon.survey.user.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.semothon.survey.core.support.response.ApiResponse;
import org.semothon.survey.user.domain.service.SignInUseCase;
import org.semothon.survey.user.presentation.request.SignInRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final SignInUseCase signInUseCase;

    @PostMapping("api/user")
    public ApiResponse<?> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        signInUseCase.execute(signInRequest);
        return ApiResponse.success();
    }
}
