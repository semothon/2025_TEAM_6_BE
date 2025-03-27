package org.semothon.survey.user.exception;

import org.semothon.survey.core.support.error.CoreException;
import org.semothon.survey.core.support.error.ErrorType;

public class UserException extends CoreException {

  public UserException(ErrorType errorType) {super(errorType);}
}
