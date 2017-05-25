package com.ai.bdex.dataexchange.utils;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ApiWebHttpUtil {
	
	public static Map<String, Object> getParamString(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<?> fields = request.getParameterNames();
		do {
			if (!fields.hasMoreElements())
				break;
			String field = (String) fields.nextElement();
			String values[] = request.getParameterValues(field);
			if (values.length > 1)
				map.put(field, values);
			else
				map.put(field, values[0]);
		} while (true);
		return map;
	}

	/**
	 * @Title: getParamsMap
	 * @Description:解析request报文封装成Map<String,String>
	 * @author: luocan
	 * @Create: 2014年8月19日 上午10:26:12
	 * @Modify: 2014年8月19日 上午10:26:12
	 * @param:
	 * @return:
	 * @throws UnsupportedEncodingException 
	 */
	public static Map<String, String> getParamsMap(HttpServletRequest request){
		
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<?> fields = request.getParameterNames();
		do {
			if (!fields.hasMoreElements())
				break;
			String field = (String) fields.nextElement();
			String values[] = request.getParameterValues(field);
			if (values.length > 1)
				map.put(field, values.toString());
			else
				map.put(field, values[0]);
		} while (true);
		return map;
	}
}
