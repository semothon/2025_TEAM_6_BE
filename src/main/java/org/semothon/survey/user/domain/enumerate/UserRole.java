package org.semothon.survey.user.domain.enumerate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    USER("사용자"),
    ADMIN("행정실");

    private final String description;
}