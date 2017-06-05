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
import com.ai.bdex.dataexchange.apigateway.dubbo.dto.ApiTransationRespDTO;
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
    	ApiTransationRespDTO resultMap=new ApiTransationRespDTO();
    	resultMap.setSerialNo(null);
    	resultMap.setResult(null);

    	try{
//    		accessToken=request.getParameter(APIConstants.AIP_PARAM_ACCESSTOKEN);
    		accessToken=RequestContext.getRequest().getParameter(APIConstants.AIP_PARAM_ACCESSTOKEN);
    		if(!StringUtils.isBlank(accessToken)){
	    		if(null==CacheUtil.getItem(APIConstants.AipToken.AIP_CACHE_ACCESSTOKEN+accessToken)){
	    			AipClientAccesstokenDTO dto=aipClientAccesstokenRSV.getAipClientAccesstokenByKey(accessToken);
	    			if(null==dto){
						resultMap.setRespCode(APIConstants.SystemErrorCode.ERRORCODE_10001);
						resultMap.setRespDesc("invalid accessToken ");
	    			}else{	    				
	    				Timestamp nowTime=new Timestamp(System.currentTimeMillis());
	    				Timestamp expirTime=new Timestamp(dto.getExpiresIn().getTime());
	    				if(nowTime.compareTo(expirTime)==1){
	    					resultMap.setRespCode( APIConstants.SystemErrorCode.ERRORCODE_10003);
	    					resultMap.setRespDesc("accessToken is expired.");
	    				}else{
	    					
	    					return null;
	    				}
	    			}
	    		}else{
	    			return null;
	    		}
    		}else{
				resultMap.setRespCode(APIConstants.SystemErrorCode.ERRORCODE_10015);
				resultMap.setRespDesc("accessToken is null");
    		}
    	}catch(Exception e){
    		logger.error("check token failted:"+accessToken, e);
    		result= "Exception";
			resultMap.setRespCode(APIConstants.SystemErrorCode.ERRORCODE_10014);
			resultMap.setRespDesc("Please contact administrator");
    	}
    	return JSON.toJSONString(resultMap);
    } 	
 	
}
