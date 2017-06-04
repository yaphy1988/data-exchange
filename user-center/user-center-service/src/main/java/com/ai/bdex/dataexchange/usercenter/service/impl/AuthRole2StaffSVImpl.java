package com.ai.bdex.dataexchange.usercenter.service.impl;

import com.ai.bdex.dataexchange.usercenter.dao.mapper.AuthRole2StaffMapper;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthRole2Staff;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthRole2StaffExample;
import com.ai.bdex.dataexchange.usercenter.dao.model.AuthRole2StaffKey;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthRole2StaffReqDTO;
import com.ai.bdex.dataexchange.usercenter.service.interfaces.IAuthRole2StaffSV;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yx on 2017/6/5.
 */
@Service("iAuthRole2StaffSV")
public class AuthRole2StaffSVImpl implements IAuthRole2StaffSV {

    private final static Logger log = LoggerFactory.getLogger(AuthRole2StaffSVImpl.class);

    @Autowired
    private AuthRole2StaffMapper authRole2StaffMapper;

    @Override
    public void updateRole2Staff(AuthRole2StaffReqDTO authRole2StaffReqDTO) throws Exception {

        AuthRole2Staff authRole2Staff = new AuthRole2Staff();
        String notCopy = "staffId,roleId";
        ObjectCopyUtil.copyObjValue(authRole2StaffReqDTO,authRole2Staff,notCopy,false);
        AuthRole2StaffExample authRole2StaffExample = new AuthRole2StaffExample();
        AuthRole2StaffExample.Criteria criteria = authRole2StaffExample.createCriteria();
        if (!StringUtil.isBlank(authRole2StaffReqDTO.getStaffId())){
            criteria.andStaffIdEqualTo(authRole2StaffReqDTO.getStaffId());
        }
        if (authRole2StaffReqDTO.getRoleId()!=null ){
            criteria.andRoleIdEqualTo(authRole2StaffReqDTO.getRoleId());
        }
        authRole2StaffMapper.updateByExampleSelective(authRole2Staff,authRole2StaffExample);
    }

    @Override
    public void insertRole2Staff(AuthRole2StaffReqDTO authRole2StaffReqDTO) throws Exception {

        AuthRole2Staff authRole2Staff = new AuthRole2Staff();
        ObjectCopyUtil.copyObjValue(authRole2StaffReqDTO,authRole2Staff,null,false);
        authRole2StaffMapper.insert(authRole2Staff);
    }

    @Override
    public AuthRole2Staff queryAuthRole2StaffByKey(String staffId, Integer roleId) throws Exception {

        AuthRole2StaffKey authRole2StaffKey = new AuthRole2StaffKey();
        authRole2StaffKey.setStaffId(staffId);
        authRole2StaffKey.setRoleId(roleId);

        AuthRole2Staff authRole2Staff = authRole2StaffMapper.selectByPrimaryKey(authRole2StaffKey);
        return authRole2Staff;
    }
}
