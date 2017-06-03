package com.ai.bdex.dataexchange.usercenter.dubbo.interfaces;

import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthRoleReqDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.AuthRoleRespDTO;

import java.util.List;

/**
 * Created by yx on 2017/6/2.
 */
public interface IAuthRoleRSV {

    public List<AuthRoleRespDTO> queryAuthRoleList(AuthRoleReqDTO authRoleReqDTO) throws Exception;

    public List<AuthRoleRespDTO> queryAuthRoleListByRole2Staff(AuthRoleReqDTO authRoleReqDTO) throws Exception;
}
