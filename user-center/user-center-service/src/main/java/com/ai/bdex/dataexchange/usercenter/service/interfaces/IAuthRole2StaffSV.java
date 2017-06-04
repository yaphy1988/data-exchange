package com.ai.bdex.dataexchange.usercenter.service.interfaces;

import com.ai.bdex.dataexchange.usercenter.dao.model.AuthRole2Staff;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthRole2StaffReqDTO;

/**
 * Created by yx on 2017/6/5.
 */
public interface IAuthRole2StaffSV {

    public void updateRole2Staff(AuthRole2StaffReqDTO authRole2StaffReqDTO) throws Exception;

    public void insertRole2Staff(AuthRole2StaffReqDTO authRole2StaffReqDTO) throws Exception;

    public AuthRole2Staff queryAuthRole2StaffByKey(String staffId,Integer roleId) throws Exception;
}
