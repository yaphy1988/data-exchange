package com.ai.bdex.dataexchange.usercenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.LoginInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.StaffInfoDTO;

public interface ILoginRSV {

	/* 接口内方法，可以有多个。此方法为登陆验证。
	 * StaffInfoVO 返回对象，对应文档接口中参
	 * loginVerify（）方法名，对应文档内方法
	 * LoginInfo   入参，对应文档入参
	 * */
    public StaffInfoDTO loginVerify(LoginInfoDTO loginInfo) throws BusinessException;
    
    /**
     * 修改上次登录时间
     * @param staffId
     * @return
     * @throws BusinessException
     */
    public int updateLastLogin(String staffId)throws BusinessException;
}
