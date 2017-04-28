package com.ai.bdex.dataexchange.busi.demo.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.http.MediaType;

import com.ai.paas.util.CacheUtil;

/**
 * @author yafei
 * @since 2017/4/3
 */

//@RequestMapping(value = "demo")
@Path("demo")
public class DemoRestController {
    //@RequestMapping(value = "test",method = {RequestMethod.GET},produces={MediaType.TEXT_PLAIN_VALUE})
	
    @Path("test")
    @GET
    @Produces(MediaType.TEXT_PLAIN_VALUE)
	public String getTest(){
        Object testValue = CacheUtil.getItem("ABC");
        return "test";
    }
}
