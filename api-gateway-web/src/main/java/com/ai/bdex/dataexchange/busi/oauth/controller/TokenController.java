package com.ai.bdex.dataexchange.busi.oauth.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientAccesstokenDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientInfoDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientLoginLogDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.dto.AipClientRefreshtokenDTO;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipClientAccesstokenRSV;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipClientInfoRSV;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipClientLoginLogRSV;
import com.ai.bdex.dataexchange.aipcenter.dubbo.interfaces.IAipClientRefreshtokenRSV;
import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.ISmsSendRSV;
import com.ai.bdex.dataexchange.busi.model.AccessTokenSign;
import com.ai.bdex.dataexchange.busi.util.SignUtil;
import com.ai.bdex.dataexchange.constants.APIConstants;
import com.ai.bdex.dataexchange.constants.APIConstants.AipToken;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.paas.util.CacheUtil;
import com.ai.paas.utils.DateUtil;
import com.ai.paas.utils.InetTool;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;

@RestController
@Path("oauth2")
public class TokenController {
	 private final Log logger = LogFactory.getLog(getClass());
	 @DubboConsumer(timeout=3000)
	 private ISmsSendRSV smsSendRSV;
	 @DubboConsumer(timeout=3000)
	 private IAipClientInfoRSV aipClientInfoRSV;
	 @DubboConsumer(timeout=3000)
	 private IAipClientAccesstokenRSV aipClientAccesstokenRSV;
	 @DubboConsumer(timeout=3000)
	 private IAipClientRefreshtokenRSV aipClientRefreshtokenRSV;
	 @DubboConsumer(timeout=3000)
	 private IAipClientLoginLogRSV aipClientLoginLogRSV;
	 


	@Path("accessToken")
	@PUT
	@Produces(MediaType.APPLICATION_JSON+";"+"charset=utf-8")	
	public Map<String,String> getAccessToken(HttpServletRequest request)throws Exception{
		Map<String,String> map=new HashMap<String,String>();
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

							//进行缓存
							CacheUtil.addItem(AipToken.AIP_CACHE_REFRESHTOKEN+refreshToken, refreshToken);
							CacheUtil.addItem(AipToken.AIP_CACHE_ACCESSTOKEN+accessToken, accessToken);
							map.put("uid", UUID.randomUUID().toString().replace("-", ""));
							map.put("access_token", accessToken);
							map.put("refresh_token",refreshToken);
							map.put("time",DateUtil.getDateString(nowTime, DateUtil.DATETIME_FORMAT) );
							map.put("expires_in",DateUtil.getDateString(accessExpireTime,AipToken.AIP_EXPIRE_FORMAT_ACCESSTOKEN));
							map.put("refresh_token_expires",DateUtil.getDateString(refreshExpireTime,AipToken.AIP_EXPIRE_FORMAT_REFRESHTOKEN));
							
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
							
							
						}else{
							
						}
					}else{
						
					}
				}else{
					
				}
			}else{
				
			}
		} catch (Exception e) {

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
	}
}
