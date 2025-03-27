package org.semothon.survey.classroom.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Building {

    Electrical("전자정보대학");

    private final String description;
}