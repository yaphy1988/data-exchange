package com.ai.bdex.dataexchange.exception;

public class AuthorException
extends BaseException
{
private AuthorizationResult result;

public AuthorException(AuthorizationResult result)
{
  super();
  this.result = result;
}

public AuthorizationResult getResult() {
  return this.result;
}

public void setResult(AuthorizationResult result) {
  this.result = result;
}
}

