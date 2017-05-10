package com.ai.bdex.dataexchange.usercenter.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.bdex.dataexchange.usercenter.dao.mapper.AuthStaffPassMapper;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthStaffPass;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthStaffPassExample;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthStaffPassDTO;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IAuthStaffPassSV;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.DateUtil;
import com.ai.paas.utils.SignUtil;

@Service("iAuthStaffPassSV")
public class AuthStaffPassSVImpl implements IAuthStaffPassSV {
	
	@Autowired
	private AuthStaffPassMapper authStaffPassMapper;

	@Override
	public int savePassInfo(AuthStaffPassDTO pass) throws Exception {
		AuthStaffPass record = new AuthStaffPass();
		BeanUtils.copyProperties(pass, record);
		record.setCreateStaff(pass.getStaffId());
		record.setCreateTime(DateUtil.getNowAsDate());
		record.setInvalidTime(DateUtil.getFutureTime());
		record.setIsFirst("1");//是否首次登录
		if(!"1".equals(pass.getPasswdFlag())){
			record.setStaffPasswd(SignUtil.SHA1(pass.getStaffPasswd()));
		}
		return authStaffPassMapper.insertSelective(record);
	}

	@Override
	public boolean validPasswd(AuthStaffPassDTO pass) throws Exception {
		AuthStaffPassExample example = new AuthStaffPassExample();
		AuthStaffPassExample.Criteria sql = example.createCriteria();
		String passwd = pass.getStaffPasswd();
		if(!"1".equals(pass.getPasswdFlag())){
			passwd = SignUtil.SHA1(pass.getStaffPasswd());
		}
		sql.andStaffIdEqualTo(pass.getStaffId());
		sql.andStaffPasswdEqualTo(passwd);
		List<AuthStaffPass> bean = authStaffPassMapper.selectByExample(example);
		if(!CollectionUtil.isEmpty(bean)){
			return true;
		}
		return false;
	}

	@Override
	public int updatePasswd(AuthStaffPassDTO pass) throws Exception {
		AuthStaffPass record = new AuthStaffPass();
		record.setStaffId(pass.getStaffId());
		if(!"1".equals(pass.getPasswdFlag())){
			record.setStaffPasswd(SignUtil.SHA1(pass.getStaffPasswd()));
		}
		record.setUpdateStaff(pass.getStaffId());
		record.setUpdateTime(DateUtil.getNowAsTimestamp());		
		return authStaffPassMapper.updateByPrimaryKeySelective(record);
	}

}
