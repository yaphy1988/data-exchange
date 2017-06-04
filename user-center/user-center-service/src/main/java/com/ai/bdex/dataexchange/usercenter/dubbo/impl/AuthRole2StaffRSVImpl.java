package com.ai.bdex.dataexchange.usercenter.dubbo.impl;

import com.ai.bdex.dataexchange.usercenter.dao.model.AuthRole2Staff;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthRole2StaffReqDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthRole2StaffRespDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IAuthRole2StaffRSV;
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
@Service("iAuthRole2StaffRSV")
public class AuthRole2StaffRSVImpl implements IAuthRole2StaffRSV {

    private final static Logger log = LoggerFactory.getLogger(AuthRole2StaffRSVImpl.class);

    @Autowired
    private IAuthRole2StaffSV iAuthRole2StaffSV;

    @Override
    public void updateRole2Staff(AuthRole2StaffReqDTO authRole2StaffReqDTO) throws Exception {
        if (authRole2StaffReqDTO == null){
            log.error("更新用户角色关系表异常，入参为空");
        }
        try{
            iAuthRole2StaffSV.updateRole2Staff(authRole2StaffReqDTO);
        }catch (Exception e){
            log.error("更新用户角色关系表异常：",e);
        }
    }

    @Override
    public void insertRole2Staff(AuthRole2StaffReqDTO authRole2StaffReqDTO) throws Exception {
        if (authRole2StaffReqDTO == null){
            log.error("插入用户角色关系表异常，入参为空！");
        }
        try{
            iAuthRole2StaffSV.insertRole2Staff(authRole2StaffReqDTO);
        }catch (Exception e){
            log.error("插入用户角色关系表异常：",e);
        }
    }

    @Override
    public AuthRole2StaffRespDTO queryAuthRole2StaffByKey(String staffId, Integer roleId) throws Exception {
        if (StringUtil.isBlank(staffId) || roleId == null || roleId.intValue()<=0){
            log.error("查询用户信息异常，staffId或roleId为空");
        }
        AuthRole2StaffRespDTO authRole2StaffRespDTO = null;
        try {
            AuthRole2Staff authRole2Staff = iAuthRole2StaffSV.queryAuthRole2StaffByKey(staffId,roleId);
            if (authRole2Staff!=null){
                authRole2StaffRespDTO = new AuthRole2StaffRespDTO();
                ObjectCopyUtil.copyObjValue(authRole2Staff,authRole2StaffRespDTO,null,false);
            }
        }catch (Exception e){
            log.error("查询用户信息异常：",e);
        }
        return authRole2StaffRespDTO;
    }
}
