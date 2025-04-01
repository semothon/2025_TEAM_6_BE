package org.semothon.survey.report.exception;

import org.semothon.survey.core.support.error.CoreException;
import org.semothon.survey.core.support.error.ErrorType;

public class ReportException extends CoreException {
  public ReportException(ErrorType errorType) {super(errorType);}
}
