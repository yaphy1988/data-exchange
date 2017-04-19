package com.ai.bdex.dataexchange.usercenter.dubbo.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.exception.BusinessException;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.LoginInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.StaffInfoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.ILoginRSV;
import com.ai.bdex.dataexchange.usercenter.service.impl.LoginSVImpl;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.ILoginSV;
import com.ai.paas.utils.DateUtil;
import com.ai.paas.utils.StringUtil;

@Service("iLoginRSV")
public class LoginRSVImpl implements ILoginRSV{
	private static final Logger log = Logger.getLogger(LoginSVImpl.class);
	
	@Autowired
	private ILoginSV iLoginSV;

	@Override
	public StaffInfoDTO loginVerify(LoginInfoDTO loginInfo) throws BusinessException {
		Long begin=System.currentTimeMillis();
		// 记录登陆日志
		log.info("记录登陆信息-->用户名:" + loginInfo.getLoginName() + " 验证码："
				+ loginInfo.getInputVerifyCode() + " 登录ip:"
				+ loginInfo.getLoginIp() + " 时间："
				+ DateUtil.getNowAsStr()+ " 备注："
		                + loginInfo.getRemark());
		// 业务校验		
		if (StringUtil.isBlank(loginInfo.getLoginPwd())) {
            throw new BusinessException("登录密码");
        }
		if (StringUtil.isBlank(loginInfo.getLoginName())) {
			throw new BusinessException("登录账号");
		}
		// 业务逻辑
		// 定义VO对象，作为返回参数。
		StaffInfoDTO StaffInfo = null;
		try {
			StaffInfo = new StaffInfoDTO();
			// 调用业务层方法
			StaffInfo = iLoginSV.loginVerify(loginInfo);
		} catch (Exception e) {
			// 错误信息通过异常抛出
			log.error("LoginSV verify error:", e);
			// 判断是否有业务异常，有则抛BusinessException
			if (e instanceof BusinessException)
				throw (BusinessException) e;
			// 无业务异常有运行异常，抛出GenericException
			else {
				throw new BusinessException(e.getMessage());
			}
		}
		log.error("zouwjlogintime**"+(System.currentTimeMillis()-begin));
		return StaffInfo;
	}

	@Override
	public int updateLastLogin(String staffId) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
