package com.ai.bdex.dataexchange.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ai.bdex.dataexchange.annotation.Security;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.StringUtil;


public class ServiceCheckChain implements PermissionCheckHandler{
	private List<PermissionCheckHandler> checkHandlers;
	@Override
	public String isPermission(Security paramSecurity) throws Exception {
		String result=null;
		if(!CollectionUtil.isEmpty(checkHandlers)){
			for(PermissionCheckHandler handler:checkHandlers){
				result=handler.isPermission(paramSecurity);
				if(!StringUtil.isBlank(result)){
					break;
				}
			}
		}
		return result;
	}
	
	public List<PermissionCheckHandler> getCheckHandlers() {
		return checkHandlers;
	}
	public void setCheckHandlers(List<PermissionCheckHandler> checkHandlers) {
		this.checkHandlers = checkHandlers;
	}
	
}
