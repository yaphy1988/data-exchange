package com.ai.bdex.dataexchange.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

public class DispatcherServlet extends org.springframework.web.servlet.DispatcherServlet
{
	private static final long serialVersionUID = -1824150500946318114L;
	
	public DispatcherServlet(WebApplicationContext webApplicationContext) {
		super(webApplicationContext);
		setDispatchOptionsRequest(true);
	}
	
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response)
	  throws Exception
	{
	  HttpServletRequest _request = new ButterflyHttpRequest(request);
	  RequestContext.setRequest(_request);
	  RequestContext.setResponse(response);
	  RequestContext.setHttpSession(_request.getSession());
	  super.doDispatch(request, response);
	}
	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
	  try
	  {
	    super.doService(request, response);
	  } catch (Exception e) {
	    throw e;
	  } finally {
	    RequestContext.clear();
	  }
	}
	
	protected void initStrategies(ApplicationContext context)
	{
	  super.initStrategies(context);
	}
	
	protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
	  throws Exception
	{
	  super.render(mv, request, response);
	}
}
