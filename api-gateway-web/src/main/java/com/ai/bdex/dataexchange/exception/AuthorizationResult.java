package com.ai.bdex.dataexchange.exception;

public class AuthorizationResult {
	  private String message;
	  
	  public AuthorizationResult() { this.result = ResultEnum.OK; }
	  
	  private ResultEnum result;
	  private String redirectPage;
	  public void setRedirectPage(String redirectPage) { this.redirectPage = redirectPage; }
	  
	  public ResultEnum getResult()
	  {
	    return this.result;
	  }
	  
	  public void setResult(ResultEnum result) {
	    this.result = result;
	  }
	  
	  public String getRedirectPage() {
	    return this.redirectPage;
	  }
	  
	  public String getMessage() {
	    return this.message;
	  }
	  
	  public void setMessage(String message) {
	    this.message = message;
	  }
	  
	  public boolean isOk() {
	    return ResultEnum.OK.equals(this.result);
	  }
	  
	  public static enum ResultEnum {
	    OK,  NOT_LOGIN,  NOT_PERMIT,  EXPIRED,  METHOD_NEED_OAUTH;
	    
	    private ResultEnum() {}
	  }
	}
