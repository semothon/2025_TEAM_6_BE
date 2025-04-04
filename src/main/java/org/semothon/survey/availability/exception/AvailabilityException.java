package org.semothon.survey.availability.exception;

import org.semothon.survey.core.support.error.CoreException;
import org.semothon.survey.core.support.error.ErrorType;

public class AvailabilityException extends CoreException {
  public AvailabilityException(ErrorType errorType) {super(errorType);}
}
