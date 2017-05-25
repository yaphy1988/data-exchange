package com.ai.bdex.dataexchange.busi.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.spi.http.HttpContext;

import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.utils.ApiWebHttpUtil;
import com.ai.paas.util.CacheUtil;

/**
 * @author yafei
 * @since 2017/4/3
 */

//@Controller
//@RequestMapping(value = "/demo")
@Path("demo")
public class DemoRestController {

	
    @Path("test")
    @GET
//    @Produces(MediaType.TEXT_PLAIN_VALUE)
//    @RequestMapping(value = "/test",method = {RequestMethod.GET},produces={MediaType.TEXT_PLAIN_VALUE})
	public @ResponseBody String getTest(HttpServletRequest request){
    	String x=request.getParameter("test");
        Object testValue = CacheUtil.getItem("ABC");
        return x;
    }
    @Path("test2")
    @GET
//    @Produces(MediaType.TEXT_PLAIN_VALUE)
//    @RequestMapping(value = "/test",method = {RequestMethod.GET},produces={MediaType.TEXT_PLAIN_VALUE})
	public @ResponseBody String getTest2(){ 	
        Object testValue = CacheUtil.getItem("ABC");
        return "TEST";
    }
    @Path("test3")
    @GET
//    @Produces(MediaType.TEXT_PLAIN_VALUE)
//    @RequestMapping(value = "/test",method = {RequestMethod.GET},produces={MediaType.TEXT_PLAIN_VALUE})
	public @ResponseBody String getTest3(@QueryParam("test") String test){ 	
        Object testValue = CacheUtil.getItem("ABC");
        return test;
    }
    @Path("test4")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces(MediaType.TEXT_PLAIN_VALUE)
//    @RequestMapping(value = "/test",method = {RequestMethod.GET},produces={MediaType.TEXT_PLAIN_VALUE})
	public @ResponseBody String getTest4(@FormParam("test") String test){ 	
        Object testValue = CacheUtil.getItem("ABC");
        return test;
    }
    @Path("test5")
    @POST
//    @Produces(MediaType.TEXT_PLAIN_VALUE)
//    @RequestMapping(value = "/test",method = {RequestMethod.GET},produces={MediaType.TEXT_PLAIN_VALUE})
	public @ResponseBody String getTest5(@QueryParam("test") String test){ 	
        Object testValue = CacheUtil.getItem("ABC");
        return test;
    }
    @Path("test6")
    @POST
//    @Produces(MediaType.TEXT_PLAIN_VALUE)
//    @RequestMapping(value = "/test",method = {RequestMethod.GET},produces={MediaType.TEXT_PLAIN_VALUE})
	public @ResponseBody String getTest6(@Context HttpServletRequest request){ 	
    	Map<String, String> map=ApiWebHttpUtil.getParamsMap(request);
        Object testValue = CacheUtil.getItem("ABC");
        map.get("");
        return request.getParameter("test");
    }
    @Path("test7")
    @POST
//    @Produces(MediaType.TEXT_PLAIN_VALUE)
//    @RequestMapping(value = "/test",method = {RequestMethod.GET},produces={MediaType.TEXT_PLAIN_VALUE})
	public @ResponseBody String getTest7(@Context HttpContext request){ 	
    	request.getAttribute("test");
        Object testValue = CacheUtil.getItem("ABC");
        return "111";
    }
    @Path("test8")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.TEXT_PLAIN_VALUE)
//    @RequestMapping(value = "/test",method = {RequestMethod.GET},produces={MediaType.TEXT_PLAIN_VALUE})
	public @ResponseBody String getTest8(TestBody body){ 	
    	System.out.println("---------------------------");
        Object testValue = CacheUtil.getItem("ABC");
        return body.getResp()+":"+body.getMess();
    }
}

class TestBody{
	private String resp;
	private String mess;
	
	public String getResp() {
		return resp;
	}
	public void setResp(String resp) {
		this.resp = resp;
	}
	public String getMess() {
		return mess;
	}
	public void setMess(String mess) {
		this.mess = mess;
	}
	
}
