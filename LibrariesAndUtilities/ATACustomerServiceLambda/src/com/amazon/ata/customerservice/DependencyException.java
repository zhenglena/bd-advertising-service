package com.amazon.ata.customerservice;

import com.amazon.coral.annotation.AwsQueryError;
import com.amazon.coral.annotation.AwsSoap11Error;
import com.amazon.coral.annotation.AwsSoap12Error;
import com.amazon.coral.annotation.BsfError;
import com.amazon.coral.annotation.Documentation;
import com.amazon.coral.annotation.HttpError;
import com.amazon.coral.annotation.Shape;
import com.amazon.coral.annotation.Wrapper;
import com.amazon.coral.annotation.XmlName;
import com.amazon.coral.annotation.XmlNamespace;
import com.amazon.coral.annotation.*;

/**
 * This exception is thrown on a database (or other dependency) error
 */
@Documentation("This exception is thrown on a database (or other dependency) error")
@Shape
@AwsSoap11Error(type= AwsErrorType.Receiver,code="DependencyException",httpCode=500)
@BsfError(value= BsfErrorType.ServiceFailure)
@Wrapper(value={WrapperType.INPUT, WrapperType.OUTPUT})
@XmlNamespace(value="http://internal.amazon.com/coral/com.amazon.ata.customerservice/")
@XmlName(value="DependencyException")
@AwsSoap12Error(type= AwsErrorType.Receiver,code="DependencyException",httpCode=500)
@HttpError(httpCode=500)
@AwsQueryError(type= AwsQueryErrorType.Receiver,code="DependencyException",httpCode=500)
public class DependencyException extends RuntimeException {

  private static final long serialVersionUID = -1L;

  public DependencyException() {
  }

  public DependencyException(Throwable cause) {
    initCause(cause);
  }

  public DependencyException(String message) {
    super(message);
  }

  public DependencyException(String message, Throwable cause) {
    super(message);
    initCause(cause);
  }

  @Override
  public String getMessage() { 
    return super.getMessage();
  }
}