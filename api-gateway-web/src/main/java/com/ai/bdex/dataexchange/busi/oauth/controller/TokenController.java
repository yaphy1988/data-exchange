package com.ai.bdex.dataexchange.busi.oauth.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientAccesstokenDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientLoginLogDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientRefreshtokenDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipClientAccesstokenRSV;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipClientInfoRSV;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipClientLoginLogRSV;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipClientRefreshtokenRSV;
import com.ai.bdex.dataexchange.annotation.Security;
import com.ai.bdex.dataexchange.busi.model.AccessTokenSign;
import com.ai.bdex.dataexchange.busi.util.SignUtil;
import com.ai.bdex.dataexchange.constants.APIConstants;
import com.ai.bdex.dataexchange.constants.APIConstants.AipToken;
import com.ai.bdex.dataexchange.security.ServiceCheckChain;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.util.CacheUtil;
import com.ai.paas.utils.DateUtil;
import com.ai.paas.utils.InetTool;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.fastjson.JSON;

@Controller
//@Path("oauth2")
@RequestMapping("/oauth2")
public class TokenController {
	 private final Log logger = LogFactory.getLog(getClass());
//	 @DubboConsumer(timeout=3000)
//	 private ISmsSendRSV smsSendRSV;
	 @DubboConsumer(timeout=3000)
	 private IAipClientInfoRSV aipClientInfoRSV;
	 @DubboConsumer(timeout=3000)
	 private IAipClientAccesstokenRSV aipClientAccesstokenRSV;
	 @DubboConsumer(timeout=3000)
	 private IAipClientRefreshtokenRSV aipClientRefreshtokenRSV;
	 @DubboConsumer(timeout=3000)
	 private IAipClientLoginLogRSV aipClientLoginLogRSV;
	 

	 /**
	  * 获取accesstoken
	  * @param request
	  * @return
	  * @throws Exception
	  */
//	@Path("accessToken")
//	@POST
//	@Produces(MediaType.APPLICATION_JSON+";"+"charset=utf-8")	
	@RequestMapping(value="/accessToken",produces={MediaType.APPLICATION_JSON_UTF8_VALUE},method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String getAccessToken(HttpServletRequest request)throws Exception{
		Map<String,Object> resultMap=new HashMap<String,Object>();
		Map<String,String> map=new HashMap<String,String>();		
		resultMap.put("result", map);
		
		String clientId=null;
		Timestamp requestTime=new Timestamp(System.currentTimeMillis());
		String refreshToken=null;
		Timestamp refreshExpireTime=null;
		try {
			String grantType=request.getParameter(APIConstants.AIP_PARAM_GRANT_TYPE);
			if("access_token".equals(grantType)){
				clientId=request.getParameter(APIConstants.AIP_PARAM_CLIENT_ID);
				String sign=request.getParameter(APIConstants.AIP_PARAM_SIGN);
				String timestamp=request.getParameter(APIConstants.AIP_PARAM_TIMESTAMP);
				String scope=request.getParameter(APIConstants.AIP_PARAM_SCOPE);
				if(!StringUtils.isBlank(sign)&&!StringUtils.isBlank(clientId)){
					AipClientInfoDTO  dto=aipClientInfoRSV.getAipClientInfoByKey(clientId);
					if(null!=dto){
						AccessTokenSign as=new AccessTokenSign();
						ObjectCopyUtil.copyObjValue(dto, as, null, false);
						as.setGrantType(grantType);
						as.setScope(scope);
						as.setTimestamp(timestamp);
						String newSign=SignUtil.getAccessTokenSign(as);
						if(sign.equals(newSign)){
							Timestamp nowTime=new Timestamp(System.currentTimeMillis());
							refreshExpireTime=new Timestamp(System.currentTimeMillis()+AipToken.AIP_EXPIRE_REFRESHTOKEN);
							Timestamp accessExpireTime=new Timestamp(System.currentTimeMillis()+AipToken.AIP_EXPIRE_ACCESSTOKEN);
							//生成refresh_token
							refreshToken=SignUtil.createRefreshToken(clientId,nowTime,refreshExpireTime);	
							//生成accesstoken
							String accessToken=SignUtil.createAccessToken(refreshToken, clientId,nowTime,accessExpireTime);
							
							//保存refresh_token，accesstoken
							Timestamp inTime=new Timestamp(System.currentTimeMillis());
							AipClientAccesstokenDTO accDto=new AipClientAccesstokenDTO();
							accDto.setAccessToken(accessToken);
							accDto.setClientId(clientId);
							accDto.setCreateTime(inTime);
							accDto.setExpiresIn(accessExpireTime);
							accDto.setRefreshToken(refreshToken);
							aipClientAccesstokenRSV.insertAipClientAccesstoken(accDto);
							AipClientRefreshtokenDTO refDTO=new AipClientRefreshtokenDTO();
							refDTO.setClientId(clientId);
							refDTO.setCreateTime(inTime);
							refDTO.setExpiresIn(refreshExpireTime);
							refDTO.setRefreshToken(refreshToken);
							refDTO.setUpdateTime(inTime);
							aipClientRefreshtokenRSV.insertAipClientRefreshtoken(refDTO);
							//进行缓存
							//CacheUtil.addItem(AipToken.AIP_CACHE_REFRESHTOKEN+refreshToken, refreshToken);
							CacheUtil.addItem(AipToken.AIP_CACHE_ACCESSTOKEN+accessToken, accessToken,(int)(AipToken.AIP_EXPIRE_ACCESSTOKEN/1000));			
							map.put("uid", UUID.randomUUID().toString().replace("-", ""));
							map.put("access_token", accessToken);
							map.put("refresh_token",refreshToken);
							map.put("time",DateUtil.getDateString(nowTime, DateUtil.DATETIME_FORMAT) );
							map.put("expires_in",DateUtil.getDateString(accessExpireTime,AipToken.AIP_EXPIRE_FORMAT_ACCESSTOKEN));
							map.put("refresh_token_expires",DateUtil.getDateString(refreshExpireTime,AipToken.AIP_EXPIRE_FORMAT_REFRESHTOKEN));
							
							resultMap.put("code", APIConstants.SystemErrorCode.CODE_00000);
							resultMap.put("code_desc", "success");
						}else{
							resultMap.put("code", APIConstants.SystemErrorCode.ERRORCODE_10016);
							resultMap.put("code_desc", "sign Matched failure ");
						}
					}else{
						resultMap.put("code", APIConstants.SystemErrorCode.ERRORCODE_10004);
						resultMap.put("code_desc", "invalid client_id ");
					}
				}else{
					resultMap.put("code", APIConstants.SystemErrorCode.ERRORCODE_10015);
					resultMap.put("code_desc", "sign or client_id is null");
				}
			}else{
				resultMap.put("code", APIConstants.SystemErrorCode.ERRORCODE_10010);
				resultMap.put("code_desc", "invalid grantType");
			}
		} catch (Exception e) {
			logger.error("get accesstoken failted", e);
			resultMap.put("code", APIConstants.SystemErrorCode.ERRORCODE_10014);
			resultMap.put("code_desc", "Please contact administrator");
			resultMap.put("result", new HashMap<String,String>());
		}finally{
			//入库T_CLIENT_LOGIN_LOG
			Timestamp responseTime=new Timestamp(System.currentTimeMillis());
			try{
				AipClientLoginLogDTO logDto=new AipClientLoginLogDTO();
				logDto.setClientId(clientId);
				logDto.setInIp(InetTool.getClientAddr(request));
				logDto.setInTime(requestTime);
				logDto.setRefreshToken(refreshToken);
				logDto.setRefreshTokenExpires(refreshExpireTime);
				logDto.setUpdateTime(responseTime);
				aipClientLoginLogRSV.insertAipClientLoginLog(logDto);
			}catch(Exception e){
				logger.error("insert T_aip_Client_Login_Log failted ", e);
			}
		}
		return JSON.toJSONString(resultMap);
	}
	
//	@Path("refreshToken")
//	@PUT
//	@Produces(MediaType.APPLICATION_JSON+";"+"charset=utf-8")
	@RequestMapping(value="/refreshToken",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String refreshAccessToken(HttpServletRequest request)throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,String> resultMap=new HashMap<String,String>();
		map.put("result", resultMap);
		String refreshtoken=request.getParameter("refresh_token");
		String clientId=request.getParameter("client_id");
		String clientSercert=request.getParameter("client_secret");
		Timestamp requestTime=new Timestamp(System.currentTimeMillis());
		Timestamp refTime=null;
		try{
			AipClientRefreshtokenDTO dto=aipClientRefreshtokenRSV.getAipClientRefreshtokenByKey(refreshtoken);
			if(null!=dto){
				//检验refreshtoken的有效性
				Timestamp oldExpireTime=new Timestamp(dto.getExpiresIn().getTime());
				Timestamp nowTime=new Timestamp(System.currentTimeMillis());
				refTime=new Timestamp(System.currentTimeMillis()+APIConstants.AipToken.AIP_EXPIRE_REFRESHTOKEN);
				Timestamp accTime=new Timestamp(System.currentTimeMillis()+APIConstants.AipToken.AIP_EXPIRE_ACCESSTOKEN);
				if(nowTime.compareTo(oldExpireTime)!=1){
					//生成accesstoken	
					String accessToken=SignUtil.createAccessToken(refreshtoken, clientId,nowTime,accTime);
					//表记录
					AipClientAccesstokenDTO accDto=new AipClientAccesstokenDTO();
					accDto.setAccessToken(accessToken);
					accDto.setClientId(dto.getClientId());
					accDto.setCreateTime(nowTime);
					accDto.setExpiresIn(accTime);
					accDto.setRefreshToken(refreshtoken);
					aipClientAccesstokenRSV.insertAipClientAccesstoken(accDto);
					//更新refreshtoken的失效时间
					dto.setUpdateTime(nowTime);
					dto.setExpiresIn(refTime);
					aipClientRefreshtokenRSV.updateAipClientRefreshtokenExpireTime(dto);
					//缓存accesstoken				
					CacheUtil.addItem(AipToken.AIP_CACHE_ACCESSTOKEN+accessToken, accessToken,(int)(AipToken.AIP_EXPIRE_ACCESSTOKEN/1000));
					//填充返回记录
					resultMap.put("uid", UUID.randomUUID().toString().replace("-", ""));
					resultMap.put("access_token", accessToken);
					resultMap.put("refresh_token",refreshtoken);
					resultMap.put("time",DateUtil.getDateString(nowTime, DateUtil.DATETIME_FORMAT) );
					resultMap.put("expires_in",DateUtil.getDateString(accTime,AipToken.AIP_EXPIRE_FORMAT_ACCESSTOKEN));
					resultMap.put("refresh_token_expires",DateUtil.getDateString(refTime,AipToken.AIP_EXPIRE_FORMAT_REFRESHTOKEN));
					
					
					map.put("code", APIConstants.SystemErrorCode.CODE_00000);
					map.put("code_desc", "success");					
				}else{
					map.put("code", APIConstants.SystemErrorCode.ERRORCODE_10003);
					map.put("code_desc", "refreshtoken is expired.");
				}				
			}else{
				map.put("code", APIConstants.SystemErrorCode.ERRORCODE_10001);
				map.put("code_desc", "invalid refreshtoken");
			}
		}catch(Exception e){
			logger.error("get accesstoken failted", e);
			map.put("code", APIConstants.SystemErrorCode.ERRORCODE_10014);
			map.put("code_desc", "Please contact administrator");
			map.put("result", new HashMap<String,String>());
		}finally{
			//入库T_CLIENT_LOGIN_LOG
			Timestamp responseTime=new Timestamp(System.currentTimeMillis());
			try{
				AipClientLoginLogDTO logDto=new AipClientLoginLogDTO();
				logDto.setClientId(clientId);
				logDto.setInIp(InetTool.getClientAddr(request));
				logDto.setInTime(requestTime);
				logDto.setRefreshToken(refreshtoken);
				logDto.setRefreshTokenExpires(refTime);
				logDto.setUpdateTime(responseTime);
				aipClientLoginLogRSV.insertAipClientLoginLog(logDto);
			}catch(Exception e){
				logger.error("insert T_aip_Client_Login_Log failted ", e);
			}
		}
		return JSON.toJSONString(map);
	}
	
}
