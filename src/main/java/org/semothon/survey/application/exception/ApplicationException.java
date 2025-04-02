package org.semothon.survey.application.exception;

import org.semothon.survey.core.support.error.CoreException;
import org.semothon.survey.core.support.error.ErrorType;

public class ApplicationException extends CoreException {
  public ApplicationException(ErrorType errorType) {super(errorType);}
}
