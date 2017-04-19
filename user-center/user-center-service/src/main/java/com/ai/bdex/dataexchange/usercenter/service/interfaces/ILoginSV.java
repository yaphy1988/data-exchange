package com.ai.bdex.dataexchange.usercenter.service.interfaces;

import com.ai.bdex.dataexchange.usercenter.dubbo.dto.LoginInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.StaffInfoDTO;

public interface ILoginSV {

	/* 接口内方法，可以有多个。此方法为登陆验证。
	 * StaffInfoVO 返回对象，对应文档接口中参
	 * loginVerify（）方法名，对应文档内方法
	 * LoginInfo   入参，对应文档入参
	 * */
    public StaffInfoDTO loginVerify(LoginInfoDTO loginInfo) throws Exception;
}
