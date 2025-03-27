package org.semothon.survey.classroom.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Building {

    SoftwareConvergence("전자정보대학"),
    Engineering("공과대학"),
    ArtAndDesign("예술디자인대학");

    private final String description;
}