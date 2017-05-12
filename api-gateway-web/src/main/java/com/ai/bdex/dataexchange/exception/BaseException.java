package com.ai.bdex.dataexchange.exception;

public abstract class BaseException
extends Exception
{
private String errorCode;

private String errorMsg;



public void setErrorCode(String errorCode) { this.errorCode = errorCode; }

public void setErrorMsg(String errorMsg) {
  this.errorMsg = errorMsg;
}

public String getErrorCode() { return this.errorCode; }

public String getErrorMsg() {
  return this.errorMsg;
}
}

