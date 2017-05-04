package com.ai.bdex.dataexchange.busi.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.paas.util.CacheUtil;

/**
 * @author yafei
 * @since 2017/4/3
 */

@Controller
@RequestMapping(value = "/demo")
//@Path("demo")
public class DemoRestController {

	
//    @Path("test")
//    @GET
//    @Produces(MediaType.TEXT_PLAIN_VALUE)
    @RequestMapping(value = "/test",method = {RequestMethod.GET},produces={MediaType.TEXT_PLAIN_VALUE})
	public @ResponseBody String getTest(){
        Object testValue = CacheUtil.getItem("ABC");
        return "test";
    }
}
