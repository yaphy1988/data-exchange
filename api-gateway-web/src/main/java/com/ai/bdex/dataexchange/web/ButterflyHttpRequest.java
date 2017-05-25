package com.ai.bdex.dataexchange.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ButterflyHttpRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;
	private String requestURI;
	
	public ButterflyHttpRequest(HttpServletRequest request)
	{
	  super(request);
	  this.request = request;
	}
	
	public ButterflyHttpRequest(HttpServletRequest request, String requestURI) {
	  super(request);
	  this.request = request;
	  this.requestURI = requestURI;
	}
	
	public String getServletPath()
	{
	  if (this.requestURI != null) {
	    return this.requestURI;
	  }
	  
	  return this.request.getServletPath();
	}
}

