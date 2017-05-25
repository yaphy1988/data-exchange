package com.ai.bdex.dataexchange.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.bdex.dataexchange.annotation.Security;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.StringUtil;

@Component("serviceCheckChain")
public class ServiceCheckChain implements PermissionCheckHandler{
	private List<PermissionCheckHandler> checkHandlers;
	@Autowired
	private PermissionCheckHandler accessTokenCheckHandler;
	@Override
	public String isPermission(Security paramSecurity) throws Exception {
		checkHandlers=new ArrayList<PermissionCheckHandler>();
		checkHandlers.add(accessTokenCheckHandler);
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
