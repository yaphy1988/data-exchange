package com.ai.bdex.dataexchange.apigateway.dubbo.interfaces;

import java.util.List;
import java.util.Map;

public interface ISmsSendRSV {
		public static final String  SMS2ALIBABA="send_sms_Alibaba";
		/**
		 * 
		 * @param phone 必填
		 * @param params templateCode 模板对应的所有参数
		 * @param templateCode 模板编码 ，必填
		 * @param uuid 唯一标识
		 * @param owner 请求方标识
		 * @throws Exception
		 */
		void sendSmsByAlibaba(List<String> phone,Map<String,String> params,String templateCode,String uuid,String owner)throws Exception;		
}
