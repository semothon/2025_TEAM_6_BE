package org.semothon.survey.classroom.exception;

import org.semothon.survey.core.support.error.CoreException;
import org.semothon.survey.core.support.error.ErrorType;

public class ClassRoomException extends CoreException {
  public ClassRoomException(ErrorType errorType) {super(errorType);}
}
