package com.amazon.ata.customerservice;

import com.amazon.coral.annotation.AwsQueryError;
import com.amazon.coral.annotation.AwsSoap11Error;
import com.amazon.coral.annotation.AwsSoap12Error;
import com.amazon.coral.annotation.BsfError;
import com.amazon.coral.annotation.HttpError;
import com.amazon.coral.annotation.Shape;
import com.amazon.coral.annotation.Wrapper;
import com.amazon.coral.annotation.XmlName;
import com.amazon.coral.annotation.XmlNamespace;
import com.amazon.coral.annotation.*;

@Shape
@AwsSoap11Error(type= AwsErrorType.Sender,code="InvalidParameterException",httpCode=500)
@BsfError(value= BsfErrorType.BadArgs)
@Wrapper(value={WrapperType.INPUT, WrapperType.OUTPUT})
@XmlNamespace(value="http://internal.amazon.com/coral/com.amazon.ata.customerservice/")
@XmlName(value="InvalidParameterException")
@AwsSoap12Error(type= AwsErrorType.Sender,code="InvalidParameterException",httpCode=400)
@HttpError(httpCode=400)
@AwsQueryError(type= AwsQueryErrorType.Sender,code="InvalidParameterException",httpCode=400)
public class InvalidParameterException extends ClientFaultException {

  private static final long serialVersionUID = -1L;

  public InvalidParameterException() {
  }

  public InvalidParameterException(Throwable cause) {
    initCause(cause);
  }

  public InvalidParameterException(String message) {
    super(message);
  }

  public InvalidParameterException(String message, Throwable cause) {
    super(message);
    initCause(cause);
  }
}