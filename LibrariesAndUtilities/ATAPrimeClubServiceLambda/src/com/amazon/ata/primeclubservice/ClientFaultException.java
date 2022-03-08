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
@AwsSoap11Error(type= AwsErrorType.Sender,httpCode=500,code="ClientFaultException")
@Wrapper(value={WrapperType.INPUT, WrapperType.OUTPUT})
@AwsSoap12Error(type= AwsErrorType.Sender,httpCode=400,code="ClientFaultException")
@BsfError(value= BsfErrorType.BadArgs)
@HttpError(httpCode=400)
@AwsQueryError(type= AwsQueryErrorType.Sender,httpCode=400,code="ClientFaultException")
@XmlName(value="ClientFaultException")
@XmlNamespace(value="http://internal.amazon.com/coral/com.amazon.ata.primeclubservice/")
public abstract class ClientFaultException extends RuntimeException {

  private static final long serialVersionUID = -1L;

  public ClientFaultException() {
  }

  public ClientFaultException(Throwable cause) {
    initCause(cause);
  }

  public ClientFaultException(String message) {
    super(message);
  }

  public ClientFaultException(String message, Throwable cause) {
    super(message);
    initCause(cause);
  }

  @Override
  public String getMessage() { 
    return super.getMessage();
  }
}