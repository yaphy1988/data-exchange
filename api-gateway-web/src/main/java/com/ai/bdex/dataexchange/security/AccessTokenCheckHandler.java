package com.ai.bdex.dataexchange.security;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.stereotype.Component;

import com.ai.bdex.dataexchange.annotation.Security;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants;
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.AipClientAccesstokenDTO;
import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.IAipClientAccesstokenRSV;
import com.ai.bdex.dataexchange.web.RequestContext;
import com.ai.paas.util.CacheUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.fastjson.JSON;

@Component("accessTokenCheckHandler")
public class AccessTokenCheckHandler implements PermissionCheckHandler{
	private final Log logger = LogFactory.getLog(getClass());
 	@DubboConsumer
 	private IAipClientAccesstokenRSV aipClientAccesstokenRSV;

	@Override
	public String isPermission(Security paramSecurity) throws Exception {		
		return checkAccessToken();
	}
    private String checkAccessToken(){
    	String accessToken=null;
    	String result=null;
    	Map<String,Object> returnMap=new HashMap<String,Object>();
		Map<String,Object> resultMap=new HashMap<String,Object>();
//		Map<String,String> map=new HashMap<String,String>();		
		
		
    	try{
//    		accessToken=request.getParameter(APIConstants.AIP_PARAM_ACCESSTOKEN);
    		accessToken=RequestContext.getRequest().getParameter(APIConstants.AIP_PARAM_ACCESSTOKEN);
    		if(!StringUtils.isBlank(accessToken)){
	    		if(null==CacheUtil.getItem(APIConstants.AipToken.AIP_CACHE_ACCESSTOKEN+accessToken)){
	    			AipClientAccesstokenDTO dto=aipClientAccesstokenRSV.getAipClientAccesstokenByKey(accessToken);
	    			if(null==dto){
						resultMap.put("resp_code", APIConstants.SystemErrorCode.ERRORCODE_10001);
						resultMap.put("resp_desc", "invalid accessToken ");
	    			}else{	    				
	    				Timestamp nowTime=new Timestamp(System.currentTimeMillis());
	    				Timestamp expirTime=new Timestamp(dto.getExpiresIn().getTime());
	    				if(nowTime.compareTo(expirTime)==1){
	    					resultMap.put("resp_code", APIConstants.SystemErrorCode.ERRORCODE_10003);
	    					resultMap.put("resp_desc", "accessToken is expired.");
	    				}else{
	    					
	    					return null;
	    				}
	    			}
	    		}else{
	    			return null;
	    		}
    		}else{
				resultMap.put("resp_code", APIConstants.SystemErrorCode.ERRORCODE_10015);
				resultMap.put("resp_desc", "accessToken is null");
    		}
    	}catch(Exception e){
    		logger.error("check token failted:"+accessToken, e);
    		result= "Exception";
			resultMap.put("resp_code", APIConstants.SystemErrorCode.ERRORCODE_10014);
			resultMap.put("resp_desc", "Please contact administrator");
    	}
    	returnMap.put("header", resultMap);
    	returnMap.put("body", null);
    	return JSON.toJSONString(returnMap);
    } 	
 	
}
