package com.ai.bdex.dataexchange.busi.oauth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RestController;

import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.ISmsSendRSV;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;

@RestController
@Path("oauth2")
public class TokenController {
	 private final Log logger = LogFactory.getLog(getClass());
	 @DubboConsumer(timeout=3000)
	 private ISmsSendRSV smsSendRSV;

	@Path("accessToken")
	@PUT
	@Produces(MediaType.APPLICATION_JSON+";"+"charset=utf-8")	
	public Map<String,String> getAccessToken()throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		try {
			
		} catch (Exception e) {

		}
		smsSendRSV.sendVerifyCodeByAlibaba("18577810125", "1233");
		return "hello world 我才";
	}
}
