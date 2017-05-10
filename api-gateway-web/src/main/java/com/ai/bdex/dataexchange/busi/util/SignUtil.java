package com.ai.bdex.dataexchange.busi.util;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.ai.bdex.dataexchange.busi.model.AccessTokenSign;
import com.ai.bdex.dataexchange.utils.MD5Util;
import com.ai.paas.utils.DateUtil;

public class SignUtil {
	 private static final String DATEFORMAT2MC="yyyy-MM-dd HH:mm:ss:SSS";
	 
	public static String getAccessTokenSign(AccessTokenSign sign){
		String result=null;
		try{
			StringBuffer bf=new StringBuffer();
			bf.append(sign.getClientSecret());
			bf.append(sign.getTimestamp());
			bf.append(sign.getClientId());
			bf.append(sign.getUserName());
			bf.append(sign.getPassword());
			bf.append(sign.getGrantType());
			bf.append(sign.getScope());
			bf.append(sign.getClientSecret());
			result=MD5Util.md5(bf.toString()).toUpperCase();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static String createRefreshToken(String clientId,Timestamp nowTime,Timestamp expireTime ){
		String refreshToken=null;
		String nowTimeStr=DateUtil.getDateString(nowTime, DATEFORMAT2MC);
		String expireTimeStr=DateUtil.getDateString(expireTime, DATEFORMAT2MC);
		refreshToken=MD5Util.md5(nowTimeStr+clientId+expireTimeStr).toUpperCase();
		return refreshToken;
	}	
	
	public static String createAccessToken(String refreshToken,String clientId,Timestamp nowTime,Timestamp expireTime){
		String nowTimeStr=DateUtil.getDateString(nowTime, DateUtil.DATETIME_FORMAT);
		String expireTimeStr=DateUtil.getDateString(expireTime, DateUtil.DATETIME_FORMAT);
		return MD5Util.md5(refreshToken+nowTimeStr+clientId+expireTimeStr).toUpperCase();
	}
	
}
