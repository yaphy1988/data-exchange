package com.ai.bdex.dataexchange.util;

import com.ai.bdex.dataexchange.exception.BusinessException;

/**
 * 
 * Title: 创建各种异常 <br>
 * Description: <br>
 * Date: 2016年11月30日 <br>
 * Copyright (c) 2016 AILK <br>
 * 
 * @author liangyi
 */
public class ExceptionFactory {

	/**
	 * 构建一个BusinessException
	 * @param e
	 * @return
	 * @author liangyi
	 */
	public static BusinessException buildABusinessException(Exception e){
		BusinessException be=buildABusinessException(e, "bdex.system.outService");
		return be;
	}
	
	/**
	 * 构建一个BusinessException，
	 * @param e
	 * @param errorCode
	 * @return
	 * @author liangyi
	 */
	public static BusinessException buildABusinessException(Exception e, String errorCode){
		BusinessException be=null;
		if (e==null) {
			be=new BusinessException(errorCode);
        }else if(e instanceof BusinessException){
        	be=(BusinessException)e;
        }else {
        	be = new BusinessException(errorCode);
        }
		return be;
	}
}
