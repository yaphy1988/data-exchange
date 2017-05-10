package com.ai.bdex.dataexchange.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RequestContext
{
  private static ThreadLocal<HttpSession> _httpsession = new ThreadLocal();
  
  private static ThreadLocal<HttpServletRequest> _request = new ThreadLocal();
  
  private static ThreadLocal<HttpServletResponse> _response = new ThreadLocal();
  
  public static String getContextPath()
  {
    return ((HttpServletRequest)_request.get()).getContextPath();
  }
  
  public static HttpServletRequest getRequest()
  {
    return (HttpServletRequest)_request.get();
  }
  
  public static void setRequest(HttpServletRequest request) {
    _request.set(request);
  }
  
  public static HttpServletResponse getResponse() {
    return (HttpServletResponse)_response.get();
  }
  
  public static void setResponse(HttpServletResponse response) {
    _response.set(response);
  }
  
  public static HttpSession getHttpSession() {
    return (HttpSession)_httpsession.get();
  }
  
  public static void setHttpSession(HttpSession session) {
    _httpsession.set(session);
  }
  
  public static void clear() {
    if (_httpsession != null) _httpsession.remove();
    if (_request != null) _request.remove();
    if (_response != null) _response.remove();
  }
}