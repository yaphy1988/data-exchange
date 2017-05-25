package com.ai.bdex.dataexchange.apigateway.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import com.ai.bdex.dataexchange.apigateway.dubbo.dto.APIConstants;
import com.ai.paas.utils.MD5Util;
import com.alibaba.fastjson.JSON;

public class ApiServiceUtil {
	
	public static String getParamMapMd5(Map<String, Object> paramMap){
		String result=null;
		TreeSet<String> set=new TreeSet<String>();
		
		if(null==paramMap||paramMap.size()==0){
			result=MD5Util.getMD5String("");
		}else{
			Set<Entry<String, Object>> entrySet=paramMap.entrySet();
			for(Entry<String, Object> entry:entrySet){				
				set.add(entry.getKey()+"="+entry.getValue());				
			}
			result=MD5Util.getMD5String(JSON.toJSONString(set));
		}
		return result;
	}

}
