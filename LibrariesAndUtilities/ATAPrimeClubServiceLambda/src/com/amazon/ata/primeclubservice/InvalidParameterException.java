package com.amazon.ata.primeclubservice;

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
@AwsSoap11Error(type= AwsErrorType.Sender,httpCode=500,code="InvalidParameterException")
@Wrapper(value={WrapperType.INPUT, WrapperType.OUTPUT})
@AwsSoap12Error(type= AwsErrorType.Sender,httpCode=400,code="InvalidParameterException")
@BsfError(value= BsfErrorType.BadArgs)
@HttpError(httpCode=400)
@AwsQueryError(type= AwsQueryErrorType.Sender,httpCode=400,code="InvalidParameterException")
@XmlName(value="InvalidParameterException")
@XmlNamespace(value="http://internal.amazon.com/coral/com.amazon.ata.primeclubservice/")
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