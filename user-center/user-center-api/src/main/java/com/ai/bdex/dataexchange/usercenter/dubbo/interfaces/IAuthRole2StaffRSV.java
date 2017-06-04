package com.ai.bdex.dataexchange.usercenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthRole2StaffReqDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthRole2StaffRespDTO;

/**
 * Created by yx on 2017/6/5.
 */
public interface IAuthRole2StaffRSV {
    public void updateRole2Staff(AuthRole2StaffReqDTO authRole2StaffReqDTO) throws Exception;

    public void insertRole2Staff(AuthRole2StaffReqDTO authRole2StaffReqDTO) throws Exception;

    public AuthRole2StaffRespDTO queryAuthRole2StaffByKey(String staffId, Integer roleId) throws Exception;
}
